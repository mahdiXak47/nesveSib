package NesveSib.Installment.requestInputs;

import lombok.Getter;

@Getter
public class CompleteSellerAccountForm {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String storeName;
    private final String storeAddress;
    private final String storePlateNumber;
    private final String storePlace;

    public CompleteSellerAccountForm(String firstName, String lastName, String email, String storeName, String storeAddress, String storePlateNumber, String storePlace) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.storeName = storeName;
        this.storeAddress = storeAddress;
        this.storePlateNumber = storePlateNumber;
        this.storePlace = storePlace;
    }
}
