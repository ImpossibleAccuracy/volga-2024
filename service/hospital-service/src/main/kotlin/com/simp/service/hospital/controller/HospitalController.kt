package com.simp.service.hospital.controller

import com.simp.service.shared.domain.payload.GreetingsResponse
import com.simp.service.shared.domain.service.HospitalService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/greetings")
class HospitalController(
    private val hospitalService: HospitalService
) {
    @GetMapping
    fun getGreetings(@RequestParam("name") name: String): ResponseEntity<GreetingsResponse> =
        hospitalService.getGreeting(name).let {
            val data = GreetingsResponse(it)

            ResponseEntity.ok(data)
        }
}