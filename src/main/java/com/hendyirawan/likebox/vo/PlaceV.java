package com.hendyirawan.likebox.vo;

import com.tinkerpop.frames.Property;
import com.tinkerpop.frames.VertexFrame;

/**
 * Place partial value object that is stored in Graph database (usually Neo4j).
 * 
 * To provide application-specific relationships, extend this interface.
 * @author ceefour
 */
public interface PlaceV extends VertexFrame {
	
	/**
	 * Place ID, usually some kind of almost SEO friendly lowercase identifier, but should by limited in length.
	 */
	@Property("_rowId") String getId();
	@Property("_rowId") void setId(String id);
	
	/**
	 * Slug used in SEO-friendly URIs.
	 */
	@Property("slug") String getSlug();
	@Property("slug") void setSlug(String slug);
	
	/**
	 * Place name, in plain text format.
	 */
	@Property("name") String getName();
	@Property("name") void setName(String name);
	
	/**
	 * Photo ID directly usable by Image Store.
	 * 
	 * If photo ID is not supported by the Article Store backend, then there should be
	 * <tt>thumbnailPhotoPath</tt>, <tt>smallPhotoPath</tt>, etc. properties. in the {@link PlaceV} subclass.
	 */
	@Property("photoId") String getPhotoId();
	@Property("photoId") void setPhotoId(String photoId);
	
	/**
	 * Latitude in decimal degrees, useful to link directly to OpenStreetMap.
	 */
	@Property("lat") Double getLat();
	@Property("lat") public void setLat(Double lat);
	/**
	 * Longitude in decimal degrees, useful to link directly to OpenStreetMap.
	 */
	@Property("lng") Double getLng();
	@Property("lng") public void setLng(Double lng);
	/**
	 * Altitude or elevation in meters, useful to link directly to OpenStreetMap.
	 * Note: This is probably optional, or very rarely used (?).
	 */
	@Property("elevation") Double getElevation();
	@Property("elevation") public void setElevation(Double elevation);
	
	/**
	 * City or locality name.
	 */
	@Property("city") String getCity();
	@Property("city") public void setCity();
	
	/**
	 * State or province name.
	 * Note: "state" is not used due to potential ambiguity with e.g. "object state".
	 */
	@Property("province") String getProvince();
	@Property("province") public void setProvince();
	
	/**
	 * Country name (not ISO code).
	 */
	@Property("country") String getCountry();
	@Property("country") public void setCountry(String country);
	
	/**
	 * ISO 3166-1-alpha-2 country code, useful for lookup.
	 * See: http://www.iso.org/iso/country_codes/iso_3166_code_lists/country_names_and_code_elements.htm
	 */
	@Property("countryCode") String getCountryCode();
	@Property("countryCode") public void setCountryCode(String countryCode);
	
}
