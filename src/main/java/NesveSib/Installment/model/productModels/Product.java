package NesveSib.Installment.model.productModels;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "generalproducttbl")
public class Product {

    @Id
//    @GeneratedValue
    @Column(name = "productcode")
    private String productCode;

    @NonNull
    @Column(name = "productname")
    private String productName;

    @NonNull
    @Column(name = "productcategory")
    private String productCategory;

    @NonNull
    @Column(name = "productmodel")
    private String productModel;

    @NonNull
    @Column(name = "productserialnumber")
    private String productSerialNumber;

    @NonNull
    @Column(name = "productcolor")
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
