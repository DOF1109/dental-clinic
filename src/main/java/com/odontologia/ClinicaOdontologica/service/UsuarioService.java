package com.odontologia.ClinicaOdontologica.service;

import com.odontologia.ClinicaOdontologica.entity.Usuario;
import com.odontologia.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(username);
        if(usuarioBuscado.isPresent())
            return usuarioBuscado.get();
        throw new UsernameNotFoundException("No se encontró el usuario");
    }
}
