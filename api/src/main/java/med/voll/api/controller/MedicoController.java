package med.voll.api.controller;

import med.voll.api.medico.DatosRegistrosMedicos;
import med.voll.api.medico.Medico;
import med.voll.api.medico.IMedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoRepository medicoRepository;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody DatosRegistrosMedicos datos) {
        medicoRepository.save(new Medico(datos));
    }
}
