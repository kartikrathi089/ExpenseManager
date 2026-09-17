package org.example.repository;

import org.example.entities.UsersInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UsersInfo,Long> {

    public UsersInfo findByUsername(String username);
}
