package ch.globaz.tmmas.personnesservice.domain.event;

import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.PersonnePhysique;
import ch.globaz.tmmas.personnesservice.domain.model.Sexe;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class PersonneMoraleCreeEvent implements DomainEvent, Serializable {

    private String nss;
    private String nom;
    private String prenom;
    private String dateNaissance;
    private Sexe sexe;
    private Adresse adresseActive;
    private Long id;

    PersonneMoraleCreeEvent(String nss, String nom, String prenom, String dateNaissance, Sexe sexe,
                                   Adresse adresseActive, Long id) {
        this.nss = nss;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.sexe = sexe;
        this.adresseActive = adresseActive;
        this.id = id;
    }

    public static PersonneMoraleCreeEvent fromEntity(PersonnePhysique personnePhysique){
        return new PersonneMoraleCreeEvent(personnePhysique.nssAsString(),
                personnePhysique.getNom(),
                personnePhysique.getPrenom(),
                personnePhysique.getDateNaissance().format(formatter),
                personnePhysique.getSexe(),
                personnePhysique.getAdresseActive(),
                personnePhysique.getId());
    }
}
