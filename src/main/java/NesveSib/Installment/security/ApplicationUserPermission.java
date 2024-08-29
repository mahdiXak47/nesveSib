package NesveSib.Installment.security;

import lombok.Getter;

@Getter
public enum ApplicationUserPermission {

    SELLER_WRITE("seller:write"),
    SELLER_READ("seller:read"),
    CUSTOMER_WRITE("customer:write"),
    CUSTOMER_READ("customer:read"),
    ADMIN_WRITE("admin:write"),
    ADMIN_READ("admin:read"),
    ;


    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

}
