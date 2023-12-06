package com.odontologia.ClinicaOdontologica.security;

import com.odontologia.ClinicaOdontologica.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider= new DaoAuthenticationProvider();
        provider.setPasswordEncoder(bCryptPasswordEncoder);
        provider.setUserDetailsService(usuarioService);
        return provider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
     http
             .csrf().disable()
             .authorizeRequests()
             .antMatchers("/get_odontologos.html").hasAnyRole("USER", "ADMIN")
             .antMatchers("/post_odontologos.html").hasRole("ADMIN")
             .antMatchers("/h2-console/**").permitAll()  // Permitir todas las solicitudes a /h2-console/**
             .anyRequest()
             .authenticated()
             .and()
             .formLogin()
                //.loginPage("/login.html") // Especifica la URL de tu formulario de inicio de sesión personalizado
                //.permitAll() // Permite que todos puedan acceder a la página de inicio de sesión
             .and()
             .logout()
                 .logoutUrl("/logout") // URL para enviar la solicitud de cierre de sesión
                 .logoutSuccessUrl("/login") // Redirección después de cerrar sesión con éxito
                 .invalidateHttpSession(true) // Invalida la sesión HTTP existente
                 .deleteCookies("JSESSIONID") // Elimina cookies específicas al cerrar sesión
            .and()
                .headers().frameOptions().disable();  // Permitir que la consola de H2 se muestre en un marco
    }

}
