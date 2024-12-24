package NesveSib.Installment.model.productModels;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "seller_store_watch_stock")
public class Watch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer watchId;

    @Column(name = "watch_model")
    private String watchModel;

    @Column(name = "watch_color")
    private String watchColor;

    @Column(name = "watch_serial_number")
    private String watchSerialNumber;

    @Column(name = "cost_for_seller")
    private String costForSeller;

    @Column(name = "seller_store_code")
    private Integer sellerStoreCode;

    @Column(name = "is_sold")
    private boolean isWatchSoldOut;

    public Watch() {
        System.out.println("watch watch ! !");
    }
}
