package com.slippery.fakestore.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private final Long EXPIRATION_TIME =8640000L;
    private final String SECRET_KEY ="869033b7a5aba3a5d250890d364177bfdc16cf267d70bfea0072877af9278f7132986f5b4cdac71761c1714297cd888ca477307c9976bd8bf4a632e91e8cd9d6d157d365a9499cf8b96188cb47ac51b8727d3306ea66a6c452be63f4d60401188cd54e5fdfe9ecb2cc72fe4b51794943bd2ee2aba4d571d039ed32166b1b11cab9eff4723cd11012159b098a6f069da615eb97ce182ef08b567b94e13f99174739adc7c68178a1bd59b8326902b2fbc5db68cc6cce9240243e4fd13852df5d75c8facd4fb91fc6cbc8eb47e3af728aefbbe38a22ffc217f83dd795885b24bbff26a23e5da326977d9d69fb2f658c3b92a7327e93acebad98532f6bbd59b04668";

    protected SecretKey generateSecretKey(){
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String generateJwtToken(String username){
        Map<String,Object> claims =new HashMap<>();

        return Jwts.builder()
                .claims()
                .add(claims)

                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION_TIME))
                .and()
                .signWith(generateSecretKey())
                .compact();
    }
    public String extractUsername(String token){
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(Jwts.parser().verifyWith(generateSecretKey()).build().parseSignedClaims(token).getPayload());

    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(generateSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
