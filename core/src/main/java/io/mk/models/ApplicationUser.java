package io.mk.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Builder
public class ApplicationUser implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "ID_USER", nullable = false, unique = true)
    @NotNull
    private Long id;

    @NotNull(message = "The field 'username' is mandatory")
    @Column(name = "USERNAME", nullable = false)
    private String username;

    @NotNull(message = "The field 'password' is mandatory")
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @NotNull(message = "The field 'role' is mandatory")
    @Column(name = "ROLE", nullable = false)
    private String role = "USER";

    public ApplicationUser(@NotNull(message = "The object 'user' is mandatory") ApplicationUser user) {
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.role = user.getRole();
        this.id = user.getId();
    }
}
