package com.simp.service.auth.controller

import com.simp.service.shared.domain.payload.GreetingsResponse
import com.simp.service.shared.domain.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greetings")
class AuthController(
    private val authService: AuthService
) {
    @GetMapping
    fun getGreetings(@RequestParam("name") name: String): ResponseEntity<GreetingsResponse> =
        authService.getGreeting(name).let {
            val data = GreetingsResponse(it)

            ResponseEntity.ok(data)
        }
}