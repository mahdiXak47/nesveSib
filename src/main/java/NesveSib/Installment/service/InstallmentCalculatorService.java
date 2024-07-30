package NesveSib.Installment.service;


import NesveSib.Installment.model.enums.TypeOfGuarantee;
import org.springframework.stereotype.Service;

@Service
public class InstallmentCalculatorService {

    // function to calculate the amount of installments for installment payment
    public double calculationOfInstallmentPayment(int numOfInstallments, double originalPrice, String type) {
        double calculatedValue = 0;
        originalPrice += originalPrice * 15 / 100;
        double x = originalPrice;
        if (type.equals(TypeOfGuarantee.CHEK.toString()))
            calculatedValue = x + (x * 25 / 1000);
        else if (type.equals(TypeOfGuarantee.SAFTE.toString()))
            calculatedValue = x * 35 / 1000;
        calculatedValue = (calculatedValue * (numOfInstallments + 1) + x) / numOfInstallments;
        return calculatedValue;
    }


}
