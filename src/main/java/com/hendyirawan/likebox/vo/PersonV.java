package com.hendyirawan.likebox.vo;

import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * Person partial value object that is stored in Graph database (usually Neo4j).
 * @author ceefour
 */
public interface PersonV extends VertexFrame {
	
	/**
	 * Directory entry <tt>uid</tt> attribute.
	 */
	@Property("_rowId") String getId();
	@Property("_rowId") void setId(String id);
	
	/**
	 * Slug (aka Directory <tt>uniqueIdentifier</tt>) used in SEO-friendly URIs.
	 */
	@Property("slug") String getSlug();
	@Property("slug") void setSlug(String slug);
	
	/**
	 * Display name (can be full name, nickname, slug, screen name, etc. whatever is commonly used by the particular app).
	 */
	@Property("name") String getName();
	@Property("name") void setName(String name);
	
	/**
	 * Avatar Photo ID directly usable by Image Store.
	 */
	@Property("photoId") String getPhotoId();
	@Property("photoId") void setPhotoId(String photoId);
	
}
