/**
 * Classe para a configuração de Filtros CORS.
 *
 * Faz a confguração de Credentils, Origins, Methods, Headers
 * Permitidas nos Requests Recebidos.
 */
package br.com.ottimizza.auth.configurations;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class CORSFilterConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        // CORS 
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        // Registers the above configuratiion for all sources.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        // Registers the filter.
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);

        return new CorsFilter(source);
    }

}
