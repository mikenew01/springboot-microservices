package io.mk.properties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt.config")
@Data
public class JwtConfiguration {

    @NestedConfigurationProperty private Header header = new Header();
    private String loginUrl = "/login/**";
    private Integer expiration = 3600;
    private String privateKey = "ija2tL0ROKfF0Eae5CCwyPdqKnXJAgkz";
    private String type = "encrypted";

    @Data @AllArgsConstructor @NoArgsConstructor
    public static class Header {
        private String name = "Authorization";
        private String prefix = "Bearer ";
    }
}
