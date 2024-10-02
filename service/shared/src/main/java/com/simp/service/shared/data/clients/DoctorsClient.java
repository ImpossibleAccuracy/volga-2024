package com.simp.service.shared.data.clients;

import com.simp.service.shared.data.contants.Services;
import com.simp.service.shared.domain.model.Doctor;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@ConditionalOnProperty(Services.AUTH_SERVICE_KEY)
@FeignClient(name = "authClient", url = "${" + Services.AUTH_SERVICE_KEY + "}")
public interface DoctorsClient {
    @GetMapping(value = "/Doctors", consumes = MediaType.APPLICATION_JSON_VALUE)
    Flowable<Doctor> getDoctorList(
            @RequestHeader(Services.Auth.AUTH_HEADER) String token,
            @RequestParam("from") int from,
            @RequestParam("count") int count);

    @GetMapping(value = "/Doctors/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Maybe<Doctor> getDoctor(
            @RequestHeader(Services.Auth.AUTH_HEADER) String token,
            @PathVariable("id") Long id);
}
