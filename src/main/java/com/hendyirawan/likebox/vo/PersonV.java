package com.hendyirawan.likebox.vo;

import com.tinkerpop.frames.VertexFrame;

/**
 * Person partial value object that is stored in Graph database (usually Neo4j).
 * @author ceefour
 */
public interface PersonV extends VertexFrame {
	
	/**
	 * Directory entry <tt>uid</tt> attribute.
	 */
	String getId();
	void setId(String id);
	
	/**
	 * Slug (aka Directory <tt>uniqueIdentifier</tt>) used in SEO-friendly URIs.
	 */
	String getSlug();
	void setSlug(String slug);
	
	/**
	 * Display name (can be full name, nickname, slug, screen name, etc. whatever is commonly used by the particular app).
	 */
	String getName();
	void setName(String name);
	
	/**
	 * Avatar Photo ID directly usable by Image Store.
	 */
	String getPhotoId();
	void setPhotoId(String photoId);
	
}
