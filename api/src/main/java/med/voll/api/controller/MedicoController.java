package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private IMedicoRepository medicoRepository;
    @Autowired
    private PagedResourcesAssembler<DatosListaMedico> pagedResourcesAssembler;
    @Autowired
    private DatosListaMedicoModelAssembler datosListaMedicoModelAssembler;

    @Transactional
    @PostMapping
    public void registrar(@RequestBody @Valid DatosRegistrosMedicos datos) {
        medicoRepository.save(new Medico(datos));
    }

    @GetMapping
    public PagedModel<EntityModel<DatosListaMedico>> listar(@PageableDefault(size = 10, sort = {"nombre"}) Pageable paginacion) {
        Page<DatosListaMedico> pagina = medicoRepository.findAllByActivoTrue(paginacion).map(DatosListaMedico::new);

        return pagedResourcesAssembler.toModel(pagina, datosListaMedicoModelAssembler);
    }

    @Transactional
    @PutMapping
    public void actualizar(@RequestBody @Valid DatosActualizacionMedico datos) {
        var medico = medicoRepository.getReferenceById(datos.id());
        medico.actualizarInformacion(datos);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.eliminar();
    }
}
