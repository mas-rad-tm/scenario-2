package ch.globaz.tmmas.rentesservice.domain.model.dossier;

import lombok.ToString;

@ToString
public enum DossierStatus {

    VALIDATION_REQUERANT,INITIE, VALIDE, CLOT, ERREUR;
}
