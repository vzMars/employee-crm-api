package dev.marcosgonzalez.employeecrmapi.repository;

import dev.marcosgonzalez.employeecrmapi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    @Query(collation = "{'locale':  'en', 'strength':  2}")
    Optional<User> findByUsername(String username);

    @Query(collation = "{'locale':  'en', 'strength':  2}")
    boolean existsByUsernameOrEmail(String username, String email);
}
