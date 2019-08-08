package com.thopham.projects.research.apiservice.controllers

import com.thopham.projects.research.apiservice.core.Response
import com.thopham.projects.research.apiservice.services.LoginResult
import com.thopham.projects.research.apiservice.services.RegisterResult
import com.thopham.projects.research.apiservice.services.UserServiceImpl
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.RuntimeException

data class LoginRequest(
        val username: String,
        val password: String
)
data class RegisterRequest(
        val username: String,
        val password: String,
        val email: String,
        val phone: String
)

@RestController
@RequestMapping("users")
class UserController(private val userService: UserServiceImpl) {
    @PostMapping("greetings")
    fun greetings() =  "Hello World."
    @PostMapping("login")
    fun login(@RequestBody request: LoginRequest): Response<LoginResult> {
        return try{
            val payload = userService.login(request.username, request.password)
            Response(true, "Đăng nhập thành công", payload)
        }
        catch(exception: Exception){
            Response(false, exception.message ?: "Thất bại trong quá trình đăng nhập.", null)
//            throw RuntimeException(exception)
        }
    }
    @PostMapping("register")
    fun register(@RequestBody request: RegisterRequest): Response<RegisterResult>{
        return try{
            val payload = userService.register(request.username, request.password, request.email, request.phone)
            Response(true, "Đăng ký thành công", payload)
        }
        catch(exception: Exception){
            Response(false, exception.message ?: "Thất bại trong quá trình đăng ký.", null)
//            throw RuntimeException(exception)
        }
    }
}