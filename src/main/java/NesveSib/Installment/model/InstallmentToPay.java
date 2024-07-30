package NesveSib.Installment.model;

import lombok.Getter;

import java.util.Date;


@Getter
public class InstallmentToPay {


    private final double installmentAmount;
    private final Date installmentDate;
    private boolean paid;

    public InstallmentToPay(double installmentAmount, Date installmentDate) {
        this.installmentAmount = installmentAmount;
        this.installmentDate = installmentDate;
        this.paid = false;
    }

    public void installmentPaid() {
        this.paid = true;
    }


}
