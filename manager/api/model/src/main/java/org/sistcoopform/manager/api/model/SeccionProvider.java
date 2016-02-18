package org.sistcoopform.manager.api.model;

import java.util.List;

import javax.ejb.Local;

import org.sistcoopform.manager.api.model.provider.Provider;

@Local
public interface SeccionProvider extends Provider {

    SeccionModel findById(String id);

    SeccionModel create(FormModel formulario, String titulo, String descripcion, int numero);

    boolean remove(SeccionModel seccion);

    List<SeccionModel> getAll(FormModel formulario);

    List<SeccionModel> getAll(FormModel formulario, int firstResult, int maxResults);

}
