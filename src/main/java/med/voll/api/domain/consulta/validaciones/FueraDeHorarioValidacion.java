package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
@Component
public class FueraDeHorarioValidacion implements ValidadorDeConsultas {
    public void validar(DatosReservaConsulta datos) {
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeApertura = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierre = fechaConsulta.getHour() > 18;

        if (domingo | horarioAntesDeApertura | horarioDespuesDeCierre) {
            throw new ValidacionException("Horario seleccionado fuera de atendimiento de la clínica");
        }
    }
}
