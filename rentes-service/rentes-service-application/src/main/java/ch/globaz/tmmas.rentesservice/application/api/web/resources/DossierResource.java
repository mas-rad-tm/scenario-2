package ch.globaz.tmmas.rentesservice.application.api.web.resources;

import ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.LocalDateDeserializer;
import ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.LocalDateSerializer;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.Dossier;
import ch.globaz.tmmas.rentesservice.domain.model.dossier.DossierStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

import static ch.globaz.tmmas.rentesservice.application.api.web.resources.localdate.DateFormatter.DATE_FORMAT;

/**
 * Ressources REST pour les dossiers
 */
@Getter
public class DossierResource extends ResourceSupport{

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);

	private String identifiant;
	private Long requerantId;

	@JsonProperty("id")
	private Long technicalId;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	private LocalDate dateEnregistrement;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDate dateValidation;

	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private LocalDate dateCloture;

	private DossierStatus status;

	private Set<DroitResource> droits;


	public DossierResource(){}

/**
	private DossierResource(Long id, String numero, Long requerantId, LocalDate dateEnregistrement, DossierStatus
			status){

		this.identifiant = numero;
		this.requerantId = requerantId;
		this.technicalId = id;
		this.status = status;

	     this.dateEnregistrement = dateEnregistrement;
		//this.dateEnregistrement = LocalDate.parse(dateEnregistrement,formatter);
	}
*/

	private DossierResource(DossierResourceBuilder builder){

		this.identifiant = builder.identifiant;
		this.requerantId = builder.requerantId;
		this.technicalId = builder.technicalId;
		this.status = builder.status;
		this.dateEnregistrement = builder.dateEnregistrement;
		this.dateValidation = builder.dateValidation;
		this.dateCloture = builder.dateCloture;
	}

	/**
	private DossierResource(Long id, String numero, Long requerantId, LocalDate dateEnregistrement, DossierStatus
			status, String dateValidation){

		this(id,numero,requerantId,dateEnregistrement,status);
		this.dateValidation = LocalDate.parse(dateValidation,formatter);

	}
*/
	/**
	private DossierResource(Long id, String numero, Long requerantId, String dateEnregistrement, DossierStatus
			status, String dateValidation, String dateCloture){

		this(id,numero,requerantId,dateEnregistrement,status,dateValidation);
		this.dateCloture = LocalDate.parse(dateCloture,formatter);

	}

*/
	public DossierResource withDateValidation(LocalDate dateValidation) {
		this.dateValidation = dateValidation;
		return this;

	}

	public DossierResource withDateCloture(LocalDate dateCloture) {
		this.dateCloture = dateCloture;
		return this;

	}

	/**
	public static DossierResource fromEntity(Dossier dossier){

		/**this.dateValidation = dossier.getDateValidation();

		String dateValidation = (dossier.getDateValidation() !=null )
				? dossier.getDateValidation().format(formatter) :null;

		String dateCloture = (dossier.getDateCloture() != null )
				? dossier.getDateCloture().format(formatter) :null;

*/
	/**
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
		return new DossierResource(dossier.id(),
				dossier.identifiant().identifiant(), dossier.requerantId(),
				dossier.dateEnregistrement(), dossier.status());
	}
	*/
	public static class DossierResourceBuilder {

		private String identifiant;
		private Long requerantId;
		private Long technicalId;
		private LocalDate dateEnregistrement;
		private DossierStatus status;
		private LocalDate dateValidation;
		private LocalDate dateCloture;

		public DossierResourceBuilder (Dossier dossier){
			this.identifiant = dossier.identifiant().getIdentifiant();
			this.requerantId = dossier.getRequerantId();
			this.technicalId = dossier.id();
			this.status = dossier.status();
			this.dateEnregistrement = dossier.dateEnregistrement();
			this.dateCloture = dossier.getDateCloture();

		}

		public DossierResourceBuilder dateValidation(LocalDate dateValidation){
			this.dateValidation = dateValidation;
			return this;
		}
		public DossierResourceBuilder dateCloture(LocalDate dateCloture){
			this.dateCloture = dateCloture;
			return this;
		}

		public DossierResource build () {
			return new DossierResource(this);
		}

	}
}
