package com.simp.service.shared.data.clients;

import com.simp.service.shared.contants.Services;
import com.simp.service.shared.domain.payload.GreetingsResponse;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authClient", url = "${" + Services.AUTH + "}")
@ConditionalOnProperty(Services.AUTH)
public interface AuthClient {
    @GetMapping("/greetings")
    GreetingsResponse greetings(@RequestParam("name") String name);
}
