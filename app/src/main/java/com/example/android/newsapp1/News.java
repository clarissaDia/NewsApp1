package com.example.android.newsapp1;

public class News {

    private String newsSection;

    private String sectionName;

    private String newsDate;

    private String newsTitle;

    private String newsUrl;

    public News (String section, String nameSection, String date, String title, String url) {
        newsSection = section;
        sectionName = nameSection;
        newsDate = date;
        newsTitle = title;
        newsUrl = url;
    }

    public String getNewsSection() {
        return newsSection;
    }

    public String getSectionName(){
        return sectionName;
    }

    public String getNewsDate() {
        return newsDate;
    }

    public String getTitle() {
        return newsTitle;
    }

    public String getUrl() {
        return newsUrl;
    }
}

