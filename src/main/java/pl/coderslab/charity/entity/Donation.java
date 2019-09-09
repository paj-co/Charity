package pl.coderslab.charity.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.validation.ValidationGroupCreateDonation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public @Data class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @ManyToMany
    @JoinTable(name = "donations_categories", joinColumns = @JoinColumn(name ="donation_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @NotBlank(groups = {ValidationGroupCreateDonation.class})
    private String street;
    @NotBlank(groups = {ValidationGroupCreateDonation.class})
    private String city;
    //TODO REGEX
    @NotBlank(groups = {ValidationGroupCreateDonation.class})
    private String zipCode;
    @NotNull(groups = {ValidationGroupCreateDonation.class})
    @DateTimeFormat(pattern = "yyyy-MM-dd") //iso znany format
    private LocalDate pickUpDate;
    @NotNull(groups = {ValidationGroupCreateDonation.class})
    private LocalTime pickUpTime;
    private String pickUpComment;
    @NotBlank(groups = {ValidationGroupCreateDonation.class})
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean pickedUp;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate takeOverDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate created;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfUserActualizationOfPickUpDetails;

    @PrePersist
    public void prePersist() {
        created = LocalDate.now();
    }


}
