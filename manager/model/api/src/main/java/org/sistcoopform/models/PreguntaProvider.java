package org.sistcoopform.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoopform.provider.Provider;

@Local
public interface PreguntaProvider extends Provider {

    PreguntaModel findById(String id);

    PreguntaModel create(SeccionModel seccion, String titulo, String descripcion, int numero);

    boolean remove(PreguntaModel pregunta);

    List<PreguntaModel> getAll(SeccionModel seccion);

    List<PreguntaModel> getAll(SeccionModel seccion, int firstResult, int maxResults);

}
