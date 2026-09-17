package org.example.repository;

import org.example.entities.RefershToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends CrudRepository<RefershToken,Integer> {

    Optional<RefershToken> findByToken(String token);

}
