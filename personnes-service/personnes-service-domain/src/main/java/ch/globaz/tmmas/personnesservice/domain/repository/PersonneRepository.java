package ch.globaz.tmmas.personnesservice.domain.repository;

import ch.globaz.tmmas.personnesservice.domain.model.PersonnePhysique;

import java.util.List;
import java.util.Optional;

public interface PersonneRepository {

	PersonnePhysique creerPersonneMorale(PersonnePhysique personnePhysique);

    PersonnePhysique synchoniser(PersonnePhysique personnePhysique);

	Boolean personneExist(Long personneId);

	Optional<PersonnePhysique> getPersonneById(Long id);

    PersonnePhysique mettreAJour(PersonnePhysique personnePhysique);

    Optional<PersonnePhysique> getPersonneByNss(String nss);

    List<PersonnePhysique> listerPersonnes();
}
