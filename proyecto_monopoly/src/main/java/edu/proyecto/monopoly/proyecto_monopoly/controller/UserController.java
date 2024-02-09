package edu.proyecto.monopoly.proyecto_monopoly.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import edu.proyecto.monopoly.proyecto_monopoly.model.db.UsuarioDb;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.PaginaDto;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.UserList;
import edu.proyecto.monopoly.proyecto_monopoly.model.dto.UsuarioUpdate;
import edu.proyecto.monopoly.proyecto_monopoly.srv.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    private final UsuarioService userService;

    public UserController(UsuarioService userService) {
        this.userService = userService;
    }

    @GetMapping("/usuario/{nickname}")
    public ResponseEntity<UsuarioDb> getUsuarioByNickname(@PathVariable("nickname") String nickname){
        Optional<UsuarioDb> userInfo = userService.getByNickname(nickname);
        if(userInfo.isPresent()){
            return ResponseEntity.ok().body(userInfo.get());
        }else{
            throw new RuntimeException("User not found with nickname::" + nickname);
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<Map<String,Object>> getAllUsuarios(
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "3") int size,
        @RequestParam(defaultValue = "id,asc") String [] sort
    ){
        try {
            List<Order> criteriosOrdenacion = new ArrayList<Order>();
            Pageable paging;

            if(sort[0].contains(",")){
                for(String criterioOrdenacion : sort){
                    String [] orden = criterioOrdenacion.split(",");
                    if(orden.length > 1){
                        criteriosOrdenacion.add(new Order(Direction.fromString(orden[1]), orden[0]));
                    }else{
                        criteriosOrdenacion.add(new Order(Direction.fromString("asc"), orden[0]));
                    }
                }
            }else{
                criteriosOrdenacion.add(new Order(Direction.fromString(sort[1]), sort[0]));
            }
            Sort sorts = Sort.by(criteriosOrdenacion);
            if(page > 0){
                paging = PageRequest.of(page - 1, size, sorts);
            }else{
                paging = PageRequest.of(page, size, sorts);

            }
            PaginaDto<UserList> paginasUsersList = userService.findAll(paging);
            List<UserList> equipos = paginasUsersList.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("data", equipos);
            response.put("currentPage", paginasUsersList.getNumber());
            response.put("pageSize", paginasUsersList.getSize());
            response.put("totalItems", paginasUsersList.getTotalElements());
            response.put("totalPages", paginasUsersList.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);




        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("usuario/update/{nickname}")
    public ResponseEntity<?> updateAlumnoEdit(
            @PathVariable(value = "nickname") String nickname,
            @Valid @RequestBody UsuarioUpdate usuarioUpdate) throws RuntimeException {
                Optional<UsuarioUpdate> originalUsuarioUpdate = userService.getUsuarioUpdateByNickname(nickname);

                if (originalUsuarioUpdate.isPresent()) {
                    UsuarioUpdate updatedUsuarioUpdateResult = userService.update(usuarioUpdate)
                    .orElseThrow(() -> new RuntimeException("Error al actualizar el AlumnoEdit"));
    
                    return ResponseEntity.ok(updatedUsuarioUpdateResult);
                } else {
                    String mensaje = "No se encontró ningún usuario con el nickname especificado: " + nickname;
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensaje);

                }
            }


    
}
