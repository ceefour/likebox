/**
 * 
 */
package com.hendyirawan.likebox.app;

import java.util.Collection;

import com.tinkerpop.frames.Relation;

/**
 * Adds app-specific relationships.
 * @author ceefour
 */
public interface PersonV extends com.hendyirawan.likebox.vo.PersonV {

	@Relation(label="LIKE") Collection<ArticleV> getLikeArticles();
	@Relation(label="LIKE") void addLikeArticle(ArticleV article);
	
	@Relation(label="AROUND") Collection<PlaceV> getAroundPlaces();
	@Relation(label="AROUND") void addAroundPlace(PlaceV place);
	
}
