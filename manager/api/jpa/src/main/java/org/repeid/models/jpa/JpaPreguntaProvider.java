package org.repeid.models.jpa;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.repeid.models.jpa.entities.ColumnaCuadriculaEntity;
import org.repeid.models.jpa.entities.FilaCuadriculaEntity;
import org.repeid.models.jpa.entities.OpcionSeleccionEntity;
import org.repeid.models.jpa.entities.PreguntaCuadriculaOpcionesEntity;
import org.repeid.models.jpa.entities.PreguntaEscalaLinealEntity;
import org.repeid.models.jpa.entities.PreguntaNumericaEntity;
import org.repeid.models.jpa.entities.PreguntaSeleccionEntity;
import org.repeid.models.jpa.entities.PreguntaTextoEntity;
import org.repeid.models.jpa.entities.PreguntaTiempoEntity;
import org.repeid.models.jpa.entities.SeccionEntity;
import org.sistcoopform.models.ColumnaCuadriculaModel;
import org.sistcoopform.models.FilaCuadriculaModel;
import org.sistcoopform.models.OpcionSeleccionModel;
import org.sistcoopform.models.PreguntaCuadriculaOpcionesModel;
import org.sistcoopform.models.PreguntaEscalaLinealModel;
import org.sistcoopform.models.PreguntaModel;
import org.sistcoopform.models.PreguntaNumericaModel;
import org.sistcoopform.models.PreguntaProvider;
import org.sistcoopform.models.PreguntaSeleccionModel;
import org.sistcoopform.models.PreguntaTextoModel;
import org.sistcoopform.models.PreguntaTiempoModel;
import org.sistcoopform.models.SeccionModel;
import org.sistcoopform.models.enums.TipoPreguntaNumero;
import org.sistcoopform.models.enums.TipoPreguntaSeleccion;
import org.sistcoopform.models.enums.TipoPreguntaTexto;
import org.sistcoopform.models.enums.TipoPreguntaTiempo;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

