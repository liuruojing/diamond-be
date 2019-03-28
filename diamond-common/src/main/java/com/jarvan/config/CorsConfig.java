package com.jarvan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * <b><code>CORSConfig</code></b>
 * <p>
 * Description.
 * <p>
 * <b>Creation Time:</b> 2018/12/29 17:56.
 *
 * @author liuruojing
 * @since nile-szcst-be 0.1.0
 */
@Configuration
public class CorsConfig {

    /**
     * Build config cors configuration.
     *
     * @return the cors configuration
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 1.allow all domain
        corsConfiguration.addAllowedOrigin("*");
        // 2.allow all Header
        corsConfiguration.addAllowedHeader("*");
        // 3.allow all method(post,get etc.）
        corsConfiguration.addAllowedMethod("*");
        return corsConfiguration;
    }

    /**
     * Cors filter.
     *
     * @return the cors filter
     */
    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }

}

