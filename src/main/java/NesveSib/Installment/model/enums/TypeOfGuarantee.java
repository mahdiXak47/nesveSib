package NesveSib.Installment.model.enums;

public enum TypeOfGuarantee {
    CHEK,
    SAFTE;

    public static TypeOfGuarantee evaluate(String type) {
        if (type.equals("سفته"))
            return TypeOfGuarantee.CHEK;
        else if (type.equals("چک")) {
            return TypeOfGuarantee.SAFTE;
        }
        else return null;
    }
}
