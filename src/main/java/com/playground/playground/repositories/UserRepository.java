package com.playground.playground.repositories;


import com.playground.playground.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("SELECT u FROM User as u WHERE u.username = ?1")
    User findOneByName(String name);
}
