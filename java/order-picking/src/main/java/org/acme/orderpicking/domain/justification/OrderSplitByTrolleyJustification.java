package org.acme.orderpicking.domain.justification;

import java.util.Objects;

import ai.timefold.solver.core.api.score.stream.ConstraintJustification;

import org.acme.orderpicking.domain.Order;

public record OrderSplitByTrolleyJustification(String orderId, long trolleySpreadCount,
        String description) implements ConstraintJustification {

    public OrderSplitByTrolleyJustification {
        Objects.requireNonNull(orderId);
        Objects.requireNonNull(description);
    }

    public OrderSplitByTrolleyJustification(Order order, long trolleySpreadCount) {
        this(order.getId(), trolleySpreadCount,
                "Order %s is split across %d trolleys.".formatted(order.getId(), trolleySpreadCount));
    }
}
