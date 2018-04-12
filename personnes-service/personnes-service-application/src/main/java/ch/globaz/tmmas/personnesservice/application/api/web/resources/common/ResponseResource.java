package ch.globaz.tmmas.personnesservice.application.api.web.resources.common;

import lombok.Getter;

@Getter
public class ResponseResource {

    private ResourceObject data;

    public ResponseResource(){};

    public ResponseResource(ResourceObject data){
        this.data = data;
    }


}
