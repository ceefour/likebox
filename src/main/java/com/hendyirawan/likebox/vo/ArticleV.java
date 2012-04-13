package com.hendyirawan.likebox.vo;

import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * Article partial value object that is stored in Graph database (usually Neo4j).
 * @author ceefour
 */
public interface ArticleV extends VertexFrame {
	
	/**
	 * Article ID, usually some kind of almost SEO friendly lowercase identifier, but should by limited in length.
	 */
	@Property("_rowId") String getId();
	@Property("_rowId") void setId(String id);
	
	/**
	 * Slug used in SEO-friendly URIs.
	 */
	@Property("slug") String getSlug();
	@Property("slug") void setSlug(String slug);
	
	/**
	 * Article title, in plain text format.
	 */
	@Property("name") String getName();
	@Property("name") void setName(String name);
	
	/**
	 * Photo ID directly usable by Image Store.
	 * 
	 * If photo ID is not supported by the Article Store backend, then there should be
	 * <tt>thumbnailPhotoPath</tt>, <tt>smallPhotoPath</tt>, etc. properties. in the {@link ArticleV} subclass.
	 */
	@Property("photoId") String getPhotoId();
	@Property("photoId") void setPhotoId(String photoId);
	
}
