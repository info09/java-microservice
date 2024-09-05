package com.learning.profile_service.repository;

import com.learning.profile_service.entity.UserProfile;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserProfileRepository extends Neo4jRepository<UserProfile, String> {
}
