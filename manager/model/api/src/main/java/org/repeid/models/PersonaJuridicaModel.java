package org.repeid.models;

import java.util.Date;
import java.util.List;

import org.repeid.models.enums.TipoEmpresa;

public interface PersonaJuridicaModel extends Model {

    String RAZON_SOCIAL = "razonSocial";
    String NOMBRE_COMERCIAL = "nombreComercial";
    String NUMERO_DOCUMENTO = "numeroDocumento";

    String getId();

    PersonaNaturalModel getRepresentanteLegal();

    void setRepresentanteLegal(PersonaNaturalModel representanteLegal);

    List<AccionistaModel> getAccionistas();

    String getCodigoPais();

    void setCodigoPais(String codigoPais);

    TipoDocumentoModel getTipoDocumento();

    String getNumeroDocumento();

    String getRazonSocial();

    void setRazonSocial(String razonSocial);

    String getNombreComercial();

    void setNombreComercial(String nombreComercial);

    Date getFechaConstitucion();

    void setFechaConstitucion(Date fechaConstitucion);

    String getActividadPrincipal();

    void setActividadPrincipal(String actividadPrincipal);

    TipoEmpresa getTipoEmpresa();

    void setTipoEmpresa(TipoEmpresa tipoEmpresa);

    boolean isFinLucro();

    void setFinLucro(boolean finLucro);

    String getUbigeo();

    void setUbigeo(String ubigeo);

    String getDireccion();

    void setDireccion(String direccion);

    String getReferencia();

    void setReferencia(String referencia);

    String getTelefono();

    void setTelefono(String telefono);

    String getCelular();

    void setCelular(String celular);

    String getEmail();

    void setEmail(String email);

}