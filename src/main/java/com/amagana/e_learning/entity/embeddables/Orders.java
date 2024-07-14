package com.amagana.e_learning.entity.embeddables;

import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@ToString
@Entity
public class Orders {
    @EmbeddedId
    private OrderId orderId;
    @Embedded
    private Address address;
    private String orderObservation;
    private double orderPrice;
    private int orderNumberPieces;
}
