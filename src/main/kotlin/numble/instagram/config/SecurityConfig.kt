package numble.instagram.config

import numble.instagram.jwt.JwtAccessDeniedHandler
import numble.instagram.jwt.JwtAuthenticationEntryPoint
import numble.instagram.jwt.JwtSecurityConfig
import numble.instagram.jwt.TokenProvider
import org.springframework.boot.autoconfigure.security.servlet.PathRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.CorsFilter

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
class SecurityConfig(
    private val tokenProvider: TokenProvider,
    private val corsFilter: CorsFilter,
    private val jwtAuthenticationEntryPoint: JwtAuthenticationEntryPoint,
    private val jwtAccessDeniedHandler: JwtAccessDeniedHandler
) {
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
        httpSecurity
            // disable csrf
            .csrf().disable()

            .addFilterBefore(corsFilter, UsernamePasswordAuthenticationFilter::class.java)

            .exceptionHandling()
            .authenticationEntryPoint(jwtAuthenticationEntryPoint)
            .accessDeniedHandler(jwtAccessDeniedHandler)

            // enable h2-console
            .and()
            .headers()
            .frameOptions()
            .sameOrigin()

            // not using Session -> STATELESS
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

            // config matchers
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/api/hello", "/api/authenticate", "/api/signup").permitAll()
            .requestMatchers(PathRequest.toH2Console()).permitAll()
            .anyRequest().authenticated()

            .and()
            .apply(JwtSecurityConfig(tokenProvider))
        return httpSecurity.build()
    }
}