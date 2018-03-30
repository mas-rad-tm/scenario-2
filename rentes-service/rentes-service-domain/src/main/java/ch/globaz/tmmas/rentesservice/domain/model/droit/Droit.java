package ch.globaz.tmmas.rentesservice.domain.model.droit;

import ch.globaz.tmmas.rentesservice.domain.command.CreerDroitCommand;
import ch.globaz.tmmas.rentesservice.domain.common.Entity;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
@Getter
public class Droit implements Entity<Droit>{

    private List<DonneesFinancieres> donnesFinancieres;
    private DroitId identifiant;
    private LocalDate dateDebutDroit;
    private LocalDate dateFinDroit;
    private Dossier dossier;
    private DroitStatus status;


    public Droit(LocalDate dateDebutDroit, Dossier dossier){
        this.dateDebutDroit = dateDebutDroit;
        this.dossier = dossier;
        this.donnesFinancieres = new ArrayList<>();
        this.identifiant = DroitId.aleatoire();
        this.status = DroitStatus.INITIE;
    }


    public void ajouteDonneeFinanciere(DonneesFinancieres donnees){
        this.donnesFinancieres.add(donnees);
    }

    @Override
    public boolean sameIdentityAs(Droit other) {
        return false;
    }

    public static Droit builder(CreerDroitCommand command, Dossier dossier) {
        return new Droit(command.getDateDebutDroit(),dossier);
    }

    //hibernate
    private Long id;

    public Droit() {}
}
