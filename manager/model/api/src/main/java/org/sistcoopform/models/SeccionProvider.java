package org.sistcoopform.models;

import java.util.List;

import javax.ejb.Local;

import org.sistcoopform.provider.Provider;

@Local
public interface SeccionProvider extends Provider {

    SeccionModel findById(String id);

    SeccionModel create(FormularioModel formulario, String titulo, String descripcion, int orden);

    boolean remove(SeccionModel seccion);

    List<FormularioModel> getAll(FormularioModel formulario);

    List<FormularioModel> getAll(FormularioModel formulario, int firstResult, int maxResults);

}
