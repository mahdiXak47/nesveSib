package NesveSib.Installment.exceptions;

public enum OutputCode {
    ERROR_5001("a seller with this national id exists!"),
    ERROR_5002("a seller with this national id does not exist!"),
    ERROR_5004("a seller with this username does not exist!"),
    ERROR_5005("a seller with this email does not exist!"),
    ERROR_5006("a seller with this phone number does not exist!"),
    ERROR_5003("your password is incorrect!"),
    ERROR_4001("Seller do not have panel in nesve sib system!"),
    ERROR_4002("Customer do not have panel in nesve sib system!"),
    ERROR_4003("Customer do not have valid guarantee"),
    ERROR_4030("You most fill all of required parts to continue"),
    SUCCESS_2001("operation completed successfully!"),
    ERROR_4004("input is invalid, type email address of phoneNumber"),
    ERROR_9999("this part of code is not implemented yet!"),
    ERROR_8888("data not added to the database!")
    ;

    private final String codeMessage;

    public String getCodeMessage() {
        return codeMessage;
    }

    OutputCode(String codeMessage) {
        this.codeMessage = codeMessage;
    }
}
