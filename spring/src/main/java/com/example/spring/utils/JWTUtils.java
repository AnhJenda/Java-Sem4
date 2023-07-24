package com.example.spring.utils;

import com.example.spring.dto.LoginResponseDto;
import com.example.spring.entity.User;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSSigner;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;

/*
    @author: Dinh Quang Anh
    Date   : 7/19/2023
    Project: spring
*/
@Component
public class JWTUtils {
    public static final String SECRET_KEY = "11111111111111111111111111111111";
    public static final String USER_NAME = "username";
    public static final String EMAIL = "email";
    public static final String TEL = "0395100761";
    public static final int expireTime = 86400000;

    public static LoginResponseDto genToken(User user){
        String token = null;

        try {
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

            builder.claim("email", user.getEmail());
            builder.claim("username", user.getUsername());
            builder.claim("tel", user.getTel());
            builder.expirationTime(new Date(System.currentTimeMillis() + expireTime));
            JWTClaimsSet claimsSet = builder.build();  // đóng gói payload
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);

            token = signedJWT.serialize();
            return token;
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    public static String genRefreshToken(User user){
        String token = null;

        try {
            JWSSigner signer = new MACSigner(generateShareSecret());
            JWTClaimsSet.Builder builder = new JWTClaimsSet.Builder();

            builder.claim("email", user.getEmail());
            builder.claim("username", user.getUsername());
            builder.claim("tel", user.getTel());
            builder.expirationTime(new Date(System.currentTimeMillis() + expireTime));
            JWTClaimsSet claimsSet = builder.build();  // đóng gói payload
            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);
            signedJWT.sign(signer);

            token = signedJWT.serialize();
            return token;
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    public boolean isTokenExpire (String token){
        Date expireTime = getExpireDateFromToken(token);
        return expireTime.before(new Date());
    }

    public String getUsernameFromToken(String token){
        String username = null;
        JWTClaimsSet claimsSet = getClainFromToken(token);
        try {
            username = claimsSet.getStringClaim(USER_NAME);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return username;
    }

    public JWTClaimsSet getClainFromToken(String token){
        JWTClaimsSet claimsSet = null;
        try{
            SignedJWT sign = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(generateShareSecret());
            if (sign.verify(verifier)){
                claimsSet = sign.getJWTClaimsSet();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return claimsSet;
    }

    public Date getExpireDateFromToken(String token){

        JWTClaimsSet claimsSet = getClainFromToken(token);

        return claimsSet.getExpirationTime();
    }

    public static byte[] generateShareSecret(){
        // 256 bit
        byte[] shareSecret = new byte[32];
        shareSecret = SECRET_KEY.getBytes();

        return shareSecret;
    }
}
