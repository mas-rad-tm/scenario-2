package ch.globaz.tmmas.personnesservice.application.service.impl;

import ch.globaz.tmmas.personnesservice.application.service.LocalitesService;
import ch.globaz.tmmas.personnesservice.domain.model.Localite;
import ch.globaz.tmmas.personnesservice.domain.repository.LocaliteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LocalitesServiceImpl implements LocalitesService {

	@Autowired
	LocaliteRepository repository;

	@Transactional
	@Override
	public void generateLocalitAsSample() {

		Localite localite = new Localite("Delémont",2800);
		repository.creerLocalite(localite);

		localite = new Localite("Porrentruy",2900);
		repository.creerLocalite(localite);

		localite = new Localite("Neuchâtel",2400);
		repository.creerLocalite(localite);

		localite = new Localite("Genève",1200);
		repository.creerLocalite(localite);

		localite = new Localite("Zurich",8000);
		repository.creerLocalite(localite);


	}
}
