package com.ssadolda.interceptor;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssadolda.util.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor   {
	private static final String Header_AUTH="access-token";
	
	
	private JwtUtil	jwtUtil;
	
	@Override
	public boolean preHandle (HttpServletRequest request , HttpServletResponse response, Object handler)
	throws Exception{
		
		//클라이언트는 서버에게 실제 요청을 보내려 했을 때 사전 요청을 보내서 서버가 현재 요청을수락할수있는상태인지 확인
		// 그게 options 요청
		if(request.getMethod().equals("OPTIONS"))
			return true; //진행
		
		String token = request.getHeader(Header_AUTH);
		
		if(token!=null) {
			jwtUtil.valid(token);
			return true;
		}
		throw new Exception("유효하지 않는 접근!");
		
		
	}
}
