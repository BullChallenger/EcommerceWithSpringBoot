package com.shop.service;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemImgDto;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.mapper.ItemImgMapper;
import com.shop.mapper.ItemMapper;
import com.shop.repository.ItemImgRepository;
import com.shop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.jboss.jandex.Main;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemImgRepository itemImgRepository;
    private final ItemImgService itemImgService;
    private final ItemMapper itemMapper;
    private final ItemImgMapper itemImgMapper;

    public Long saveItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {
        Item item = itemMapper.toEntity(itemFormDto);
        itemRepository.save(item);

        for (int i = 0; i < itemImgFileList.size(); i++) {
            ItemImg itemImg = new ItemImg();
            itemImg.setItem(item);

            if (i == 0) { itemImg.setRepImgYn("Y"); }
            else { itemImg.setRepImgYn("N"); }

            itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
        }

        return item.getId();
    }

    @Transactional(readOnly = true)
    public ItemFormDto getItemDtl(Long itemId) {

        List<ItemImg> itemImgList = itemImgRepository.findByItemIdOrderByIdAsc(itemId);
        List<ItemImgDto> itemImgDtoList = new ArrayList<ItemImgDto>();

        for (ItemImg itemImg : itemImgList) {
            System.out.println("itemImg Name : " + itemImg.getImgName());
            System.out.println("itemImg Original Name : " + itemImg.getOriImgName());
            System.out.println("itemImg Original Name : " + itemImg.getImgUrl());
        }
        itemImgDtoList = itemImgMapper.toDtoList(itemImgList);

        for (ItemImgDto itemImgDto : itemImgDtoList) {
            System.out.println("itemImgDto Name : " + itemImgDto.getImgName());
            System.out.println("itemImgDto Original Name : " + itemImgDto.getOriImgName());
            System.out.println("itemImgDto Original Name : " + itemImgDto.getImgUrl());
        }

        Item item = itemRepository.findById(itemId).orElseThrow(EntityNotFoundException::new);
        ItemFormDto itemFormDto = itemMapper.toDto(item);

        itemFormDto.setItemImgDtoList(itemImgDtoList);

        return itemFormDto;
    }

    public Long updateItem(ItemFormDto itemFormDto, List<MultipartFile> itemImgFileList) throws Exception {

        Item item = itemRepository.findById(itemFormDto.getId()).orElseThrow(EntityNotFoundException::new);
        item.updateItem(itemFormDto);

        List<Long> itemImgIds = itemFormDto.getItemImgIds();

        for (int i = 0; i < itemImgFileList.size(); i++) {
            itemImgService.updateItemImg(itemImgIds.get(i), itemImgFileList.get(i));
        }

        return item.getId();
    }

    @Transactional(readOnly = true)
    public Page<Item> getAdminPage(ItemSearchDto itemSearchDto, Pageable pageable) {

        return itemRepository.getAdminItemPage(itemSearchDto, pageable);
    }

    @Transactional(readOnly = true)
    public Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable) {

        return itemRepository.getMainItemPage(itemSearchDto, pageable);
    }
}
