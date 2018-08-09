package ch.globaz.tmmas.personnesservice.infrastructure.messaging.kafka;

public enum KafkaTopics {

    DOSSIER_CREE("dossier-cree"),
    PERSONNE_VERIFIE("personne-verifie");

    public String nom;

    KafkaTopics(String nom){
        this.nom = nom;
    }

    public String nom() {
        return nom;
    }
}
