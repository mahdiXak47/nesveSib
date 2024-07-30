package NesveSib.Installment.conttroller;


import NesveSib.Installment.model.InstallmentCalculationInput;
import NesveSib.Installment.service.InstallmentCalculatorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("installment-settings")
public class InstallmentController {

    private final InstallmentCalculatorService calculatorService;

    public InstallmentController(InstallmentCalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }


    @PostMapping("/installment-calculation")
    public Double installmentCalculation(@RequestBody InstallmentCalculationInput input) {
        return calculatorService.calculationOfInstallmentPayment(input.numOfInstallments(),input.amountToCalculate(),input.guarantee());
    }

    /*TODO:
    *  - getting existed installment
    *  - creating new installment for a product
    *  - editing some installments about a product
    *  - terminating existed installment after settlement
    */
}
