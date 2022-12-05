package com.shop.mapper;

import com.shop.dto.ItemImgDto;
import com.shop.entity.ItemImg;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemImgMapper extends GenericMapper<ItemImgDto, ItemImg> {

}
