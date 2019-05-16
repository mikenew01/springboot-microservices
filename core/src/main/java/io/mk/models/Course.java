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
public class Course implements AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "ID_COURSE", nullable = false, unique = true)
    @NotNull
    private Long id;

    @NotNull(message = "The field title are blank")
    @Column(name = "TITLE", nullable = false)
    private String title;
}
