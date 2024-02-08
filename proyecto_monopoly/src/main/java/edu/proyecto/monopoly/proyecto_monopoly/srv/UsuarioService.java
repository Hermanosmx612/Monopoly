package edu.proyecto.monopoly.proyecto_monopoly.srv;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.UsuarioDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.PaginaDto;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.UserList;
import edu.proyecto.monopoly.proyecto_monopoly.repository.UsuarioRepository;
import edu.proyecto.monopoly.proyecto_monopoly.srv.mapper.UsuarioMapper;
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

    public PaginaDto<UserList> findAll(Pageable pageable){
        Page<UsuarioDb> paginaUserDb = usuarioRepository.findAll(pageable);
        return new PaginaDto<UserList>(
            paginaUserDb.getNumber(),
            paginaUserDb.getSize(),
            paginaUserDb.getTotalElements(),
            paginaUserDb.getTotalPages(),
            UsuarioMapper.INSTANCE.usuarioDbToUsuarioList(paginaUserDb.getContent()),
            paginaUserDb.getSort()
        );
    }
}
