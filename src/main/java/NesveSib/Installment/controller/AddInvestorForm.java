package NesveSib.Installment.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddInvestorForm {

    private final String investorFirstName;

    private final String investorLastName;

    private final String investorPhoneNumber;

    private final String InvestmentAmount;
}
