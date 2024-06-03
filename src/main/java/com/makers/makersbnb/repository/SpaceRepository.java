package com.makers.makersbnb.repository;

import com.makers.makersbnb.model.Space;
import org.springframework.data.repository.CrudRepository;

// SpaceRepository inherits from CrudRepository
// The type parameters 'Space' and 'Long' relate to the Model and id
// We're saying - this repository should create instances of Space
// and the ids of those spaces should be of type Long
public interface SpaceRepository extends CrudRepository<Space, Long> {
}
