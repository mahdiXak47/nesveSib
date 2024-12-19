package NesveSib.Installment.model.addingProductToStore;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "seller_store_airpod_stock")
public class Airpod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer airpodId;

    @Column(name = "type")
    private String airpodType;

    @Column(name = "model")
    private String airpodModel;

    @Column(name = "color")
    private String airpodColor;

    @Column(name = "serial_number")
    private String airpodSerialNumber;

    @Column(name = "cost_for_seller")
    private String costForSeller;

    @Column(name = "seller_store_code")
    private Integer sellerStoreCode;


    public Airpod() {
        System.out.println("airpod airpod");
    }
}
