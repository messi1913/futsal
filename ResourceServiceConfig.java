package com.samsung.mes4.system.oauth2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;
import java.nio.file.Files;
/**
 * <PRE>
 * Module Name :
 * Modification History
 * 수정일             수정자           수정내용
 * -------------------------------------------------
 * 2018-08-05          sangmessi          최초 생성
 * </PRE>
 *
 * @author : sangmessi
 * @version : 1.0
 * Copyright (C) 2018 by SAMSUNG SDS. All right reserved.
 * @date : J2018-08-05
 */
@Configuration
@EnableResourceServer
public class ResourceServiceConfig extends ResourceServerConfigurerAdapter {
	@Value("${resource.id:spring-boot-application}")
	private String resourceId;

	@Value("${security.oauth2.jwt.publicKey}")
	private String publicKey;

	@Value("classpath:publicKey.txt")
	private Resource pkResouce;

	@Bean
	public String getPublicKey(){
		String a = null;
		try {
			a = new String(Files.readAllBytes(pkResouce.getFile().toPath()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return a;
	}

	@Bean
	public TokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		System.err.println(getPublicKey());
		converter.setVerifierKey(getPublicKey());
		return converter;
	}

	@Bean
	@Primary
	public DefaultTokenServices tokenService() {
		DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
		defaultTokenServices.setTokenStore(tokenStore());
		defaultTokenServices.setSupportRefreshToken(true);
		return defaultTokenServices;
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.resourceId(resourceId);
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/nexacro.do").hasRole("USER");
	}

}
