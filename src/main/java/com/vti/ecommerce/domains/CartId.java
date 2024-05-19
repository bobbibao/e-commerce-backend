package com.vti.ecommerce.domains;
import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;

public class CartId implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "user_id")
    private String userId;

    public CartId() {
    }

    public CartId(Long productId, String userId) {
        this.productId = productId;
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        CartId cartId = (CartId) obj;
        return Objects.equals(productId, cartId.productId) && Objects.equals(userId, cartId.userId);
    }
}