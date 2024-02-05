package edu.proyecto.monopoly.proyecto_monopoly.controller;

import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.RolDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.db.UsuarioDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.JwtDto;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.Loginusuario;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.Mensaje;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.NuevoUsuario;
import edu.proyecto.monopoly.proyecto_monopoly.model.enums.RolNombre;
import edu.proyecto.monopoly.proyecto_monopoly.srv.JwtService;
import edu.proyecto.monopoly.proyecto_monopoly.srv.RolService;
import edu.proyecto.monopoly.proyecto_monopoly.srv.UsuarioService;
import jakarta.validation.Valid;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@RestController
@RequestMapping("/auth")
@CrossOrigin // Permitir acceder desde cualquier origen
public class AuthController {
    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtService jwtService;

    @PostMapping("nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Datos incorrectos o email invalido"));
        }
        if(usuarioService.existsByNickname(nuevoUsuario.getNickname())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("El nickname del usuario ya existe"));
        }
        if(usuarioService.existsByEmail(nuevoUsuario.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("El email del usuario ya existe"));
        }

        UsuarioDb usuarioDb = new UsuarioDb(nuevoUsuario.getNombre(), nuevoUsuario.getNickname(), nuevoUsuario.getEmail(), passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<RolDb> rolesDb = new HashSet<>();
        rolesDb.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin")){
            rolesDb.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        }
        usuarioDb.setRoles(rolesDb);
        usuarioService.save(usuarioDb);
        return ResponseEntity.status(HttpStatus.CREATED).body(new Mensaje("Usuario creado"));
    }

    @PostMapping("login")
    public ResponseEntity<?> login (@Valid @RequestBody Loginusuario loginusuario, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Mensaje("Datos incorrectos"));
        }

        Authentication authentication = 
                authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginusuario.getNickname(), loginusuario.getPassword()));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                String jwt = jwtService.generateToken(authentication);
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
                return ResponseEntity.status(HttpStatus.OK).body(jwtDto);

    }

    
    
} 
