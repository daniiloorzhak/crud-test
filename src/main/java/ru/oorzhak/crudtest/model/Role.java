package ru.oorzhak.crudtest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Transient
    public static final Role ADMIN_ROLE = new Role(1L, "ROLE_ADMIN");
    @Transient
    public static final Role USER_ROLE = new Role(2L, "ROLE_USER");

    @Id
    private Long id;
    private String name;
}
