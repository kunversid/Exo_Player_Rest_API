package com.visionias.exoplayer;

public class ListItem {
    private String title;
    private String videoUrl;
    private String publishDate;
    private String thumbnail;


    public ListItem(String title, String videoUrl, String publishDate, String thumbnail) {
        this.title = title;
        this.videoUrl = videoUrl;
        this.publishDate = publishDate;
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
