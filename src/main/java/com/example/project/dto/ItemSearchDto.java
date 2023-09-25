package com.example.project.dto;

import com.example.project.domain.items.ItemSellStatus;
import lombok.Data;

@Data
public class ItemSearchDto {
    private String searchDateType;
    private ItemSellStatus searchSellStatus;
    private String searchBy;
    private String searchQuery = "";
}
