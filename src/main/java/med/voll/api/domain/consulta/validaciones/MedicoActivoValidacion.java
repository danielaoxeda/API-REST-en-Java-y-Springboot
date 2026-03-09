package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.medico.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicoActivoValidacion implements ValidadorDeConsultas{

    @Autowired
    private IMedicoRepository medicoRepository;

    public void validar(DatosReservaConsulta datos) {
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
