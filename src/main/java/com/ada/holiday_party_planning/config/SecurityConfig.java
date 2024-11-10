package com.ada.holiday_party_planning.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuração de segurança da aplicação usando Spring Security.
 * Esta configuração permite o acesso irrestrito a todos os endpoints
 * e usa a autenticação HTTP Basic.
 */

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura o filtro de segurança para permitir que todas as solicitações
     * sejam acessíveis sem autenticação. Usa HTTP Basic para autenticação por padrão,
     * mas, como todas as solicitações são permitidas, qualquer usuário pode acessar qualquer endpoint.
     *
     * @param http o objeto HttpSecurity que configura a segurança da aplicação
     * @return o filtro de segurança configurado (SecurityFilterChain)
     * @throws Exception em caso de erro na configuração de segurança
     */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults())
                .authorizeRequests(authorize  -> authorize.anyRequest().permitAll())
                .csrf(AbstractHttpConfigurer -> AbstractHttpConfigurer.disable())
                .build();
    }
}