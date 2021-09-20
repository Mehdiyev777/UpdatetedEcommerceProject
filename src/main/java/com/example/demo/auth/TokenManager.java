package com.example.demo.auth;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
@Service
public class TokenManager {
    private  static final String key ="Oyundtgdfgfdgoynuyursanecommerceseseshhdfklsfklsdhjlkhklwdhlkshdsdhklsadhsadhkjghjkgdsasecret";
    private static final int expireTime=50000*60*1000;

    public String generateToken (String username){

        String token = Jwts.builder()
                .setSubject(username)
                .setIssuer("ulvi mehdiyev")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS512,key)

                .compact();
        return token;
    }

    public Boolean hasTokenValid(String token) {
        if (getUserFromToken(token) != null && hasTokenExpire(token)) {
            return true;
        }
        return false;
    }
    public String getUserFromToken(String token) {
        Claims claims = parseToken(token); //Tokenı parse etmek için yazdığımız metodumuzu çağırıyoruz.
        return claims.getSubject(); //Kullanıcı adını claimsler arasından alıyoruz.

    }
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(key) //Kullanıcıdan aldığımız tokenı parse etmek için secret keyimizi setliyoruz.
                .parseClaimsJws(token) //Parse edilecek tokenı veriyoruz bu kısım bize Jws<Claims> dönecektir.
                .getBody(); //Asıl kullanacağımız kısım olan body kısmını bu şekilde Claims nesnesi halinde almamızı sağlayacak.
    }
    public boolean hasTokenExpire(String token) {
        Claims claims = parseToken(token); //Tokenı tekrardan parse edip claimlerimizi alıyoruz.
        Date now = new Date(System.currentTimeMillis()); //Bir zaman oluşturuyoruz.
        return claims.getExpiration().after(now); //expire time'ın yukarda oluştumuş olduğumuz zamandan sonra
        // olup olmadığını kontrol ediyoruz.
    }
}
