package com.odontologia.ClinicaOdontologica.security;

import com.odontologia.ClinicaOdontologica.entity.Usuario;
import com.odontologia.ClinicaOdontologica.entity.UsuarioRole;
import com.odontologia.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosInicialesSecurity implements ApplicationRunner {
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder cifrador= new BCryptPasswordEncoder();

        // Creo un usuario ADMIN como si fuese real
        String passAdminSinCifrar = "admin";
        String passAdminCifrado = cifrador.encode(passAdminSinCifrar);
        Usuario usuarioAdmin = new Usuario("admin","admin",
                "admin@admin.com", passAdminCifrado, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);

        // Creo un usuario USER como si fuese real
        String passUserSinCifrar = "digital";
        String passUserCifrado = cifrador.encode(passUserSinCifrar);
        Usuario usuarioUser = new Usuario("Lionel","lio10",
                "lionel@messi.com", passUserCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuarioUser);
    }

}
