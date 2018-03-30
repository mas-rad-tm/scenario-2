package ch.globaz.tmmas.rentesservice.domain.common;

public enum GlobalParamers {

    DATE_FORMATTER_PATTER("dd.MM.yyyy");

    public String value;

    GlobalParamers(String value){
        this.value  = value;
    }
}
