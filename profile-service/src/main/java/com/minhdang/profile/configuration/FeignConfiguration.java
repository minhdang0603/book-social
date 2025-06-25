package com.minhdang.profile.configuration;

import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public Encoder multipartEncoder() {
        // Use the Spring FormEncoder to handle multipart requests
        return new SpringFormEncoder();
    }
}
