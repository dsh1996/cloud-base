package com.server.authserver.shiro;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
@Slf4j
@Component
@ConfigurationProperties(prefix = "shiro.config")
public class ShiroProperties {
    public ShiroProperties() {
      log.info("------------reading shiro filter config... ------------");
    }
    private Map<String, String> filterPath;

}
