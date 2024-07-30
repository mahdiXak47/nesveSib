package NesveSib.Installment.model;


import NesveSib.Installment.model.enums.TypeOfGuarantee;


public record InstallmentCalculationInput(int numOfInstallments, double amountToCalculate, String guarantee) {

}
