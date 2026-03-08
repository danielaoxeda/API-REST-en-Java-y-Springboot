package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.medico.IMedicoRepository;

public class MedicoActivoValidacion {

    private IMedicoRepository medicoRepository;

    public void reservar(DatosReservaConsulta datos) {
        //eleccion del medico opcional
        if (datos.idMedico() == null) {
            return;
        }

        var medicoEstaActivo = medicoRepository.findActivoById(datos.idMedico());
        if (!medicoEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con médico excluido");
        }
    }
}
