package edu.proyecto.monopoly.proyecto_monopoly.model.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Loginusuario {
    @NotBlank
    private String nickname;
    @NotBlank
    private String password;
}
