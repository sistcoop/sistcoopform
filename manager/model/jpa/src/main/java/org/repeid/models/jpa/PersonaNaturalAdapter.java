package org.repeid.models.jpa;

import java.util.Date;

import javax.persistence.EntityManager;

import org.repeid.models.StoredFileModel;
import org.repeid.models.PersonaNaturalModel;
import org.repeid.models.TipoDocumentoModel;
import org.repeid.models.enums.EstadoCivil;
import org.repeid.models.enums.Sexo;
import org.repeid.models.jpa.entities.PersonaNaturalEntity;
import org.repeid.models.jpa.entities.StoredFileEntity;

/**
 * @author <a href="mailto:carlosthe19916@sistcoop.com">Carlos Feria</a>
 */

public class PersonaNaturalAdapter implements PersonaNaturalModel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private PersonaNaturalEntity personaNaturalEntity;
    private EntityManager em;

    public PersonaNaturalAdapter(EntityManager em, PersonaNaturalEntity personaNaturalEntity) {
        this.em = em;
        this.personaNaturalEntity = personaNaturalEntity;
    }

    public PersonaNaturalEntity getPersonaNaturalEntity() {
        return this.personaNaturalEntity;
    }

    public static PersonaNaturalEntity toPersonaNaturalEntity(PersonaNaturalModel model, EntityManager em) {
        if (model instanceof PersonaNaturalAdapter) {
            return ((PersonaNaturalAdapter) model).getPersonaNaturalEntity();
        }
        return em.getReference(PersonaNaturalEntity.class, model.getId());
    }

    @Override
    public void commit() {
        em.merge(personaNaturalEntity);
    }

    @Override
    public String getId() {
        return personaNaturalEntity.getId();
    }

    @Override
    public String getCodigoPais() {
        return personaNaturalEntity.getCodigoPais();
    }

    @Override
    public void setCodigoPais(String codigoPais) {
        personaNaturalEntity.setCodigoPais(codigoPais);
    }

    @Override
    public TipoDocumentoModel getTipoDocumento() {
        return new TipoDocumentoAdapter(em, personaNaturalEntity.getTipoDocumento());
    }

    @Override
    public String getNumeroDocumento() {
        return personaNaturalEntity.getNumeroDocumento();
    }

    @Override
    public String getApellidoPaterno() {
        return personaNaturalEntity.getApellidoPaterno();
    }

    @Override
    public void setApellidoPaterno(String apellidoPaterno) {
        personaNaturalEntity.setApellidoPaterno(apellidoPaterno);
    }

    @Override
    public String getApellidoMaterno() {
        return personaNaturalEntity.getApellidoMaterno();
    }

    @Override
    public void setApellidoMaterno(String apellidoMaterno) {
        personaNaturalEntity.setApellidoMaterno(apellidoMaterno);
    }

    @Override
    public String getNombres() {
        return personaNaturalEntity.getNombres();
    }

    @Override
    public void setNombres(String nombres) {
        personaNaturalEntity.setNombres(nombres);
    }

    @Override
    public Date getFechaNacimiento() {
        return personaNaturalEntity.getFechaNacimiento();
    }

    @Override
    public void setFechaNacimiento(Date fechaNacimiento) {
        personaNaturalEntity.setFechaNacimiento(fechaNacimiento);
    }

    @Override
    public Sexo getSexo() {
        String sexo = personaNaturalEntity.getSexo();
        if (sexo != null) {
            return Sexo.valueOf(sexo);
        } else {
            return null;
        }
    }

    @Override
    public void setSexo(Sexo sexo) {
        personaNaturalEntity.setSexo(sexo != null ? sexo.toString() : null);
    }

    @Override
    public EstadoCivil getEstadoCivil() {
        String estadoCivil = personaNaturalEntity.getEstadoCivil();
        if (estadoCivil != null) {
            return EstadoCivil.valueOf(estadoCivil);
        } else {
            return null;
        }
    }

    @Override
    public void setEstadoCivil(EstadoCivil estadoCivil) {
        personaNaturalEntity.setEstadoCivil(estadoCivil != null ? estadoCivil.toString() : null);
    }

    @Override
    public String getOcupacion() {
        return personaNaturalEntity.getOcupacion();
    }

    @Override
    public void setOcupacion(String ocupacion) {
        personaNaturalEntity.setOcupacion(ocupacion);
    }

    @Override
    public String getUbigeo() {
        return personaNaturalEntity.getUbigeo();
    }

    @Override
    public void setUbigeo(String ubigeo) {
        personaNaturalEntity.setUbigeo(ubigeo);
    }

    @Override
    public String getDireccion() {
        return personaNaturalEntity.getDireccion();
    }

    @Override
    public void setDireccion(String direccion) {
        personaNaturalEntity.setDireccion(direccion);
    }

    @Override
    public String getReferencia() {
        return personaNaturalEntity.getReferencia();
    }

    @Override
    public void setReferencia(String referencia) {
        personaNaturalEntity.setReferencia(referencia);
    }

    @Override
    public String getTelefono() {
        return personaNaturalEntity.getTelefono();
    }

    @Override
    public void setTelefono(String telefono) {
        personaNaturalEntity.setTelefono(telefono);
    }

    @Override
    public String getCelular() {
        return personaNaturalEntity.getCelular();
    }

    @Override
    public void setCelular(String celular) {
        personaNaturalEntity.setCelular(celular);
    }

    @Override
    public String getEmail() {
        return personaNaturalEntity.getEmail();
    }

    @Override
    public void setEmail(String email) {
        personaNaturalEntity.setEmail(email);
    }

    @Override
    public StoredFileModel getFoto() {
        StoredFileEntity storedFileEntity = personaNaturalEntity.getFoto();
        if (storedFileEntity != null) {
            return new StoredFileAdapter(em, storedFileEntity);
        } else {
            return null;
        }
    }

    @Override
    public StoredFileModel getFirma() {
        StoredFileEntity storedFileEntity = personaNaturalEntity.getFirma();
        if (storedFileEntity != null) {
            return new StoredFileAdapter(em, storedFileEntity);
        } else {
            return null;
        }
    }

    @Override
    public void setFoto(StoredFileModel foto) {
        if (foto != null) {
            StoredFileEntity storedFileEntity = StoredFileAdapter.toStoredFileEntity(foto, em);
            personaNaturalEntity.setFoto(storedFileEntity);
        } else {
            personaNaturalEntity.setFoto(null);
        }
    }

    @Override
    public void setFirma(StoredFileModel firma) {
        if (firma != null) {
            StoredFileEntity storedFileEntity = StoredFileAdapter.toStoredFileEntity(firma, em);
            personaNaturalEntity.setFirma(storedFileEntity);
        } else {
            personaNaturalEntity.setFirma(null);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getNumeroDocumento() == null) ? 0 : getNumeroDocumento().hashCode());
        result = prime * result + ((getTipoDocumento() == null) ? 0 : getTipoDocumento().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof PersonaNaturalModel))
            return false;
        PersonaNaturalModel other = (PersonaNaturalModel) obj;
        if (getNumeroDocumento() == null) {
            if (other.getNumeroDocumento() != null)
                return false;
        } else if (!getNumeroDocumento().equals(other.getNumeroDocumento()))
            return false;
        if (getTipoDocumento() == null) {
            if (other.getTipoDocumento() != null)
                return false;
        } else if (!getTipoDocumento().equals(other.getTipoDocumento()))
            return false;
        return true;
    }

}
