package com.example.project.domain.carts;

import com.example.project.domain.BaseEntity;
import com.example.project.domain.members.Member;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "cart")
@Entity
public class Cart extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cart_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @Builder
    public Cart(Member member){
        this.member = member;
    }

    public static Cart createCart(Member member) {
        Cart cart = Cart.builder()
                .member(member)
                .build();
        return cart;
    }
}
