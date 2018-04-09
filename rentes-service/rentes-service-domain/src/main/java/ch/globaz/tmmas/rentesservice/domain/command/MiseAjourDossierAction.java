package ch.globaz.tmmas.rentesservice.domain.command;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MiseAjourDossierAction {

	VALIDER("valider"), CLORE("clore");


	private String action;

	MiseAjourDossierAction(String action){
		this.action = action;
	}

}
