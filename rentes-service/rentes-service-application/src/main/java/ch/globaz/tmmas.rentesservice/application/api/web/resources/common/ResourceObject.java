package ch.globaz.tmmas.rentesservice.application.api.web.resources.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;



@JsonPropertyOrder({"id","type","attributes","links","relationship"})
@Getter
public  class ResourceObject extends ResourceSupport{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("id")
    private Long technicalId;
    private String type;
    private ResourceAttributes attributes;

    public ResourceObject (Long id, String type, ResourceAttributes attributes) {
        this.technicalId = id;
        this.type = type;
        this.attributes = attributes;
    }

    public ResourceObject() {}

}
