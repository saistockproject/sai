package com.app.smpt.persistence;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.app.smpt.model.FeedData;
import com.app.smpt.model.FeedItem;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
 
@Repository 
public class InMemoryItemsRepository implements ItemsRepository {
    private final Map<String, List<FeedItem>> db;

    public InMemoryItemsRepository() {
	db = Maps.newHashMap();
    }

    @Override
    public void save(FeedData data) {
	db.put(hasKeyForUrl(data.getFeedUrl()), data.getItems());
    }

    @Override
    public List<FeedItem> findAll() {
	List<FeedItem> result = Lists.newLinkedList();
	for (List<FeedItem> itemsForUrl : db.values()) {
	    result.addAll(itemsForUrl);
	}
	return result;
    }
    
    private String hasKeyForUrl(String feedUrl) {
	return feedUrl;
    }

}
