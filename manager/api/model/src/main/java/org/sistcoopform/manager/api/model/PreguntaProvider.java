package org.sistcoopform.manager.api.model;

import java.util.List;

import javax.ejb.Local;

import org.sistcoopform.manager.api.model.enums.TipoPreguntaNumero;
import org.sistcoopform.manager.api.model.enums.TipoPreguntaSeleccion;
import org.sistcoopform.manager.api.model.enums.TipoPreguntaTexto;
import org.sistcoopform.manager.api.model.enums.TipoPreguntaTiempo;
import org.sistcoopform.manager.api.model.provider.Provider;

@Local
public interface PreguntaProvider extends Provider {

    PreguntaTiempoModel createTiempo(SeccionModel seccion, String titulo, String descripcion, int numero,
            TipoPreguntaTiempo tipoPregunta, boolean obligatorio);

    PreguntaTextoModel createTexto(SeccionModel seccion, String titulo, String descripcion, int numero,
            TipoPreguntaTexto tipoPregunta, boolean obligatorio);

    PreguntaNumericaModel createNumero(SeccionModel seccion, String titulo, String descripcion, int numero,
            TipoPreguntaNumero tipoPregunta, boolean obligatorio);

    PreguntaSeleccionModel createSeleccion(SeccionModel seccion, String titulo, String descripcion,
            int numero, TipoPreguntaSeleccion tipoPregunta, boolean obligatorio);

    OpcionSeleccionModel createOpcionSeleccion(PreguntaSeleccionModel preguntaSeleccion, String denominacion,
            int numero, boolean editable);

    PreguntaEscalaLinealModel createEscalaLineal(SeccionModel seccion, String titulo, String descripcion,
            int numero, String etiqueta1, String etiqueta2, int desde, int hasta, boolean obligatorio);

    PreguntaCuadriculaOpcionesModel createCuadricula(SeccionModel seccion, String titulo, String descripcion,
            int numero, boolean requiereRespuestaPorFila);

    FilaCuadriculaModel createFilaCuadricula(PreguntaCuadriculaOpcionesModel preguntaCuadricula,
            String denominacion, int numero, boolean editable);

    ColumnaCuadriculaModel createColumnaCuadricula(PreguntaCuadriculaOpcionesModel preguntaCuadricula,
            String denominacion, int numero, boolean editable);

    PreguntaModel findById(String id);

    boolean remove(PreguntaModel pregunta);

    List<PreguntaModel> getAll(SeccionModel seccion);

    List<PreguntaModel> getAll(SeccionModel seccion, int firstResult, int maxResults);

}
