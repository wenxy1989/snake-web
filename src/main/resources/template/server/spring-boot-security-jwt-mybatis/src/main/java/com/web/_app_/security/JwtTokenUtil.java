package com.web.system.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class JwtTokenUtil {

    public static String getHeader() {
        return "Authorization";
    }

    public static String getTokenPrefix() {
        return "token ";
    }

    private static String secret = "123456";

    public static String createToken(String payload) {
        Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(secret), SignatureAlgorithm.HS256.getJcaName());
        JwtBuilder builder = Jwts.builder()
//                .setHeaderParam("typ", "JWT")
//                .setHeaderParam("alg", SignatureAlgorithm.HS256.getValue())
                .signWith(SignatureAlgorithm.HS256, signingKey)
                .setPayload(payload);
        return builder.compact();
    }

    public static Jwt<Header, Claims> parse(String token) {
        Key signingKey = new SecretKeySpec(DatatypeConverter.parseBase64Binary(secret), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.parser().setSigningKey(signingKey).parse(token);
    }

    public static Header getHeader(String token) {
        return parse(token).getHeader();
    }

    public static Claims getBody(String token) {
        return parse(token).getBody();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        Claims body = getBody(token);
        if (null != body) {
            String username = (String) body.get("username");
            List authorities = body.get("authorities", List.class);
            return new UsernamePasswordAuthenticationToken(username, null, getAuthorities(authorities));
        }
        return null;
    }

    public static Collection<GrantedAuthority> getAuthorities(List<Map<String, String>> authorities) {
        if (null != authorities) {
            Collection<GrantedAuthority> collection = new HashSet<>();
            for (Map<String, String> authority : authorities) {
                collection.add(new SimpleGrantedAuthority(authority.get("authority")));
            }
            return collection;
        }
        return null;
    }

    public static String getUsername(String token) {
        return (String) getBody(token).get("name");
    }
}
