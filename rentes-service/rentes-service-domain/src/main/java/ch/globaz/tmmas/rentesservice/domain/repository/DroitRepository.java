package ch.globaz.tmmas.rentesservice.domain.repository;

import ch.globaz.tmmas.rentesservice.domain.model.droit.Droit;

import java.util.List;
import java.util.Optional;

public interface DroitRepository {
    Droit initieDroit(Droit droit);

    Optional<Droit> getDroitById(Long idDossier);

    List<Droit> getDroitByIdDossier(Long idDossier);
}
