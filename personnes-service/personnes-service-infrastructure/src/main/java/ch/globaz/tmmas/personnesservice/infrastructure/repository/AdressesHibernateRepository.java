package ch.globaz.tmmas.personnesservice.infrastructure.repository;

import ch.globaz.tmmas.personnesservice.domain.model.Adresse;
import ch.globaz.tmmas.personnesservice.domain.repository.AdressesRepository;
import org.springframework.stereotype.Component;

@Component
public class AdressesHibernateRepository extends HibernateRepository implements AdressesRepository {

    @Override
    public Adresse creerAdresse(Adresse adresse) {
        getSession().saveOrUpdate(adresse);
        return adresse;
    }
}
