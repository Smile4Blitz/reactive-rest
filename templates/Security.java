@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http.csrf().disable()
                   .authorizeExchange()
                   .pathMatchers("/api/v1/admin/**").hasRole("ADMIN")
                   .anyExchange().authenticated()
                   .and().build();
    }
}
