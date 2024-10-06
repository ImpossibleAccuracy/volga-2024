package com.simp.service.shared.data.clients.feign;

import com.simp.service.shared.contants.AuthConstants;
import com.simp.service.shared.domain.properties.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactivefeign.client.ReactiveHttpRequestInterceptor;
import reactivefeign.client.ReactiveHttpRequestInterceptors;
import reactivefeign.utils.Pair;

import java.util.List;

@Configuration
public class FeignInterceptor {
//    private static <T> ReactiveFeign.Builder<T> createBuilder() {
//        ReactiveFeign.Builder<T> builder = WebReactiveFeign.builder();
//
//        return builder
//                .addRequestInterceptor(ReactiveHttpRequestInterceptors.addHeader("X-Auth-Name", "SECRET_SERVICE_NAME"))
//                .addRequestInterceptor(ReactiveHttpRequestInterceptors.addHeader("X-Auth-Pass", "SECRET_SERVICE_PASSWORD"));
//    }
//
//    public static <T> T build(Class<T> service, String url) {
//        ReactiveFeign.Builder<T> builder = createBuilder();
//
//        return builder
//                .addRequestInterceptor(ReactiveHttpRequestInterceptors.addHeader("X-Auth-Name", "SECRET_SERVICE_NAME"))
//                .addRequestInterceptor(ReactiveHttpRequestInterceptors.addHeader("X-Auth-Pass", "SECRET_SERVICE_PASSWORD"))
//                .target(service, url);
//    }

    @Bean
    public ReactiveHttpRequestInterceptor serviceIdentityInterceptor(SecurityProperties securityProperties) {
        var user = securityProperties.serviceCredentials.stream().findAny().get();

        // TODO: check works
        return ReactiveHttpRequestInterceptors.addHeaders(
                List.of(
                        new Pair<>(AuthConstants.SERVICE_ROLE_USERNAME, user.name()),
                        new Pair<>(AuthConstants.SERVICE_ROLE_PASSWORD, user.password())
                )
        );
    }
}
