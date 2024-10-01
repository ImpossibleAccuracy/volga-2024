package com.simp.service.hospital.service

import com.simp.service.shared.domain.service.AuthService
import com.simp.service.shared.domain.service.HospitalService
import org.springframework.stereotype.Service

@Service
class HospitalServiceImpl(
    private val authService: AuthService,
) : HospitalService {
    override fun getGreeting(name: String): String = when {
        name.startsWith("auth") -> authService.getGreeting(name.substring(4))
        else -> "Hello, $name [from hospital]"
    }
}