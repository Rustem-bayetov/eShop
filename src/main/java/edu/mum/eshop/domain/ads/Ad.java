package edu.mum.eshop.domain.ads;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
@Data @Entity
public class Ad {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Integer id;
    @NotBlank
    private String description;
    @NotBlank
    private String adImage;
}
