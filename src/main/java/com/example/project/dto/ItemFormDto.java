package com.example.project.dto;

import com.example.project.domain.items.Item;
import com.example.project.domain.items.ItemSellStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemFormDto {

    private Long id;

    @NotBlank(message = "상품명은 필수 입력 값입니다.")
    private String itemName;

    @NotNull(message = "가격은 필수 입력 값입니다.")
    private Integer price;

    @NotBlank(message = "상세 내용은 필수 입력 값입니다.")
    private String itemDetail;

    @NotNull(message = "재고는 필수 입력 값입니다.")
    private Integer stockNumber;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    public ItemFormDto(String itemName, Integer price, String itemDetail, Integer stockNumber,ItemSellStatus itemSellStatus){
        this.itemName=itemName;
        this.price=price;
        this.itemDetail=itemDetail;
        this.stockNumber=stockNumber;
        this.itemSellStatus=itemSellStatus;
    }

    public Item toEntity(ItemFormDto dto){
        Item entity = Item.builder()
                .itemName(dto.itemName)
                .itemDetail(dto.itemDetail)
                .itemSellStatus(dto.itemSellStatus)
                .price(dto.price)
                .stockNumber(dto.stockNumber)
                .build();

        return entity;
    }

    public static ItemFormDto of(Item entity){
        ItemFormDto dto = ItemFormDto.builder()
                .id(entity.getId())
                .itemName(entity.getItemName())
                .itemDetail(entity.getItemDetail())
                .itemSellStatus(entity.getItemSellStatus())
                .price(entity.getPrice())
                .stockNumber(entity.getStockNumber())
                .build();

        return dto;
    }
}
