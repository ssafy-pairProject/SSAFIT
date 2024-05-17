package com.ssadolda.util;

import java.nio.charset.StandardCharsets;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.io.UnsupportedEncodingException;


import io.jsonwebtoken.security.SignatureAlgorithm;

public class JwtUtil {
	
	private static final String SALT = "ssafyIsMyBestChoicessafyIsGod100manwonThankyouThanks";
	Map<String, String> headers = new HashMap<>();
	
	public String createToken(String claimId, String data)throws UnsupportedEncodingException{
		headers.put("typ", "JWT");
		return Jwts.builder()
				.header()//헤더완료
				.add(headers)
				.and()
				.expiration(new Date(System.currentTimeMillis()+3000)) //페이로드 완료
				.signWith(Keys.hmacShaKeyFor(SALT.getBytes(StandardCharsets.UTF_8)))// 서명완료
				.compact();
	}
	// 유효성 검사
	public void valid (String token)throws Exception{
//		Jwts.parser().setSigningKey(SALT.getBytes("UTF-8")).parseClaimsJws(token);
		Jwts.parser().verifyWith(Keys.hmacShaKeyFor(SALT.getBytes(StandardCharsets.UTF_8))).build().parseSignedClaims(token);
	}

	

}
