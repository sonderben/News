package com.sonderben.news.models;

public class Article {
    private String title;
    private String description;
    private String urlImage;
    private String url;

    public Article(String url,String title, String description, String urlImage) {
        this.url = url;
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
    }

    public String getTitle() {
        return title;
    }
    public String getUrl() {
        return url;
    }



    public String getDescription() {
        return description;
    }



    public String getUrlImage() {
        return urlImage;
    }


}
