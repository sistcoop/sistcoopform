package org.sistcoopform.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoopform.provider.Provider;

@Local
public interface SeccionProvider extends Provider {

    SeccionModel findById(String id);

    SeccionModel create(FormularioModel formulario, String titulo, String descripcion, int numero);

    boolean remove(SeccionModel seccion);

    List<SeccionModel> getAll(FormularioModel formulario);

    List<SeccionModel> getAll(FormularioModel formulario, int firstResult, int maxResults);

}
