package NesveSib.Installment.conttroller;


import NesveSib.Installment.dataProcessingModel.ProductPurchaseInputs;
import NesveSib.Installment.exceptions.OutputCode;
import NesveSib.Installment.service.ProductPurchaseService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer_product_purchase")
public class ProductPurchaseByCustomer {

    private final Logger logger = ProjectInternalTools.logger;

    private final ProductPurchaseService productPurchaseService;

    public ProductPurchaseByCustomer(ProductPurchaseService productPurchaseService) {
        this.productPurchaseService = productPurchaseService;
    }

    @PostMapping("/buy_product")
    public ResponseEntity<String> buyProductDirectly(@RequestBody ProductPurchaseInputs input) {
        if (productPurchaseService.checkPurchaseRequirements(input)) {
            logger.info("buyProductDirectly: all inputs checked successfully");
            productPurchaseService.directPurchaseProduct(input);
            logger.info("buyProductDirectly: data saved in database successfully");
            return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
        }
        logger.info("buyProductDirectly: inputs are not completely filled");
        return ResponseEntity.ok(OutputCode.ERROR_4030.getCodeMessage());
    }

    @PostMapping("/buy_product_in_installment")
    public ResponseEntity<String> buyProductInInstallment(@RequestBody ProductPurchaseInputs input, @RequestParam Integer numberOfInstallments) {
        if (productPurchaseService.checkPurchaseRequirements(input)) {
            logger.info("buyProductInInstallment: all inputs checked successfully");
            productPurchaseService.installmentPurchaseProduct(input,numberOfInstallments);
            logger.info("buyProductInInstallment: data saved in database successfully");
            return ResponseEntity.ok(OutputCode.SUCCESS_2001.getCodeMessage());
        }
        logger.info("buyProductInInstallment: inputs are not completely filled");
        return ResponseEntity.ok(OutputCode.ERROR_4030.getCodeMessage());
    }


    /*TODO: - add the purchased product to customer panel
    *       - show the installments of the purchased product in customer panel and seller panel
    *       - writing the product purchase invoices and send it for customer and seller in .pdf file
    *       - */
}
