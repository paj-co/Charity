package pl.coderslab.charity.entity;



import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
public @Data class Institution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;
    @NotBlank
    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "institution")
    private List<Donation> donations;

    private boolean activePartnership;

}
