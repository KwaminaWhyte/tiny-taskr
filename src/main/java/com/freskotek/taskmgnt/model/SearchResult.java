package com.freskotek.taskmgnt.model;

public class SearchResult {
    private String type; // The type of the search result (e.g. "task", "board")
    private String name; // The name of the search result
    private String url; // The URL for the search result
    private String id; // The task Id or Board ID

    public SearchResult(String type, String name, String url, String id) {
        this.type = type;
        this.name = name;
        this.url = url;
        this.id = id;
    }

    public SearchResult() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public  String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }
}
