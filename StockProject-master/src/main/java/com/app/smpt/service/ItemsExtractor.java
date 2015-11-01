package com.app.smpt.service;

import java.util.List;

import com.app.smpt.model.FeedItem;


public interface ItemsExtractor {

    List<FeedItem> extractItems(String feedUrl);

}
