package org.sistcoopform.manager.api.model;

import java.util.List;

import javax.ejb.Local;

import org.sistcoopform.manager.api.model.enums.TipoPreguntaNumero;
import org.sistcoopform.manager.api.model.enums.TipoPreguntaSeleccion;
import org.sistcoopform.manager.api.model.enums.TipoPreguntaTexto;
import org.sistcoopform.manager.api.model.enums.TipoPreguntaTiempo;
import org.sistcoopform.manager.api.model.provider.Provider;

@Local
public interface QuestionProvider extends Provider {

	PreguntaTiempoModel createTiempo(SectionModel seccion, String titulo, String descripcion, int numero,
			TipoPreguntaTiempo tipoPregunta, boolean obligatorio);

	PreguntaTextoModel createTexto(SectionModel seccion, String titulo, String descripcion, int numero,
			TipoPreguntaTexto tipoPregunta, boolean obligatorio);

	PreguntaNumericaModel createNumero(SectionModel seccion, String titulo, String descripcion, int numero,
			TipoPreguntaNumero tipoPregunta, boolean obligatorio);

	PreguntaSeleccionModel createSeleccion(SectionModel seccion, String titulo, String descripcion, int numero,
			TipoPreguntaSeleccion tipoPregunta, boolean obligatorio);

	OpcionSeleccionModel createOpcionSeleccion(PreguntaSeleccionModel preguntaSeleccion, String denominacion,
			int numero, boolean editable);

	PreguntaEscalaLinealModel createEscalaLineal(SectionModel seccion, String titulo, String descripcion, int numero,
			String etiqueta1, String etiqueta2, int desde, int hasta, boolean obligatorio);

	PreguntaCuadriculaOpcionesModel createCuadricula(SectionModel seccion, String titulo, String descripcion,
			int numero, boolean requiereRespuestaPorFila);

	FilaCuadriculaModel createFilaCuadricula(PreguntaCuadriculaOpcionesModel preguntaCuadricula, String denominacion,
			int numero, boolean editable);

	ColumnaCuadriculaModel createColumnaCuadricula(PreguntaCuadriculaOpcionesModel preguntaCuadricula,
			String denominacion, int numero, boolean editable);

	PreguntaModel findById(String id);

	boolean remove(PreguntaModel pregunta);

	List<PreguntaModel> getAll(SectionModel seccion);

	List<PreguntaModel> getAll(SectionModel seccion, int firstResult, int maxResults);

}
