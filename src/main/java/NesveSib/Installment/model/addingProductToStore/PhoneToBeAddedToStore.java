package NesveSib.Installment.model.addingProductToStore;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PhoneToBeAddedToStore {

    private final String phoneModel;

    private final String phoneType;

    private final String phoneColor;

    private final String phonePartNumber;

    private final String phoneStorage;

    private final String phoneFirstIMEI;

    private final String phoneSecondIMEI;

    private final String phonePurchasedCost;

    @Override
    public String toString() {
        return "PhoneAddedToStore{" +
//                "phoneHealthType=" + phoneHealth +
                ", phoneModel='" + phoneModel + '\'' +
                ", phoneStorage=" + phoneStorage +
                ", phoneColor='" + phoneColor + '\'' +
                ", phonePartNumber='" + phonePartNumber + '\'' +
//                ", phonePurchasedBySellerCost=" + phonePurchasedBySellerCost +
//                ", firstIMEI=" + firstIMEI +
//                ", secondIMEI=" + secondIMEI +
//                ", phoneBatteryHealth=" + phoneBatteryHealth + "%" +
                '}';
    }
}



