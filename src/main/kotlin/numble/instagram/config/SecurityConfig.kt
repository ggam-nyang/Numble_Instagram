package numble.instagram.config

import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.WebSecurityConfigurer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
//@EnableMethodSecurity
@Configuration
class SecurityConfig(
//    private val tokenProvider: TokenProvider,
//    private val corsFilter: CorsFilter,
//    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
//    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            // token을 사용하는 방식이기 때문에 csrf를 disable합니다.
//            .csrf().disable()
//
//            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter::class.java)
//
//            .exceptionHandling()
//            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
//            .accessDeniedHandler(jwtAccessDeniedHandler)

            // h2-console ??
//            .and()
//            .headers()
//            .frameOptions()
//            .sameOrigin()

            // 세션을 사용하지 않기 때문에 STATELESS로 설정
//            .and()
//            .sessionManagement()
//            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // 인증 request 설정 (whiteList 설정)
//            .and()
            .authorizeHttpRequests()
            .requestMatchers("/api/hello", "/api/authenticate", "/api/signup").permitAll() // WhiteList
            .requestMatchers(PathRequest.toH2Console()).permitAll()
//            .anyRequest().permitAll()
            .anyRequest().authenticated() // 나머지 API 인증 필요

//            .and()
//            .apply(JwtSecurityConfig(tokenProvider))
        return httpSecurity.build()
    }
}