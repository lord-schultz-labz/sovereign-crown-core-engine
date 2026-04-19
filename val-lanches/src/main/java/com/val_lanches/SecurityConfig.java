package com.val_lanches;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration // Marca como classe de configuração do Spring
public class SecurityConfig {

    // 🔐 Define as regras de segurança da aplicação
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // ❌ Desativa CSRF (facilita testes com formulário simples)
            // Em produção, o ideal é manter ativado
            .csrf(csrf -> csrf.disable())

            // 🔑 Define quem pode acessar o quê
            .authorizeHttpRequests(auth -> auth

                // ✅ Libera acesso SEM login:
                .requestMatchers(
                        "/login",          // página de login
                        "/css/**",         // arquivos CSS
                        "/js/**",          // arquivos JS
                        "/images/**"       // imagens
                ).permitAll()

                // 🔒 Qualquer outra requisição precisa estar logado
                .anyRequest().authenticated()
            )

            // 🔐 Configuração do formulário de login
            .formLogin(form -> form

                // 👇 Diz que você tem uma página customizada de login
                .loginPage("/login")

                // 👉 Para onde vai depois de logar com sucesso
                .defaultSuccessUrl("/menu", true)

                // ⚠️ ESSENCIAL: permite acessar a página de login
                .permitAll()
            )

            // 🚪 Configuração de logout
            .logout(logout -> logout

                // URL de logout (Spring já trata automaticamente)
                .logoutSuccessUrl("/login")

                // Permite logout sem restrição
                .permitAll()
            );

        return http.build();
    }

    // 👤 Cria um usuário fake em memória (pra teste)
    // Assim você consegue logar sem precisar de banco ainda
    @Bean
    public UserDetailsService userDetailsService() {

        var user = User
                .withUsername("admin")     // login
                .password("{noop}123")     // senha (noop = sem criptografia)
                .roles("USER")             // papel do usuário
                .build();

        return new InMemoryUserDetailsManager(user);
    }
}
