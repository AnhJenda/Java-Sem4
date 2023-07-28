package com.example.spring_school.utils;

import com.example.spring_school.entity.User;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {
    public static final String SECRET_KEY = "11111111111111111111111111111111111";

    public static final String USERNAME = "username";

    public static final String EMAIL = "email";

    public static final String TEL = "tel";
    public static final int expireTime = 86400000;
    public static final int refreshExpireTime = 86400000;
    public static String genToken(User user) {
        String token = null;
        try {
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim(USERNAME, user.getUsername());
            builder.claim(EMAIL, user.getEmail());
            builder.claim(TEL, user.getTel());
            builder.expirationTime(new Date(System.currentTimeMillis() + expireTime));
            JWTClaimsSet claimsSet = builder.build(); // đóng gói cho payload token
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            token = signedJWT.serialize();
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    public static String genRefreshToken(User user) {
        String token = null;
        try {
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();
            builder.claim(USERNAME, user.getUsername());
            builder.claim(EMAIL, user.getEmail());
            builder.claim(TEL, user.getTel());
            builder.expirationTime(new Date(System.currentTimeMillis() + refreshExpireTime));
            JWTClaimsSet claimsSet = builder.build(); // đóng gói cho payload token
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);
            token = signedJWT.serialize();
            return token;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;
    }

    private JWTClaimsSet getClaimFromToken(String token) {
        JWTClaimsSet claimsSet = null;
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(generateShareSecret());
            if (signedJWT.verify(verifier)) {
                claimsSet = signedJWT.getJWTClaimsSet();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claimsSet;
    }

    public boolean isTokenExpire(String token) {
        Date expireTime = getExpireDateFormToken(token);
        return expireTime.before(new Date());
    }

    public String getUsernameFromToken(String token) {
        String username = null;
        JWTClaimsSet jwtClaimsSet = getClaimFromToken(token);
        try {
            username = jwtClaimsSet.getStringClaim(USERNAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return username;
    }

    public Date getExpireDateFormToken(String token) {
        Date expireDate = null;
        JWTClaimsSet claimsSet = getClaimFromToken(token);
        return claimsSet.getExpirationTime();
    }


    private static byte[] generateShareSecret() {
        // 256 bit = 32 byte
        byte[] sharedSecret = new byte[32];
        sharedSecret = SECRET_KEY.getBytes();
        return sharedSecret;
    }

}
