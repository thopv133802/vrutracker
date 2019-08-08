package com.thopham.projects.research.apiservice.services

import com.google.gson.Gson
import com.thopham.projects.research.apiservice.models.UserModel
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.util.*

data class Token(
        val token: String,
        val expiration: Long
)

@Component
class TokenGenerator {
    companion object {
        private const val SECRET_KEY = "fa15cc37540256ba25cebc0ca03f845c"
        private const val EXPIRED_TIME = 24 * 60 * 60 * 1000
    }

    fun generate(user: UserModel): Token {
        val expiration = System.currentTimeMillis() + EXPIRED_TIME
        val token = Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY.toByteArray())
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(expiration))
                .setSubject(Gson().toJson(user))
                .compact()
        return Token("Bearer $token", expiration)
    }

    fun resolve(token: String): UserModel {
        val claims = Jwts.parser()
                .setSigningKey(SECRET_KEY.toByteArray())
                .parseClaimsJws(token)
                .body
        val user = Gson().fromJson(claims.subject, UserModel::class.java)
        println("User Resolved: $user")
        return user
    }
}