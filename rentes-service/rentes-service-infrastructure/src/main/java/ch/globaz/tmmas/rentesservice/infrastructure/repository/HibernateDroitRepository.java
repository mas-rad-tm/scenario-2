package ch.globaz.tmmas.rentesservice.infrastructure.repository;


import ch.globaz.tmmas.rentesservice.domain.model.droit.Droit;
import ch.globaz.tmmas.rentesservice.domain.repository.DroitRepository;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class HibernateDroitRepository extends HibernateRepository implements DroitRepository  {

    private static final Logger LOGGER = LoggerFactory.getLogger(HibernateDroitRepository.class);

    @Override
    public Droit initieDroit(Droit droit) {
        getSession().saveOrUpdate(droit);

        return droit;
    }

    @Override
    public Optional<Droit> getDroitById(Long idDroit) {

        LOGGER.debug("{}#getBiyId, droit:{}",this.getClass().getName(),idDroit);


        Optional<Droit> optionalDroit = Optional.ofNullable(getSession().get(Droit.class,idDroit));


        return optionalDroit;
    }

    @Override
    public List<Droit> getDroitByIdDossier(Long idDossier) {

        Query query = getSession().createQuery("FROM Droit droit WHERE droit.dossier.id = :idDossier");

        query.setParameter("idDossier", idDossier);

        // You can replace the above to commands with this one

        // Query query = session.createQuery("from Student where studentId = 1 ");

        List<Droit> list = query.list();




        return list;
    }
}