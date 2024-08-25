package NesveSib.Installment.model.productModels;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@RequiredArgsConstructor
@Setter
@Entity
@Table(name = "generalproducttbl")
public class Product {

    @Id
    @Column(name = "productcode")
    private String productCode;

    @NonNull
    @Column(name = "productname")
    private final String productName;

    @NonNull
    @Column(name = "productprice")
    private final Double price;

    @NonNull
    @Column(name = "productcategory")
    private final String productCategory;

    @NonNull
    @Column(name = "productmodel")
    private final String productModel;

    @NonNull
    @Column(name = "productcolor")
    private final String productColor;

    @Override
    public String toString() {
        return "Product{" +
                "productCode='" + productCode + '\'' +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", productCategory='" + productCategory + '\'' +
                ", productModel='" + productModel + '\'' +
                ", productColor='" + productColor + '\'' +
                '}';
    }
}
