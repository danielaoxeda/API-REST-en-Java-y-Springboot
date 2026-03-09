package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistrosPaciente(
        @NotBlank(message = "Nombre es obligatorio")
        String nombre,

        @NotBlank(message = "Email es obligatorio")
        @Email(message = "Formato de email es inválido")
        String email,

        @NotBlank(message = "Teléfono es obligatorio")
        String telefono,

        @NotBlank(message = "Documento es obligatorio")
        @Pattern(regexp = "\\d{8}" , message = "Formato do Documento es inválido" )
        String documento,

        @NotNull(message = "Datos de dirección son obligatorios")
        @Valid DatosDireccion direccion) {

}
