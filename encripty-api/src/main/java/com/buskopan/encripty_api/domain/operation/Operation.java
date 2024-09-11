package com.buskopan.encripty_api.domain.operation;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "operations")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Operation {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "userdocument", nullable = false)
    private String userDocument;

    @Column(name = "creditcardtoken", nullable = false)
    private String credCardToken;

    @Column(name = "operationvalue", nullable = false)
    private Long value;
}
