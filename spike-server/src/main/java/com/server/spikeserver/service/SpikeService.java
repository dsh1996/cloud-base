package com.server.spikeserver.service;

import com.server.spikeserver.entity.Item;

import java.util.List;

public interface SpikeService {

    /**
     * 秒杀商品
     * @param itemId
     * @param number
     * @return
     */
    Integer spikeItem(Long itemId, Integer number);

    void addItem(Item item);

    List<Item> getItems();

    Boolean remove();
}
