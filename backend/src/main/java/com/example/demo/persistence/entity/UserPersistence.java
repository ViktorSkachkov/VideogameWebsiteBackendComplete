package com.example.demo.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Entity
@Table(name = "user")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPersistence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @NotBlank
    @Length(min = 2, max = 250)
    @Column(name = "username")
    private String username;
    @NotBlank
    @Length(min = 2, max = 100)
    @Column(name = "email")
    private String email;
    @NotBlank
    @Length(min = 2, max = 250)
    @Column(name = "bank_account")
    private String bankAccount;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<RolePersistence> userRoles;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<GameOrderPersistence> gameOrders;
    @NotBlank
    @Length(min = 2, max = 200)
    @Column(name = "pwd")
    private String pwd;
    @Column(name = "deleted")
    @NotNull
    @EqualsAndHashCode.Exclude
    private Boolean deleted;
}

