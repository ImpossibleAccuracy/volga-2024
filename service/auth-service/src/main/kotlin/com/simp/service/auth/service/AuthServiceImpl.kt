package com.simp.service.auth.service

import com.simp.service.shared.domain.service.AuthService
import org.springframework.stereotype.Service

@Service
class AuthServiceImpl : AuthService {
    override fun getGreeting(name: String): String =
        "Hello, $name [from auth-service]"
}