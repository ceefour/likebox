package com.hendyirawan.likebox.dao;

import org.neo4j.rest.graphdb.RestGraphDatabase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hendyirawan.likebox.app.ArticleV;
import com.hendyirawan.likebox.app.PersonV;
import com.hendyirawan.likebox.app.PlaceV;
import com.tinkerpop.blueprints.pgm.Index;
import com.tinkerpop.blueprints.pgm.Vertex;
import com.tinkerpop.blueprints.pgm.impls.neo4j.Neo4jGraph;
import com.tinkerpop.frames.FramesManager;

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
		} catch (Exception e) {
			log.info("Add Person vertex ID={} slug={} name={}", new Object[] {
					id, slug, name });
			person = frames.createFramedVertex(PersonV.class);
			person.setId(id);
			person.setSlug(slug);
			person.setName(name);
			person.setPhotoId(photoId);
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
		} catch (Exception e) {
			log.info("Add Article vertex ID={} slug={} name={}", new Object[] {
					id, slug, name });
			article = frames.createFramedVertex(ArticleV.class);
			article.setId(id);
			article.setSlug(slug);
			article.setName(name);
			article.setPhotoId(photoId);
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
		} catch (Exception e) {
			log.info("Add Place vertex ID={} slug={} name={}", new Object[] {
					id, slug, name });
			place = frames.createFramedVertex(PlaceV.class);
			place.setId(id);
			place.setSlug(slug);
			place.setName(name);
			place.setPhotoId(photoId);
			placeIdx.put("_rowId", id, place.asVertex());
		}
		return place;
	}
	
	public void addLike(PersonV person, ArticleV article) {
		person.addLikeArticle(article);
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
