package ch.globaz.tmmas.rentesservice.domain.model.dossier;

import ch.globaz.tmmas.rentesservice.domain.common.Entity;
import ch.globaz.tmmas.rentesservice.domain.command.CreerDossierCommand;
import ch.globaz.tmmas.rentesservice.domain.model.droit.Droit;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;


@ToString
@EqualsAndHashCode
@Getter
public class Dossier implements Entity<Dossier> {


    private DossierId identifiant;
    private ZonedDateTime dateEnregistrement;
    private ZonedDateTime dateValidation;
    private ZonedDateTime dateCloture;
    private Long requerantId;
    private DossierStatus status;



    public DossierId identifiant() {
        return identifiant;
    }

    public Long id() {
        return id;
    }

    public Long requerantId () {
        return requerantId;
    }

    public ZonedDateTime dateEnregistrement() {
        return dateEnregistrement;
    }

    public DossierStatus status () {
        return status;
    }

    public Dossier(ZonedDateTime dateEnregistrement, Long requerantId){
        this.requerantId = requerantId;
        this.dateEnregistrement = dateEnregistrement;
        this.identifiant = DossierId.aleatoire();
        this.status = DossierStatus.INITIE;

    }

    public static Dossier builder(Long requerantId, ZonedDateTime dateEnregistrement) {
        return new Dossier(dateEnregistrement,requerantId);
    }
/*
    public static Dossier builder(CreerDossierCommand command) {
        ZoneId zoneId = ZoneId.of("Europe/Paris");
        ZonedDateTime zonedDateTime = ZonedDateTime.of(command.getDateEnregistrement(),zoneId);
        return new Dossier(command.getDateEnregistrement(),command.getRequerantId());
    }*/

    public Dossier validerDossier(ZonedDateTime dateValidation){

        this.status = DossierStatus.VALIDE;
        this.dateValidation = dateValidation;
        return this;

    }

    public Dossier cloreDossier(ZonedDateTime dateCloture){
        this.status = DossierStatus.CLOT;
        this.dateCloture = dateCloture;
        return this;
    }



    //hibernate
    private Long id;

    public Dossier() {}



    @Override
    public boolean sameIdentityAs(Dossier dossier) {
        return this.identifiant.equals(dossier.identifiant());
    }
}
