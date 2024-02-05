package edu.proyecto.monopoly.proyecto_monopoly.srv;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.UsuarioDb;
import edu.proyecto.monopoly.proyecto_monopoly.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.NonNull;

@Service
@Transactional // Mantiene la coherencia en la BD si hay varios de accesos de escritura concurrentes
public class UsuarioService {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<UsuarioDb> getByNickname(String nickname){
        return usuarioRepository.findByNickname(nickname);
    }
    public boolean existsByNickname(String nickanme){
        return usuarioRepository.existsByNickname(nickanme);
    }

    public boolean existsByEmail(String email){
        return usuarioRepository.existsByEmail(email);
    }

    public void save(@NonNull UsuarioDb usuario){
        usuarioRepository.save(usuario);
    }
}
