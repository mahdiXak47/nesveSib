package NesveSib.Installment.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "simple_user")
public class SimpleUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "first_name", nullable = false)
    private String firstName;
    
    @Column(name = "last_name", nullable = false)
    private String lastName;
    
    @Column(name = "username" , nullable = false, unique = true)
    private String username;
    
    @Column(name = "password" , nullable = false)
    private String password;
}