package org.example.Service;

import org.example.entities.RefershToken;
import org.example.entities.UsersInfo;
import org.example.repository.RefreshTokenRepository;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class RefershTokenService {

    @Autowired
    RefreshTokenRepository refreshTokenRepository;

    @Autowired
    UserRepository userRepository;

    public RefershToken createRefreshToken(String username){
        UsersInfo userInfo = userRepository.findByUsername(username);
        RefershToken refreshToken = RefershToken.builder()
                .usersInfo(userInfo)
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(600000))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public RefershToken verifyExpiration(RefershToken token){
        if(token.getExpiryDate().compareTo(Instant.now()) < 0){
            refreshTokenRepository.delete(token);
            throw new RuntimeException(token.getToken() + " Refresh token was expired. Please make a new signin request");
        }
        return token;
    }


    public Optional<RefershToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }


}
