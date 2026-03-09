package med.voll.api.domain.consulta;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.validaciones.ValidadorDeConsultas;
import med.voll.api.domain.medico.IMedicoRepository;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.paciente.IPacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaConsultas {

    @Autowired
    private IConsultaRepository consultaRepository;

    @Autowired
    private IMedicoRepository medicoRepository;

    @Autowired
    private IPacienteRepository pacienteRepository;

    // Inyeccion de lista de un repositorio que implementa validadores
    @Autowired
    private List<ValidadorDeConsultas> validadores;

     public DatosDetalleConsulta reservar(DatosReservaConsulta datos) {
         if (!pacienteRepository.existsById(datos.idPaciente())) {
             throw new ValidacionException("No existe un paciente con el id informado");
         }
         if (datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) {
             throw new ValidacionException("No existe un médico con el id informado");
         }
         // Validaciones
         validadores.forEach(v -> v.validar(datos));

         var medico = elegirMedico(datos);
         if (medico== null) {
             throw new ValidacionException("No existe un médico disponible en ese horario");
         }
         var paciente = pacienteRepository.findById(datos.idPaciente()).get();

         var consulta = new Consulta(null, medico, paciente, datos.fecha(),null);

            consultaRepository.save(consulta);
            return new DatosDetalleConsulta(consulta);
     }

    private Medico elegirMedico(DatosReservaConsulta datos) {
        if( datos.idMedico() != null) {
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null) {
            throw new ValidacionException("Es necesario elegir una especialidad cuando no se elige el médico.");
        }
        return medicoRepository
                .elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha())
                .orElseThrow(() -> new ValidacionException(
                        "No hay médicos disponibles para esa fecha"));
     }

     public void cancelar(DatosCancelamientoConsulta datos) {
         if(!consultaRepository.existsById(datos.idConsulta())) {
             throw new ValidacionException("Id de la consulta informado no existe!");
         }
         var consulta = consultaRepository.getReferenceById(datos.idConsulta());
         consulta.cancelar(datos.motivo());
     }

}
