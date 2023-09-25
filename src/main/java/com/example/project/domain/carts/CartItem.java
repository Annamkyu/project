package com.example.project.domain.carts;

import com.example.project.domain.BaseEntity;
import com.example.project.domain.items.Item;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "cart_item")
@Entity
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;

    @Builder
    public CartItem(Cart cart, Item item, int count){
        this.cart=cart;
        this.item=item;
        this.count=count;
    }

    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .item(item)
                .count(count)
                .build();

        return cartItem;
    }

    public void addCount(int count) {
        this.count += count;
    }

    public void updateCount(int count){
        this.count = count;
    }
}
