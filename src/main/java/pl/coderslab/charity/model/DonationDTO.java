package pl.coderslab.charity.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.entity.Category;
import pl.coderslab.charity.entity.Institution;
import pl.coderslab.charity.entity.User;
import pl.coderslab.charity.validation.DateWhenPickedUpChecked;
import pl.coderslab.charity.validation.NoDateWhenCheckboxIsChecked;
import pl.coderslab.charity.validation.ValidationGroupChangePickUpDetails;
import pl.coderslab.charity.validation.ValidationGroupCreateDonation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@DateWhenPickedUpChecked(
        groups = {ValidationGroupChangePickUpDetails.class},
        isChecked = "pickedUp",
        actualPickedUpDate = "takeOverDate",
        message = "Gdy zaznaczono \"Odebrane\", podaj także datę!"
)
@NoDateWhenCheckboxIsChecked(
        groups = {ValidationGroupChangePickUpDetails.class},
        isChecked = "pickedUp",
        actualPickedUpDate = "takeOverDate",
        message = "Gdy odznaczono \"Odebrane\", wyczyść pole z datą!"
)
public @Data class DonationDTO {

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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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


}
