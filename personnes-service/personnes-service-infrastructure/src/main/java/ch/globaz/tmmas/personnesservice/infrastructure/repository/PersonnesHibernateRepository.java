package ch.globaz.tmmas.personnesservice.infrastructure.repository;

import ch.globaz.tmmas.personnesservice.domain.model.NSS;
import ch.globaz.tmmas.personnesservice.domain.model.PersonnePhysique;
import ch.globaz.tmmas.personnesservice.domain.repository.PersonneRepository;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonnesHibernateRepository extends HibernateRepository implements PersonneRepository {

	@Override
	public PersonnePhysique creerPersonneMorale(PersonnePhysique personnePhysique) {
		getSession().saveOrUpdate(personnePhysique);
		return personnePhysique;
	}

	@Override
	public PersonnePhysique synchoniser(PersonnePhysique personnePhysique) {
		personnePhysique = (PersonnePhysique) getSession().merge(personnePhysique);
		return personnePhysique;
	}

	@Override
	public Boolean personneExist(Long personneId){
		CriteriaBuilder builder = getSession().getCriteriaBuilder();
		CriteriaQuery<PersonnePhysique> criteria = builder.createQuery(PersonnePhysique.class);
		Root<PersonnePhysique> root = criteria.from(PersonnePhysique.class);

		criteria.select(root.get("id"))
				.where(builder.equal(root.get("id"), personneId));
		Query<PersonnePhysique> q = getSession().createQuery(criteria);
		return q.uniqueResult() != null;
	}

	@Override
	public Optional<PersonnePhysique> getPersonneById(Long id) {
		PersonnePhysique personnePhysique =  getSession().get(PersonnePhysique.class, id);
		return Optional.ofNullable(personnePhysique);
	}

	@Override
	public PersonnePhysique mettreAJour(PersonnePhysique personnePhysique){


		//this.getSession().flush();
		personnePhysique = (PersonnePhysique) this.getSession().merge(personnePhysique);
		//this.getSession().saveOrUpdate(personnePhysique);
		//this.getSession().update(personnePhysique);
		return personnePhysique;
	}

	@Override
	public Optional<PersonnePhysique> getPersonneByNss(String nss) {

		CriteriaBuilder builder = getSession().getCriteriaBuilder();

		CriteriaQuery<PersonnePhysique> criteria = builder.createQuery(PersonnePhysique.class);
		Root<PersonnePhysique> root = criteria.from(PersonnePhysique.class);

		criteria.select(root).where(builder.equal(root.get("nss"), new NSS(nss)));

		Query<PersonnePhysique> q = getSession().createQuery(criteria);

		List<PersonnePhysique> personnes = q.getResultList();

		PersonnePhysique personnePhysique = null;

		if(personnes.size() > 0){
			personnePhysique =q .getSingleResult();
		}


		return Optional.ofNullable(personnePhysique);
	}

	@Override
	public List<PersonnePhysique> listerPersonnes() {
		return getSession().createQuery("FROM " + PersonnePhysique.class.getSimpleName()).list();
	}


}
