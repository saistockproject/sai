package com.app.smpt.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.smpt.model.FeedData;
import com.app.smpt.model.FeedItem;
import com.app.smpt.persistence.ItemsRepository;
 

@Service
public class FeedReciever {
    private static final Logger LOGGER = LoggerFactory.getLogger(FeedReciever.class);
    private final ItemsExtractor extractor;
    private final ItemsRepository repository;

    @Autowired
    public FeedReciever(ItemsExtractor extractor, ItemsRepository repository) {
	this.extractor = extractor;
	this.repository = repository;
    }

    public void addFeed(String feedUrl) {
	LOGGER.info(String.format("going to add feed: %s", feedUrl));
	List<FeedItem> items = extractor.extractItems(feedUrl);
	repository.save(new FeedData(feedUrl, items));
    }
}
