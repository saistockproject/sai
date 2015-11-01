package com.app.smpt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.smpt.model.FeedItem;
import com.app.smpt.persistence.ItemsRepository;


@Service
public class ItemsRetriever {

    private ItemsRepository repository;

    @Autowired
    public ItemsRetriever(ItemsRepository repository) {
	this.repository = repository;
    }

    public List<FeedItem> get() {
	return repository.findAll();
    }

}
