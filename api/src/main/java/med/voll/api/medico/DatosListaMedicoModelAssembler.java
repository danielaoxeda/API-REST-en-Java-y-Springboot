package med.voll.api.medico;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class DatosListaMedicoModelAssembler implements RepresentationModelAssembler<DatosListaMedico, EntityModel<DatosListaMedico>> {

    @Override
    public EntityModel<DatosListaMedico> toModel(DatosListaMedico datosListaMedico) {
        return EntityModel.of(datosListaMedico);
    }
}