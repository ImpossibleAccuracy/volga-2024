package com.simp.service.shared.data.config.feign;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simp.service.shared.domain.exception.ServiceException;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

@RequiredArgsConstructor
public class PropagateErrorDecoder implements ErrorDecoder {
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    public Exception decode(String methodKey, Response response) {
        var status = response.status();

        var rawData = new String(response.body().asInputStream().readAllBytes());
        var data = objectMapper.readValue(rawData, ErrorResponse.class);

        return new ServiceException(data.message, status);
    }

    private record ErrorResponse(
            @JsonProperty("message")
            String message
    ) {
    }
}
