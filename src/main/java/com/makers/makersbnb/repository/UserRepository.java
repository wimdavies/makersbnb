package com.makers.makersbnb.repository;

import com.makers.makersbnb.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByGithubId(Integer githubId);
}
