package pl.coderslab.charity.entity;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.validation.ValidationGroupCreateDonation;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
public @Data class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Pole nie może być puste")
    @Min(value = 1, message = "Przynajniej jeden worek")
    private Integer quantity;

    @NotEmpty(message = "Zaznacz chociaż jedną kategorię")
    @ManyToMany
    @JoinTable(name = "donations_categories", joinColumns = @JoinColumn(name ="donation_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @NotBlank(message = "Pole nie może być puste")
    private String street;
    @NotBlank(message = "Pole nie może być puste")
    private String city;
    @Pattern(regexp = "^$|^([0-9]){2}-([0-9]){3}$", message = "Niepoprawny format kodu pocztowego")
    @NotBlank(message = "Pole nie może być puste")
    private String zipCode;

    @NotNull(message = "Podaj datę")
    @DateTimeFormat(pattern = "yyyy-MM-dd") //iso znany format
    @Future(message = "Podaj datę z przyszłości")
    private LocalDate pickUpDate;
    @NotNull(message = "Podaj godzinę")
    private LocalTime pickUpTime;
    private String pickUpComment;

    @Pattern(regexp = "^$|^(\\+48|\\+48 )?(([0-9]{9}|[0-9]{2} [0-9]{3} [0-9]{2} [0-9]{2}|[0-9]{2} [0-9]{3}-[0-9]{2}-[0-9]{2})|([0-9]{3} [0-9]{3} [0-9]{3}|([0-9]{3}-[0-9]{3}-[0-9]{3}|([0-9]{3}))))$",
    message = "Niepoprawny format numeru telefonu")
    @NotBlank(message = "Pole nie może być puste")
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
