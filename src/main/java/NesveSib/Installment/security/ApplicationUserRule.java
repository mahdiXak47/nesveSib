package NesveSib.Installment.security;

import com.google.common.collect.Sets;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static NesveSib.Installment.security.ApplicationUserPermission.*;

@Getter
public enum ApplicationUserRule {
    CUSTOMER(Sets.newHashSet(CUSTOMER_WRITE, CUSTOMER_READ)),
    SELLER(Sets.newHashSet(CUSTOMER_READ, SELLER_READ, SELLER_WRITE)),
    ADMIN(Sets.newHashSet(CUSTOMER_READ, SELLER_READ, SELLER_WRITE, CUSTOMER_WRITE)),
    ;

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRule(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> permissions = getPermissions().stream()
                .map(permission -> new SimpleGrantedAuthority(permission.toString()))
                .collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return permissions;
    }
}
