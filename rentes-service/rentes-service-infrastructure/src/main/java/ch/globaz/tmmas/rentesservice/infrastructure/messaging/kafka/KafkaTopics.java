package ch.globaz.tmmas.rentesservice.infrastructure.messaging.kafka;

public enum KafkaTopics {

    DOSSIER_CREE("dossier-cree");

    String nom;

    KafkaTopics(String nom){
        this.nom = nom;
    }

    public String nom() {
        return nom;
    }
}
