package ch.globaz.tmmas.personnesservice.infrastructure.repository;

import ch.globaz.tmmas.personnesservice.domain.model.Localite;
import ch.globaz.tmmas.personnesservice.domain.model.PersonneMorale;
import ch.globaz.tmmas.personnesservice.domain.repository.PersonneRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class PersonnesHibernateRepository extends HibernateRepository implements PersonneRepository {

	@Override
	public PersonneMorale creerPersonneMorale(PersonneMorale personneMorale) {
		getSession().saveOrUpdate(personneMorale);
		return personneMorale;
	}

	@Override
	public Optional<PersonneMorale> getPersonneById(Long id) {
		PersonneMorale personneMorale =  getSession().get(PersonneMorale.class, id);
		return Optional.of(personneMorale);
	}
}
