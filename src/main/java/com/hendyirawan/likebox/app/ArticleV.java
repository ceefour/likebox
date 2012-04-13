package com.hendyirawan.likebox.app;

import java.util.Collection;

import com.tinkerpop.frames.Direction;
import com.tinkerpop.frames.Relation;

/**
 * Adds app-specific relations.
 * @author ceefour
 */
public interface ArticleV extends com.hendyirawan.likebox.vo.ArticleV {

	@Relation(label="LIKE", direction=Direction.INVERSE) Collection<PersonV> getLikedByPeople();
	@Relation(label="LIKE", direction=Direction.INVERSE) void addLikedByPerson(PersonV person);
	
	@Relation(label="AROUND") Collection<PlaceV> getAroundPlaces();
	@Relation(label="AROUND") void addAroundPlace(PlaceV place);
}
