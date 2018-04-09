package ch.globaz.tmmas.personnesservice.infrastructure.repository;

import ch.globaz.tmmas.personnesservice.domain.model.Localite;
import ch.globaz.tmmas.personnesservice.domain.repository.LocaliteRepository;
import org.springframework.stereotype.Component;

@Component
public class LocaliteHibernateRepository extends HibernateRepository implements LocaliteRepository  {


	@Override
	public Localite creerLocalite(Localite localite) {
		getSession().saveOrUpdate(localite);

		return localite;
	}
}
