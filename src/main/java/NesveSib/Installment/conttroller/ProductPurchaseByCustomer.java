package NesveSib.Installment.conttroller;


import NesveSib.Installment.dataProcessingModel.ProductPurchaseInputs;
import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.service.ProductPurchaseService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer_product_purchase")
public class ProductPurchaseByCustomer {

    private final ProductPurchaseService productPurchaseService;

    public ProductPurchaseByCustomer(ProductPurchaseService productPurchaseService) {
        this.productPurchaseService = productPurchaseService;
    }


    @PostMapping("/buy_product")
    public void buyProduct(@RequestBody ProductPurchaseInputs input) {
        if (productPurchaseService.checkPurchaseRequirements(input).equals(OutputCode.SUCCESS_2001))
            productPurchaseService.purchaseProduct(input);

    }


    /*TODO: - add the purchased product to customer panel
    *       - show the installments of the purchased product in customer panel and seller panel
    *       - writing the product purchase invoices and send it for customer and seller in .pdf file
    *       - */
}
