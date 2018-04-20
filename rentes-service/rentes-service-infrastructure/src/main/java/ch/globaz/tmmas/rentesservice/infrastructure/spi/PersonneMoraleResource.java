package ch.globaz.tmmas.rentesservice.infrastructure.spi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;



@JsonPropertyOrder({"id","type","attributes"})
@JsonIgnoreProperties(value = "links")
@Getter
public  class PersonneMoraleResource {

    @JsonProperty("id")
    private Long technicalId;
    private String type;
    private PersonneMoraleResourceAttributes attributes;

    public PersonneMoraleResource(Long id, String type, PersonneMoraleResourceAttributes attributes) {
        this.technicalId = id;
        this.type = type;
        this.attributes = attributes;
    }

    public PersonneMoraleResource() {}

}
