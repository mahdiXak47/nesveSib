package NesveSib.Installment.respository;

import NesveSib.Installment.model.InstallmentInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentInformationRepository extends JpaRepository<InstallmentInformation, Integer> {
}
