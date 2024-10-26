package NesveSib.Installment.service;


import NesveSib.Installment.model.enums.TypeOfGuarantee;
import org.springframework.stereotype.Service;

@Service
public class InstallmentCalculatorService {

    // function to calculate the amount of installments for installment payment
    public double calculationOfInstallmentPayment(int numOfInstallments, double originalPrice, String type,int productInterestPerCent) {
        double calculatedValue = 0;
        originalPrice += originalPrice * productInterestPerCent / 100;
        double x = originalPrice;
        TypeOfGuarantee typeOfGuarantee = TypeOfGuarantee.evaluate(type);
        if (typeOfGuarantee.equals(TypeOfGuarantee.CHEK))
            calculatedValue = x + (x * 25 / 1000);
        else if (typeOfGuarantee.equals(TypeOfGuarantee.SAFTE))
            calculatedValue = x * 35 / 1000;
        calculatedValue = (calculatedValue * (numOfInstallments + 1) + x) / numOfInstallments;
        return calculatedValue;
    }


}
