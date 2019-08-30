package pl.coderslab.charity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.charity.entity.Donation;

import java.util.List;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    Long sumHandOverBags();

    @Query("SELECT COUNT (DISTINCT d.institution) FROM Donation d")
    Long sumSupportedInstitutions();

    List<Donation> findDonationsByUser_IdOrderByCreatedDesc(long userId);
    List<Donation> findDonationsByUser_IdOrderByPickedUpDesc(long userId);
    List<Donation> findDonationsByUser_IdOrderByTakeOverDateDesc(long userId);
}