@Named
@Stateless
@Local(PreguntaProvider.class)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class JpaPreguntaProvider extends AbstractHibernateStorage implements PreguntaProvider {

    // private static final String TITULO = "titulo";

    @PersistenceContext
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return this.em;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }

    @Override
    public PreguntaTiempoModel createTiempo(SeccionModel seccion, String titulo, String descripcion,
            int numero, TipoPreguntaTiempo tipoPregunta, boolean obligatorio) {
        SeccionEntity seccionEntity = em.find(SeccionEntity.class, seccion.getId());

        PreguntaTiempoEntity preguntaEntity = new PreguntaTiempoEntity();
        preguntaEntity.setTitulo(titulo);
        preguntaEntity.setDescripcion(descripcion);
        preguntaEntity.setNumero(numero);
        preguntaEntity.setSeccion(seccionEntity);

        preguntaEntity.setTipoPregunta(tipoPregunta.toString());
        preguntaEntity.setObligatorio(obligatorio);
        em.persist(preguntaEntity);
        return new PreguntaTiempoAdapter(em, preguntaEntity);
    }

    @Override
    public PreguntaTextoModel createTexto(SeccionModel seccion, String titulo, String descripcion, int numero,
            TipoPreguntaTexto tipoPregunta, boolean obligatorio) {
        SeccionEntity seccionEntity = em.find(SeccionEntity.class, seccion.getId());

        PreguntaTextoEntity preguntaEntity = new PreguntaTextoEntity();
        preguntaEntity.setTitulo(titulo);
        preguntaEntity.setDescripcion(descripcion);
        preguntaEntity.setNumero(numero);
        preguntaEntity.setSeccion(seccionEntity);

        preguntaEntity.setTipoPregunta(tipoPregunta.toString());
        preguntaEntity.setObligatorio(obligatorio);
        em.persist(preguntaEntity);
        return new PreguntaTextoAdapter(em, preguntaEntity);
    }

    @Override
    public PreguntaNumericaModel createNumero(SeccionModel seccion, String titulo, String descripcion,
            int numero, TipoPreguntaNumero tipoPregunta, boolean obligatorio) {
        SeccionEntity seccionEntity = em.find(SeccionEntity.class, seccion.getId());

        PreguntaNumericaEntity preguntaEntity = new PreguntaNumericaEntity();
        preguntaEntity.setTitulo(titulo);
        preguntaEntity.setDescripcion(descripcion);
        preguntaEntity.setNumero(numero);
        preguntaEntity.setSeccion(seccionEntity);

        preguntaEntity.setTipoPregunta(tipoPregunta.toString());
        preguntaEntity.setObligatorio(obligatorio);
        em.persist(preguntaEntity);
        return new PreguntaNumericaAdapter(em, preguntaEntity);
    }

    @Override
    public PreguntaSeleccionModel createSeleccion(SeccionModel seccion, String titulo, String descripcion,
            int numero, TipoPreguntaSeleccion tipoPregunta, boolean obligatorio) {
        SeccionEntity seccionEntity = em.find(SeccionEntity.class, seccion.getId());

        PreguntaSeleccionEntity preguntaEntity = new PreguntaSeleccionEntity();
        preguntaEntity.setTitulo(titulo);
        preguntaEntity.setDescripcion(descripcion);
        preguntaEntity.setNumero(numero);
        preguntaEntity.setSeccion(seccionEntity);

        preguntaEntity.setTipoPregunta(tipoPregunta.toString());
        preguntaEntity.setObligatorio(obligatorio);

        em.persist(preguntaEntity);
        return new PreguntaSeleccionAdapter(em, preguntaEntity);
    }

    @Override
    public OpcionSeleccionModel createOpcionSeleccion(PreguntaSeleccionModel preguntaSeleccion,
            String denominacion, int numero, boolean editable) {
        PreguntaSeleccionEntity preguntaSeleccionEntity = em.find(PreguntaSeleccionEntity.class,
                preguntaSeleccion.getId());

        OpcionSeleccionEntity opcionSeleccionEntity = new OpcionSeleccionEntity();
        opcionSeleccionEntity.setDenominacion(denominacion);
        opcionSeleccionEntity.setNumero(numero);
        opcionSeleccionEntity.setEditable(editable);
        opcionSeleccionEntity.setPreguntaSeleccion(preguntaSeleccionEntity);

        em.persist(opcionSeleccionEntity);
        return new OpcionSeleccionAdapter(em, opcionSeleccionEntity);
    }

    @Override
    public PreguntaEscalaLinealModel createEscalaLineal(SeccionModel seccion, String titulo,
            String descripcion, int numero, String etiqueta1, String etiqueta2, int desde, int hasta,
            boolean obligatorio) {
        SeccionEntity seccionEntity = em.find(SeccionEntity.class, seccion.getId());

        PreguntaEscalaLinealEntity preguntaEntity = new PreguntaEscalaLinealEntity();
        preguntaEntity.setTitulo(titulo);
        preguntaEntity.setDescripcion(descripcion);
        preguntaEntity.setNumero(numero);
        preguntaEntity.setSeccion(seccionEntity);

        preguntaEntity.setEtiqueta1(etiqueta1);
        preguntaEntity.setEtiqueta2(etiqueta2);
        preguntaEntity.setDesde(desde);
        preguntaEntity.setHasta(hasta);
        preguntaEntity.setObligatorio(obligatorio);

        em.persist(preguntaEntity);
        return new PreguntaEscalaLinealAdapter(em, preguntaEntity);
    }

    @Override
    public PreguntaCuadriculaOpcionesModel createCuadricula(SeccionModel seccion, String titulo,
            String descripcion, int numero, boolean requiereRespuestaPorFila) {
        SeccionEntity seccionEntity = em.find(SeccionEntity.class, seccion.getId());

        PreguntaCuadriculaOpcionesEntity opcion = new PreguntaCuadriculaOpcionesEntity();
        opcion.setTitulo(titulo);
        opcion.setDescripcion(descripcion);
        opcion.setNumero(numero);
        opcion.setSeccion(seccionEntity);

        opcion.setRequiereRespuestaPorFila(requiereRespuestaPorFila);
        em.persist(opcion);
        return new PreguntaCuadriculaOpcionesAdapter(em, opcion);
    }

    @Override
    public FilaCuadriculaModel createFilaCuadricula(PreguntaCuadriculaOpcionesModel preguntaCuadricula,
            String denominacion, int numero, boolean editable) {
        PreguntaCuadriculaOpcionesEntity preguntaCuadriculaOpcionesEntity = em
                .find(PreguntaCuadriculaOpcionesEntity.class, preguntaCuadricula.getId());

        FilaCuadriculaEntity fila = new FilaCuadriculaEntity();
        fila.setDenominacion(denominacion);
        fila.setNumero(numero);
        fila.setEditable(editable);
        fila.setPreguntaCuadricula(preguntaCuadriculaOpcionesEntity);
        em.persist(fila);
        return new FilaCuadriculaAdapter(em, fila);
    }

    @Override
    public ColumnaCuadriculaModel createColumnaCuadricula(PreguntaCuadriculaOpcionesModel preguntaCuadricula,
            String denominacion, int numero, boolean editable) {
        PreguntaCuadriculaOpcionesEntity preguntaCuadriculaOpcionesEntity = em
                .find(PreguntaCuadriculaOpcionesEntity.class, preguntaCuadricula.getId());

        ColumnaCuadriculaEntity fila = new ColumnaCuadriculaEntity();
        fila.setDenominacion(denominacion);
        fila.setNumero(numero);
        fila.setEditable(editable);
        fila.setPreguntaCuadricula(preguntaCuadriculaOpcionesEntity);
        em.persist(fila);
        return new ColumnaCuadriculaAdapter(em, fila);
    }

    @Override
    public PreguntaModel findById(String id) {
        return null;
    }

    @Override
    public boolean remove(PreguntaModel pregunta) {
        return false;
    }

    @Override
    public List<PreguntaModel> getAll(SeccionModel seccion) {
        return getAll(seccion, -1, -1);
    }

    @Override
    public List<PreguntaModel> getAll(SeccionModel seccion, int firstResult, int maxResults) {
        /*TypedQuery<SeccionEntity> query = em.createNamedQuery("SeccionEntity.findByIdFormulario",
                SeccionEntity.class);
        query.setParameter("idFormulario", formulario.getId());
        if (firstResult != -1) {
            query.setFirstResult(firstResult);
        }
        if (maxResults != -1) {
            query.setMaxResults(maxResults);
        }
        List<SeccionEntity> entities = query.getResultList();
        List<SeccionModel> models = new ArrayList<SeccionModel>();
        for (SeccionEntity entity : entities) {
            models.add(new SeccionAdapter(em, entity));
        }
        return models;*/
        return null;
    }

}
