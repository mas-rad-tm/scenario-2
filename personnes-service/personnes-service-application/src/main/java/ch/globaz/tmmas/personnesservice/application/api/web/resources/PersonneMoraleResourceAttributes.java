package ch.globaz.tmmas.personnesservice.application.api.web.resources;

import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ResourceAttributes;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.ResourceObject;
import ch.globaz.tmmas.personnesservice.application.api.web.resources.common.DateFormatter;
import ch.globaz.tmmas.personnesservice.domain.model.*;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;


/**
 * Ressources REST pour les dossiers
 */
@Getter
public class PersonneMoraleResourceAttributes implements ResourceAttributes {



	private Sexe sexe;
	private String nom;
	private String prenom;
	private String nss;

	@JsonProperty("id")
	private Long technicalId;

	private String formattedDateNaissance;
	private String isoDateNaissance;



	public PersonneMoraleResourceAttributes(){}


	public PersonneMoraleResourceAttributes(PersonnePhysique personnePhysique){


		this.formattedDateNaissance = personnePhysique.getDateNaissance().format(DateFormatter.DATE_FORMAT);
		this.isoDateNaissance = personnePhysique.getDateNaissance().toString();
		this.technicalId = personnePhysique.getId();
		this.nom = personnePhysique.getNom();
		this.prenom = personnePhysique.getPrenom();
		this.sexe = personnePhysique.getSexe();
		this.nss = personnePhysique.nssAsString();

	}

	public ResourceObject buildResourceObject () {
	    return new ResourceObject(this.getTechnicalId(),"personneMorale",
			    this);
    }

}
