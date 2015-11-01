package com.app.smpt.common.quandl;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;

/**
 * Represents a single row of a QDataset result.
 */
public class QEntry {
    private String date;
    private ArrayList<String> row = new ArrayList<>();

    public QEntry(JSONArray entry) {
        this.date = entry.get(0).toString();
        
        for(Object eachValue : entry) {
            row.add(eachValue == null ? "null" : eachValue.toString());
        }
    }

    public String getDate() {
        return date;
    }

    public List<String> getRow() {
        return row;
    }
    
    @Override
    public String toString() {
        return row.toString();
    }
}
