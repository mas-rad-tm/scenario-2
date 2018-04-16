package ch.globaz.tmmas.personnesservice.domain.model;

import ch.globaz.tmmas.personnesservice.domain.common.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class PersonneId implements ValueObject<PersonneId>{


	public void setNss(String nss){
		this.nss = nss;
	}

	public String getNss(String nss){
		return nss;
	}

	private String nss;

	public PersonneId(String nss){
		this.nss = nss;
	}

	@Override
	public boolean sameValueAs(PersonneId other) {
		return this.nss.equals(other.nss);
	}

	PersonneId(){}
}
