package NesveSib.Installment.model.productModels;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "general_product_tbl")
public class Product {

    @Id
//    @GeneratedValue
    //TODO: implementation of generating unique keys for each product in database
    @Column(name = "product_code")
    private Integer productCode;

    @NonNull
    @Column(name = "product_name")
    private String productName;

    @NonNull
    @Column(name = "product_category")
    private String productCategory;

    @NonNull
    @Column(name = "product_model")
    private String productModel;

    @NonNull
    @Column(name = "product_serial_number")
    private String productSerialNumber; // if the product is mobile it has to be first IMEI

    @Column(name = "product_imei")
    private String productImei;

    @NonNull
    @Column(name = "product_color")
    private String productColor;


    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", productCategory='" + productCategory + '\'' +
                ", productModel='" + productModel + '\'' +
                ", productSerialNumber='" + productSerialNumber + '\'' +
                ", productColor='" + productColor + '\'' +
                '}';
    }
}
