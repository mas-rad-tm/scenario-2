package ch.globaz.tmmas.rentesservice.application.api.messaging.listener;



import ch.globaz.tmmas.rentesservice.application.event.InternalEventPublisher;
import ch.globaz.tmmas.rentesservice.application.service.DossierService;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.infrastructure.messaging.event.PersonnePhysiqueVerificationEvent;
import ch.globaz.tmmas.rentesservice.infrastructure.repository.DossierHibernateRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class MessagingListener {


    @Autowired
    DossierService dossierService;

    @Autowired
    InternalEventPublisher eventPublisher;

    private static final Logger LOGGER = LoggerFactory.getLogger(MessagingListener.class);


    @KafkaListener(topics = "personne-verifie",containerFactory = "jsonKafkaListenerContainerFactory")
    public void listen(PersonnePhysiqueVerificationEvent message, Acknowledgment acknowledgment) {

        LOGGER.info("Message consumed {}",message);

        LOGGER.info("PersonnePhysiqueVerified id:{}, exist:{}",message.getId(),message.getExist());
        //l'id tiers existe
        if(message.getExist()){
            dossierService.getByIdRequerant(message.getId()).map(dossier ->
               dossierService.initierDossier(dossier.getId())
            );
        }else{
            dossierService.getByIdRequerant(message.getId()).map(dossier ->
                dossierService.erreurDossier(dossier.getId())
            );
        }

        acknowledgment.acknowledge();
        LOGGER.info("Consummer acknoledge");
    }
}
