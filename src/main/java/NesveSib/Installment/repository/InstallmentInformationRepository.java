package NesveSib.Installment.repository;

import NesveSib.Installment.model.InstallmentInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentInformationRepository extends JpaRepository<InstallmentInformation, Integer> {
}
