package NesveSib.Installment.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddShopManForm {

    private final String shopManFirstName;

    private final String shopManLastName;

    private final String shopManPhoneNumber;

    private final String shopManNationalId;
}
