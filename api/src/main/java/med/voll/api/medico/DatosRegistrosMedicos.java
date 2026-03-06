package med.voll.api.medico;

import med.voll.api.direccion.DatosDireccion;

public record DatosRegistrosMedicos(
        String nombre,
        String email,
        String documento,
        Especialidad especialidad,
        DatosDireccion direccion
) {


}
