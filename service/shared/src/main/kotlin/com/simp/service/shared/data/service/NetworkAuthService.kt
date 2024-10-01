package com.simp.service.shared.data.service

import com.simp.service.shared.contants.Services
import com.simp.service.shared.data.clients.AuthClient
import com.simp.service.shared.domain.service.AuthService
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.stereotype.Service

@Service
@ConditionalOnProperty(Services.AUTH)
class NetworkAuthService(
    private val authClient: AuthClient,
) : AuthService {
    override fun getGreeting(name: String): String =
        authClient.greetings(name).greeting
}