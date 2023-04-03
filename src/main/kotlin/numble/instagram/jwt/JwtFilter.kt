package numble.instagram.jwt

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import java.io.IOException

class JwtFilter(private val tokenProvider: TokenProvider) : GenericFilterBean() {
    private val logger = LoggerFactory.getLogger(JwtFilter::class.java)

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilter(servletRequest: ServletRequest, servletResponse: ServletResponse, filterChain: FilterChain) {
        val httpServletRequest = servletRequest as HttpServletRequest
        val jwt = resolveToken(httpServletRequest)
        val requestURI = httpServletRequest.requestURI
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
            val authentication = tokenProvider.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
            logger.debug("Security Context에 '${authentication.name}' 인증 정보를 저장했습니다, uri: $requestURI")
        } else {
            logger.debug("유효한 JWT 토큰이 없습니다, uri: $requestURI")
        }

        filterChain.doFilter(servletRequest, servletResponse)
    }

    private fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7)
        } else null
    }
}