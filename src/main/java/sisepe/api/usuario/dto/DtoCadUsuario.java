package sisepe.api.usuario.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DtoCadUsuario(
        @NotBlank
        @Pattern(regexp = "\\d{11}") String cpf,

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email
) {
}
