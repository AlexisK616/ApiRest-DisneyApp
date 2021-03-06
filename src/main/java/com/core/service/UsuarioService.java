package com.core.service;

import com.core.domain.Usuario;
import com.core.exceptions.BadRequestException;
import com.core.repository.IUsuarioRepository;
import java.util.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UsuarioService implements UserDetailsService {

    private IUsuarioRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String nombre) throws UsernameNotFoundException {
        Usuario usuario = userRepository.findByNombre(nombre);
        if (nombre == null) {
            throw new UsernameNotFoundException("Nombre de usuario no encontrado");
        }
        User user = this.userBuilder(usuario);
        return user;
    }

    public User userBuilder(Usuario usuario) {
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean acountNonLocked = true;

        List<GrantedAuthority> authorithies = new ArrayList();
        authorithies.add(new SimpleGrantedAuthority(usuario.getRole()));

        return new User(usuario.getNombre(), usuario.getContraseña(), enabled, accountNonExpired, credentialsNonExpired, acountNonLocked, authorithies);
    }
    
    @Transactional
    public Usuario registroUsuario(Usuario usuario) {
        if (!userRepository.existsByEmail(usuario.getEmail())) {
            usuario.setContraseña(BCrypt.hashpw(usuario.getContraseña(), BCrypt.gensalt()));
            return userRepository.save(usuario);
        }else{
            throw new BadRequestException("User already exists");
        }  
    }
}
