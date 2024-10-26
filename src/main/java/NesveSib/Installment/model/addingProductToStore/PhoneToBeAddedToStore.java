package NesveSib.Installment.model.addingProductToStore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PhoneToBeAddedToStore {

    private final String phoneName;

    private final PhoneHealth phoneHealth;

    private final String phoneModel;

    private final PhoneStorage phoneStorage;

    private final String phoneColor;

    private final String phonePartNumber;

    private final Long phonePurchasedBySellerCost;

    private final Long phoneBatteryHealth;

    private final String firstIMEI;

    private final String secondIMEI;

    @Override
    public String toString() {
        return "PhoneAddedToStore{" +
                "phoneHealthType=" + phoneHealth +
                ", phoneModel='" + phoneModel + '\'' +
                ", phoneStorage=" + phoneStorage +
                ", phoneColor='" + phoneColor + '\'' +
                ", phonePartNumber='" + phonePartNumber + '\'' +
                ", phonePurchasedBySellerCost=" + phonePurchasedBySellerCost +
                ", firstIMEI=" + firstIMEI +
                ", secondIMEI=" + secondIMEI +
                ", phoneBatteryHealth=" + phoneBatteryHealth + "%" +
                '}';
    }
}



