package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.paciente.IPacienteRepository;


public class PacienteActivoValidacion {

    private IPacienteRepository pacienteRepository;

    public void validar(DatosReservaConsulta datos) {
        var pacienteEstaActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if (!pacienteEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con paciente excluido");
        }
    }
}
