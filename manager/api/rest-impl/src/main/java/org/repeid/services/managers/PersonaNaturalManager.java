package org.repeid.services.managers;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.repeid.representations.idm.PersonaNaturalRepresentation;
import org.sistcoopform.models.PersonaNaturalModel;
import org.sistcoopform.models.StoreConfigurationModel;
import org.sistcoopform.models.StoredFileModel;
import org.sistcoopform.models.StoredFileProvider;
import org.sistcoopform.models.enums.EstadoCivil;
import org.sistcoopform.models.enums.Sexo;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PersonaNaturalManager {

	public void update(PersonaNaturalModel model, PersonaNaturalRepresentation representation) {
		model.setCodigoPais(representation.getCodigoPais());
		model.setApellidoPaterno(representation.getApellidoPaterno());
		model.setApellidoMaterno(representation.getApellidoMaterno());
		model.setNombres(representation.getNombres());
		model.setFechaNacimiento(representation.getFechaNacimiento());
		model.setSexo(Sexo.valueOf(representation.getSexo().toUpperCase()));
		model.setEstadoCivil(representation.getEstadoCivil() != null
				? EstadoCivil.valueOf(representation.getEstadoCivil().toUpperCase()) : null);

		model.setUbigeo(representation.getUbigeo());
		model.setDireccion(representation.getDireccion());
		model.setReferencia(representation.getReferencia());
		model.setOcupacion(representation.getOcupacion());
		model.setTelefono(representation.getTelefono());
		model.setCelular(representation.getCelular());
		model.setEmail(representation.getEmail());

		model.commit();
	}

	public StoredFileModel setFoto(PersonaNaturalModel personaNatural, StoreConfigurationModel config, byte[] bytes,
			StoredFileProvider storedFileProvider) {
		StoredFileModel storedFileModel = storedFileProvider.create(bytes, config);
		personaNatural.setFoto(storedFileModel);
		personaNatural.commit();
		return storedFileModel;
	}

	public StoredFileModel setFirma(PersonaNaturalModel personaNatural, StoreConfigurationModel config, byte[] bytes,
			StoredFileProvider storedFileProvider) {
		StoredFileModel storedFileModel = storedFileProvider.create(bytes, config);
		personaNatural.setFirma(storedFileModel);
		personaNatural.commit();
		return storedFileModel;
	}

}