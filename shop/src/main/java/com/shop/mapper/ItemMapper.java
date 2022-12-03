package com.shop.mapper;

import com.shop.dto.ItemFormDto;
import com.shop.entity.Item;
import org.mapstruct.Mapper;

@Mapper
public interface ItemMapper extends GenericMapper<ItemFormDto, Item> {

}
