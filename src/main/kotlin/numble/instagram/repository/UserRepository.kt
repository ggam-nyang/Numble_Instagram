package numble.instagram.repository

import numble.instagram.entity.User
import org.springframework.data.jpa.repository.EntityGraph
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    @EntityGraph(attributePaths = ["authorities"])
    fun findOneWithAuthoritiesByNickName(nickName: String): User?
}