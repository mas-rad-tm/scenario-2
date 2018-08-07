package ch.globaz.tmmas.rentesservice.application.api.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@RequestMapping("/events-stream")
public class SSEController {

    public static final CopyOnWriteArrayList<SseEmitter> consommateurs = new CopyOnWriteArrayList<>();

    @RequestMapping
    public SseEmitter handle() {

        SseEmitter consommateur = new SseEmitter(180_000L);
        this.consommateurs.add(consommateur);

        consommateur.onCompletion(() -> this.consommateurs.remove(consommateur));
        consommateur.onTimeout(() -> this.consommateurs.remove(consommateur));

        return consommateur;
    }


}
