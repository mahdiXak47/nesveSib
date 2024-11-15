package NesveSib.Installment.model.productModels;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@Table(name = "seller_store_phone_stock")
public class PhoneStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "code")
    private Integer phoneCode;

    @Column(name = "model")
    private String phoneModel;

    @Column(name = "condition")
    private String condition;

    @Column(name = "color")
    private String color;

    @Column(name = "part_number")
    private String partNumber;

    @Column(name = "storage")
    private String storage;

    @Column(name = "first_imei")
    private String firstImei;

    @Column(name = "second_imei")
    private String secondImei;

    @Column(name = "cost_for_seller")
    private String costForSeller;

    @Column(name = "seller_code")
    private Integer sellerCode;

    public PhoneStock() {
        System.out.println("system error in phone stock");
    }

    @Override
    public String toString() {
        return "PhoneStock{" +
                "phoneCode=" + phoneCode +
                ", phoneModel='" + phoneModel + '\'' +
                ", condition='" + condition + '\'' +
                ", color='" + color + '\'' +
                ", partNumber='" + partNumber + '\'' +
                ", storage='" + storage + '\'' +
                ", firstImei='" + firstImei + '\'' +
                ", secondImei='" + secondImei + '\'' +
                ", costForSeller='" + costForSeller + '\'' +
                '}';
    }
}
