package NesveSib.Installment.controller;


import NesveSib.Installment.model.dto.InstallmentCalculationInput;
import NesveSib.Installment.service.InstallmentCalculatorService;
import NesveSib.Installment.utils.ProjectInternalTools;
import ch.qos.logback.classic.Logger;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("installment-settings")
public class InstallmentController {

    private final Logger logger = ProjectInternalTools.getLogger(InstallmentController.class.getName());

    private final InstallmentCalculatorService calculatorService;

    public InstallmentController(InstallmentCalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @PostMapping("/installment-calculation")
    public Double installmentCalculation(@RequestBody InstallmentCalculationInput input) {
        return calculatorService.calculationOfInstallmentPayment(input.numOfInstallments(),input.amountToCalculate(),input.guarantee(),input.productInterestPerCent());
    }

    /*TODO:
    *  - getting existed installment
    *  - creating new installment for a product
    *  - editing some installments about a product
    *  - terminating existed installment after settlement
    */
}
