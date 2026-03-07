package med.voll.api.domain.direccion;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// lombok
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Embeddable
public class Direccion {
    private String calle;
    private String numero;
    private String complemento;
    private String barrio;
    private String codigo_postal;
    private String ciudad;
    private String provincia;

    public Direccion(DatosDireccion datosDireccion) {
        this.calle = datosDireccion.calle();
        this.numero = datosDireccion.numero();
        this.complemento = datosDireccion.complemento();
        this.barrio = datosDireccion.barrio();
        this.codigo_postal = datosDireccion.codigo_postal();
        this.ciudad = datosDireccion.ciudad();
        this.provincia = datosDireccion.provincia();

    }

    public void actualizarDireccion(DatosDireccion datos) {
        if (datos.calle() != null) {
            this.calle = datos.calle();
        }
        if (datos.numero() != null) {
            this.numero = datos.numero();
        }
        if (datos.complemento() != null) {
            this.complemento = datos.complemento();
        }
        if (datos.barrio() != null) {
            this.barrio = datos.barrio();
        }
        if (datos.codigo_postal() != null) {
            this.codigo_postal = datos.codigo_postal();
        }
        if (datos.ciudad() != null) {
            this.ciudad = datos.ciudad();
        }
        if (datos.provincia() != null) {
            this.provincia = datos.provincia();
        }
    }
}


