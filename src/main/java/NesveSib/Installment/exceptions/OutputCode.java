package NesveSib.Installment.exceptions;

public enum OutputCode {
    ERROR_5001("a seller with this national id exists!"),
    ERROR_4001("Seller do not have panel in nesve sib system!"),
    ERROR_4002("Customer do not have panel in nesve sib system!"),
    ERROR_4003("Customer do not have valid guarantee"),

    SUCCESS_2001("operation completed successfully!"),
    ;

    private final String codeMessage;

    OutputCode(String codeMessage) {
        this.codeMessage = codeMessage;
    }
}
