package org.repeid.representations.idm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "estadoCivil")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class EstadoCivilRepresentation {

	String denominacion;

	public EstadoCivilRepresentation(String denominacion) {
		this.denominacion = denominacion;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

}
