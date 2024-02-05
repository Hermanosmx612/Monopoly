package edu.proyecto.monopoly.proyecto_monopoly.srv.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import edu.proyecto.monopoly.proyecto_monopoly.model.clases.UsuarioPrincipal;
import edu.proyecto.monopoly.proyecto_monopoly.model.db.UsuarioDb;
import edu.proyecto.monopoly.proyecto_monopoly.srv.UsuarioService;



@Service
public class UserDetailsServiceImpl implements UserDetailsService{

    @Autowired
    UsuarioService usuarioService;
    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {
        UsuarioDb usuario = usuarioService.getByNickname(nickname).get();
        return UsuarioPrincipal.build(usuario);
    }
    
}
