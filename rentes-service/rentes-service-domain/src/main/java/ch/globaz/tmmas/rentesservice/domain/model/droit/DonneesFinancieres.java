package ch.globaz.tmmas.rentesservice.domain.model.droit;

import ch.globaz.tmmas.rentesservice.domain.common.ValueObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@Getter
@ToString
public final class DonneesFinancieres implements ValueObject<DonneesFinancieres>{

    private Integer montantDonnees;

    private String libelle;

    private DonneesFinancieresType type;

    private DonneesFinancieres(String libelle, Integer montant, DonneesFinancieresType type){
        this.libelle = libelle;
        this.montantDonnees = montant;
        this.type = type;
    }

    public static DonneesFinancieres newFortune(String libelle, Integer montant){
        return new DonneesFinancieres(libelle,montant,DonneesFinancieresType.FORTUNE);
    }

    public static DonneesFinancieres newDepense(String libelle, Integer montant){
        return new DonneesFinancieres(libelle,montant,DonneesFinancieresType.DEPENSE);
    }

    public static DonneesFinancieres newRevenu(String libelle, Integer montant){
        return new DonneesFinancieres(libelle,montant,DonneesFinancieresType.REVENU);
    }


    //hibernate
    private Long id;

    public DonneesFinancieres() {}

    @Override
    public boolean sameValueAs(DonneesFinancieres other) {
        return false;
    }
}
