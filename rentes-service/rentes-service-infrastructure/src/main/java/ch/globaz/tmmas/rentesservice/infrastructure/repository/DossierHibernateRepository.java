package ch.globaz.tmmas.rentesservice.infrastructure.repository;


import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.droit.Droit;
import ch.globaz.tmmas.rentesservice.domain.repository.DossierRepository;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import java.util.List;
import java.util.Optional;

@Repository
public class DossierHibernateRepository extends HibernateRepository implements DossierRepository {


	private static final Logger LOGGER = LoggerFactory.getLogger(DossierHibernateRepository.class);


	@Override
	public Dossier initieDossier(Dossier dossier) {

		LOGGER.debug("initieDossier (): {}", dossier);

		getSession().saveOrUpdate(dossier);

		return dossier;
	}

	@Override
	public Dossier creerDossier(Dossier dossier) {
		LOGGER.debug("creerDossier (): {}", dossier);

		getSession().saveOrUpdate(dossier);

		return dossier;
	}


	@Override
	public List<Dossier> allDossiers() {
		return getSession().createQuery("FROM " + Dossier.class.getSimpleName()).list();
	}


	@Override
	public Optional<Dossier> dossierById(Long dossierId) {

		LOGGER.debug("{}#getBiyId, dossierId:{}",this.getClass().getName(),dossierId);

		Optional<Dossier> dossier = Optional.ofNullable(getSession().get(Dossier.class,dossierId));

		return dossier;
	}

	@Override
	public Optional<Dossier> dossierByIdRequerant(Long requerantId) {

		LOGGER.debug("{}#getBiyIdRequerant, requerantId:{}",this.getClass().getName(),requerantId);

		Query query = getSession().createQuery("from Dossier where requerantId = :idRequerant");
		query.setParameter("idRequerant", requerantId);

		List results = query.list();

		Dossier dossierForRequerant = null;

		if(results.size() == 1){
			dossierForRequerant = (Dossier) results.get(0);
		}else if(results.size() > 1){
			throw new RuntimeException("more than one dossier found...");
		}

		Optional<Dossier> dossier = Optional.ofNullable(dossierForRequerant);

		return dossier;
	}


	@Override
	public Dossier validerDossier(Dossier dossier) {

		getSession().saveOrUpdate(dossier);

		return dossier;
	}

	@Override
	public Dossier cloreDossier(Dossier dossier) {

		getSession().saveOrUpdate(dossier);

		return dossier;
	}

	@Override
	public Dossier erreurDossier(Dossier dossier) {
		LOGGER.debug("erreurDossier (): {}", dossier);
		getSession().saveOrUpdate(dossier);

		return dossier;
	}


}
