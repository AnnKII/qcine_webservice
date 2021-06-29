package com.WEBservice_v1;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoder;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Key;

import java.util.Date;

@SpringBootApplication
public class Application_RUN {
	public static void main(String[] args) {
//
//		//Get secret key and save it
//		SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//		String privateKey = Encoders.BASE64.encode(key.getEncoded());
//
//		SecretKey keys = Keys.hmacShaKeyFor(Decoders.BASE64.decode(privateKey));
//		System.out.println("Private key: "+ privateKey);
//
//		// set Header and Claims
//		String jws = Jwts.builder()
//				.claim("Hello", "World")
//				.claim("1", "1")
//				.claim("2", "2")
//				.signWith(key).compact();
//		System.out.println("Encoded JWT: "+ jws);
//
//		// Verify the JWT
//		try {
//			Jwts.parserBuilder().setSigningKey(keys).build().parseClaimsJws(jws);
//			System.out.println("Can be Trusted");
//			String header = jws.substring(0, jws.indexOf('.'));
//			Base64 decoder = new Base64(true);
//			byte []decoded = decoder.decode(header);
//			System.out.println("Header: "+ header);
//			System.out.println("Decoded header: "+ String.valueOf(decoded));
//		}
//		catch (JwtException e){
//			System.out.println("Can't be Trusted");
//		}
		//Verify JWT with required value
//		try {
//			Jwts.parserBuilder().require("kid", "Kwann").setSigningKey(key).build().parseClaimsJws(jws);
//			System.out.println("Can trust");
//
//
//		} catch(InvalidClaimException ice) {
//			// the 'myfield' field was missing or did not have a 'myRequiredValue' value
//			System.out.println("Can't trusted");
//		}






		SpringApplication.run(Application_RUN.class, args);
		
	}
}
