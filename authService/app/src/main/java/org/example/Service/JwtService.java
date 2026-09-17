package org.example.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static java.security.KeyRep.Type.SECRET;

@Service
public class JwtService {

    public static final String SECRET_KEY ="d8f4c9b1e7a2f6c3d9e5b8a1c7f2d4e9b6a3c8d1e5f7a9b2c4d6e8f1a3b5c7d9";

    public String extractUsername(String token){
        return extractClaim(token,Claims::getSubject);


    }

    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
    public Boolean validateToken(String token, UserDetails userDetails){
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String GenerateToken(String username){
        Map<String ,Object> claims = new HashMap<>();
        return generateToken(claims,username);
    }
    private String generateToken(Map<String, Object> claims, String username) {

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(
                        new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24)
                ) // 24 hours
                .signWith(getSignKey())
                .compact();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.
                parser().
                setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
