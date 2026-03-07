package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.paciente.DatosDetallePaciente;
import med.voll.api.domain.paciente.DatosRegistrosPaciente;
import med.voll.api.domain.paciente.IPacienteRepository;
import med.voll.api.domain.paciente.Paciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteRepository pacienteRepository;

    @Transactional
    @PostMapping
    public ResponseEntity registrar(@RequestBody @Valid DatosRegistrosPaciente datos, UriComponentsBuilder uriComponentsBuilder) {

        var paciente = new Paciente(datos);
        pacienteRepository.save(paciente);

        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();

        return ResponseEntity.created(uri).body( new DatosDetallePaciente(paciente));
    }
}
