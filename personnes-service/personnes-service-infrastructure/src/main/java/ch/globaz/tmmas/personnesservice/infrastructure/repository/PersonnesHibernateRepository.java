package ch.globaz.tmmas.personnesservice.infrastructure.repository;

import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.model.Localite;
import ch.globaz.tmmas.personnesservice.domain.model.PersonneId;
import ch.globaz.tmmas.personnesservice.domain.model.PersonneMorale;
import ch.globaz.tmmas.personnesservice.domain.repository.PersonneRepository;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Component
public class PersonnesHibernateRepository extends HibernateRepository implements PersonneRepository {

	@Override
	public PersonneMorale creerPersonneMorale(PersonneMorale personneMorale) {
		getSession().saveOrUpdate(personneMorale);
		return personneMorale;
	}

	@Override
	public PersonneMorale synchoniser(PersonneMorale personneMorale) {
		getSession().merge(personneMorale);
		return personneMorale;
	}

	@Override
	public Optional<PersonneMorale> getPersonneById(Long id) {
		PersonneMorale personneMorale =  getSession().get(PersonneMorale.class, id);
		return Optional.ofNullable(personneMorale);
	}

	@Override
	public PersonneMorale mettreAJour(PersonneMorale personneMorale){
		this.getSession().flush();
		this.getSession().saveOrUpdate(personneMorale);
		//this.getSession().update(personneMorale);
		return personneMorale;
	}

	@Override
	public Optional<PersonneMorale> getPersonneByNss(String nss) {

		CriteriaBuilder builder = getSession().getCriteriaBuilder();

		CriteriaQuery<PersonneMorale> criteria = builder.createQuery(PersonneMorale.class);
		Root<PersonneMorale> root = criteria.from(PersonneMorale.class);

		criteria.select(root).where(builder.equal(root.get("identifiant"), new PersonneId(nss)));

		Query<PersonneMorale> q = getSession().createQuery(criteria);

		List<PersonneMorale> personnes = q.getResultList();

		PersonneMorale personneMorale = null;

		if(personnes.size() > 0){
			personneMorale =q .getSingleResult();
		}


		return Optional.ofNullable(personneMorale);
	}





}
