package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.paciente.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PacienteActivoValidacion implements ValidadorDeConsultas{

    @Autowired
    private IPacienteRepository pacienteRepository;

    public void validar(DatosReservaConsulta datos) {
        var pacienteEstaActivo = pacienteRepository.findActivoById(datos.idPaciente());
        if (!pacienteEstaActivo) {
            throw new ValidacionException("Consulta no puede ser reservada con paciente excluido");
        }
    }
}
