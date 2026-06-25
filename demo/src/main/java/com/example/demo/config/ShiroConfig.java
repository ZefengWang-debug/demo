package com.example.demo.config;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
@Configuration
public class ShiroConfig {
 @Bean public ShiroFilterChainDefinition shiroFilterChainDefinition() {
  DefaultShiroFilterChainDefinition chain = new DefaultShiroFilterChainDefinition();
  chain.addPathDefinition("/**", "anon");
  return chain;
 }
}
