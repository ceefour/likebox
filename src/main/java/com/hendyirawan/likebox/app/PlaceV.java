package com.hendyirawan.likebox.app;

import java.util.Collection;

import com.tinkerpop.frames.Direction;
import com.tinkerpop.frames.Relation;

/**
 * Add app-specific relations.
 * @author ceefour
 */
public interface PlaceV extends com.hendyirawan.likebox.vo.PlaceV {
	
	@Relation(label="AROUND", direction=Direction.INVERSE) Collection<ArticleV> getAroundByArticles();
	@Relation(label="AROUND", direction=Direction.INVERSE) void addAroundByArticle(ArticleV article);
	
	@Relation(label="AROUND", direction=Direction.INVERSE) Collection<PersonV> getAroundByPeople();
	@Relation(label="AROUND", direction=Direction.INVERSE) void addAroundByPerson(PersonV person);
	
}
