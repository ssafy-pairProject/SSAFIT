//package com.ssadolda.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//@EnableWebSecurity //스프링 시큐리티 활성화 어노테이션 
//public class SecurityConfig {
//	
//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
//		return http 
//				//stateless한 rest api를 개발할 것이므로 csrf공격에 대한 옵션은 끈다
//				// 세션을 사용하지 않으면 어차피 csrf에 대해 안전하기에 보통은 disable 해놓는다
//		.csrf(AbstractHttpConfigurer::disable)
//		//특정 url에 대한 권한 설정
//		//authorizeHttpRequests : http 요청에 대한 인증을 주로 다룸 
//		.authorizeHttpRequests((authorizeRequests) ->{
//			//requestMatchers(String) path에 대한 요청에 대해 다룬다
//			// **.authenticated() 일단 인증만 되어있으면 해당 path요청을 마음대로 가능
//			// **.hasAnyRole() 매개변수로 권한 이름을 넣어준다. 갯수는 상관없음. 해당 권한 중 하나라도 있으면 요청 가능
//			// **.hasRole() 매개변수 하나만 넣을 수 있음. 그 대상에게만 권한 부여
//			
//			authorizeRequests.requestMatchers("/user/**").authenticated();
//			
//			authorizeRequests.requestMatchers("/gym/regist")
//			// ROLE_는 붙이면 안된다. hasAnyRole을 사용할 때 자동으로 ROLE_이 붙기 떄문
//			.hasAnyRole("ADMIN","MANAGER");
//			
//			authorizeRequests.requestMatchers("/gym/modify")
//			.hasRole("ADMIN");

//			authorizeRequests.requestMatchers("/gym/delete/**")
//			.hasRole("ADMIN");
//			
//			authorizeRequests.anyRequest().permitAll();
//			
//		})
//		
//		.formLogin((formLogin)->{
//			//권한이 필요한 요청은 해당 url로 리다이렉트 
//			formLogin.loginPage("/login");
//		})
//		.build();
//		
//	}
//
//}
