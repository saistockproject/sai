package com.app.smpt.persistence;

import java.util.List;

import com.app.smpt.model.FeedData;
import com.app.smpt.model.FeedItem;


public interface ItemsRepository {
    void save(FeedData data);

    List<FeedItem> findAll();
}
