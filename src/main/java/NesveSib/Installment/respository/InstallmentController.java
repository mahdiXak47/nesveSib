package NesveSib.Installment.respository;

import NesveSib.Installment.model.InstallmentInformation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstallmentController extends JpaRepository<InstallmentInformation, Long> {
}
