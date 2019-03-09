package br.com.ottimizza.auth.configurations;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.DefaultUserApprovalHandler;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import org.springframework.http.HttpMethod;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    static final String CLIEN_ID = "devglan-client";
    static final String CLIENT_SECRET = "$2a$04$e/c1/RfsWuThaWFCrcCuJeoyvwCV0URN/6Pn9ZFlrtIWaU/vj/BfG"; // devglan-secret
    static final String GRANT_TYPE = "password";
    static final String AUTHORIZATION_CODE = "authorization_code";
    static final String REFRESH_TOKEN = "refresh_token";
    static final String IMPLICIT = "implicit";
    static final String SCOPE_READ = "read";
    static final String SCOPE_WRITE = "write";
    static final String TRUST = "trust";
    static final int ACCESS_TOKEN_VALIDITY_SECONDS = 1 * 60 * 60;
    static final int FREFRESH_TOKEN_VALIDITY_SECONDS = 6 * 60 * 60;

    @Autowired
    DataSource dataSource;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public TokenStore inMemoryTokenStore() {
        return new InMemoryTokenStore();
    }

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                // .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST) // remove this
                .tokenStore(tokenStore());
        // .userApprovalHandler(userApprovalHandler());
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .jdbc(dataSource)
                // .inMemory()
                // .withClient(CLIEN_ID)
                // .secret(CLIENT_SECRET)
                //.authorizedGrantTypes(GRANT_TYPE, AUTHORIZATION_CODE, REFRESH_TOKEN, IMPLICIT)
                //.scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
                //.accessTokenValiditySeconds(ACCESS_TOKEN_VALIDITY_SECONDS)
                //.refreshTokenValiditySeconds(FREFRESH_TOKEN_VALIDITY_SECONDS)
//                .redirectUris(
//                        "*", "http://localhost:8082/login", "http://localhost:9094/login",
//                        "http://localhost:8081/app1/login"
//                )
                // .autoApprove(true)
                // .and()
                .build();
    }

//    @Bean
//    public UserApprovalHandler userApprovalHandler() {
//        return new DefaultUserApprovalHandler();
//    }
    /* JDBC Token Storage
    @Bean
    public JdbcTokenStore inJdbcTokenStore() {
        return new JdbcTokenStore(dataSource);
    }
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
