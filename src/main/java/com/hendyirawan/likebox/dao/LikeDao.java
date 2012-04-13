package com.hendyirawan.likebox.dao;

import java.util.List;

import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hendyirawan.likebox.app.ArticleV;
import com.hendyirawan.likebox.app.PersonV;
import com.hendyirawan.likebox.app.PlaceV;
import com.tinkerpop.blueprints.pgm.Edge;
import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.frames.FramesManager;
import com.tinkerpop.gremlin.java.GremlinPipeline;

/**
 * DAO to manage Like relations.
 * @author ceefour
 */
public class LikeDao {

	private transient Logger log = LoggerFactory.getLogger(LikeDao.class);
	private String uri = "http://localhost:7474/db/data";
	private RestGraphDatabase graphDb;
	private Neo4jGraph g;
	private FramesManager frames;
	private String prefix;
	
	public void init() {
		log.info("Connecting to Neo4j REST Graph Database {}", uri);
		graphDb = new RestGraphDatabase(uri);
		g = new Neo4jGraph(graphDb);
		frames = new FramesManager(g);
	}
	
	public void destroy() {
		g.shutdown();
	}
	
	protected Index<Vertex> getIndex(String kind) {
		String indexName = prefix + kind;
		log.debug("Get vertex index {}", indexName);
		try {
			return g.getIndex(indexName, Vertex.class);
		} catch (Exception e) {
			log.info("Creating vertex index " + indexName, e);
			return g.createManualIndex(indexName, Vertex.class);
		}
	}
	
	public PersonV getPerson(String id, String slug, String name, String photoId) {
		Index<Vertex> personIdx = getIndex("Person");
		PersonV person;
		try {
			log.debug("Find vertex {} ID {}", personIdx.getIndexName(), id);
			Vertex v = personIdx.get("_rowId", id).iterator().next();
			person = frames.frame(v, PersonV.class);
			log.debug("Found Person vertex #{} id={} slug={} name={}", new Object[] {
					person.asVertex().getId(), person.getId(), person.getSlug(), person.getName() });
		} catch (Exception e) {
			log.info("Add Person vertex ID={} slug={} name={}", new Object[] {
					id, slug, name });
			person = frames.createFramedVertex(PersonV.class);
			person.setId(id);
			person.setSlug(slug);
			person.setName(name);
			person.setPhotoId(photoId);
			log.info("Added Person vertex #{} ID={} slug={} name={}", new Object[] {
					person.asVertex().getId(), id, slug, name });
			personIdx.put("_rowId", id, person.asVertex());
		}
		return person;
	}
	
	public ArticleV getArticle(String id, String slug, String name, String photoId) {
		Index<Vertex> articleIdx = getIndex("Article");
		ArticleV article;
		try {
			log.debug("Find vertex {} ID {}", articleIdx.getIndexName(), id);
			Vertex v = articleIdx.get("_rowId", id).iterator().next();
			article = frames.frame(v, ArticleV.class);
			log.debug("Found Article vertex #{} id={} slug={} name={}", new Object[] {
					article.asVertex().getId(), article.getId(), article.getSlug(), article.getName() });
		} catch (Exception e) {
			log.info("Add Article vertex ID={} slug={} name={}", new Object[] {
					id, slug, name });
			article = frames.createFramedVertex(ArticleV.class);
			article.setId(id);
			article.setSlug(slug);
			article.setName(name);
			article.setPhotoId(photoId);
			log.info("Added Article vertex #{} ID={} slug={} name={}", new Object[] {
					article.asVertex().getId(), id, slug, name });
			articleIdx.put("_rowId", id, article.asVertex());
		}
		return article;
	}
	
	public PlaceV getPlace(String id, String slug, String name, String photoId) {
		Index<Vertex> placeIdx = getIndex("Place");
		PlaceV place;
		try {
			log.debug("Find vertex {} ID {}", placeIdx.getIndexName(), id);
			Vertex v = placeIdx.get("_rowId", id).iterator().next();
			place = frames.frame(v, PlaceV.class);
			log.debug("Found Place vertex #{} id={} slug={} name={}", new Object[] {
				place.asVertex().getId(), place.getId(), place.getSlug(), place.getName() });
		} catch (Exception e) {
			log.info("Add Place vertex ID={} slug={} name={}", new Object[] {
					id, slug, name });
			place = frames.createFramedVertex(PlaceV.class);
			place.setId(id);
			place.setSlug(slug);
			place.setName(name);
			place.setPhotoId(photoId);
			log.info("Added Place vertex #{} ID={} slug={} name={}", new Object[] {
					place.asVertex().getId(), id, slug, name });
			placeIdx.put("_rowId", id, place.asVertex());
		}
		return place;
	}
	
	/**
	 * Please use this method instead of directly on value objects, because they don't check for existing relation.
	 * @param person
	 * @param article
	 */
	public void addLike(PersonV person, final ArticleV article) {
		if ( person.getLikeArticles().contains(article) ) {
			log.debug("Person {} already likes Article {}", person.getId(), article.getId());
		} else {
			log.debug("Person {} now likes Article {}", person.getId(), article.getId());
			person.addLikeArticle(article);
		}
	}

	/**
	 * Please use this method instead of directly on value objects, because they don't check for existing relation.
	 * @param person
	 * @param article
	 */
	public void removeLike(PersonV person, final ArticleV article) {
		log.debug("Person {} unlikes Article {}", person.getId(), article.getId());
		// If RestGraphDatabase supports Cypher, this would work and very fast:
		// START liker=node:likeboxPerson(_rowId='hendy'), liked=node:likeboxArticle(_rowId='air_itu_sehat') MATCH liker -[rel:LIKE]-> liked RETURN rel
		GremlinPipeline<Vertex, Edge> pipe = new GremlinPipeline<Vertex, Edge>(person.asVertex());
		List<Edge> rels = (List<Edge>) pipe.outE("LIKE").inV().has("_rowId", article.getId()).back(2).toList();
		for (Edge e : rels) {
			log.info("Remove edge #{} Person {} like Article {}", new Object[] {
					e.getId(), person.getId(), article.getId() });
			g.removeEdge(e);
		}
		// not yet supported
//		person.getLikeArticles().remove(article);
	}

	/**
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * @param uri the uri to set
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * @return the prefix
	 */
	public String getPrefix() {
		return prefix;
	}

	/**
	 * @param prefix the prefix to set
	 */
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
}
