package com.hendyirawan.likebox.vo;

import com.tinkerpop.frames.VertexFrame;

/**
 * Article partial value object that is stored in Graph database (usually Neo4j).
 * @author ceefour
 */
public interface ArticleV extends VertexFrame {
	
	/**
	 * Article ID, usually some kind of almost SEO friendly lowercase identifier, but should by limited in length.
	 */
	String getId();
	void setId(String id);
	
	/**
	 * Slug used in SEO-friendly URIs.
	 */
	String getSlug();
	void setSlug(String slug);
	
	/**
	 * Article title, in plain text format.
	 */
	String getName();
	void setName(String name);
	
	/**
	 * Photo ID directly usable by Image Store.
	 * 
	 * If photo ID is not supported by the Article Store backend, then there should be
	 * <tt>thumbnailPhotoPath</tt>, <tt>smallPhotoPath</tt>, etc. properties. in the {@link ArticleV} subclass.
	 */
	String getPhotoId();
	void setPhotoId(String photoId);
	
}
