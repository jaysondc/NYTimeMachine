package com.shakeup.nytimemachine.commons.models;

import com.squareup.moshi.Json;

/**
 * Created by Jayson on 9/20/2017.
 *
 * Model for New York Times Articles
 */

public final class Article {
    @Json(name="web_url") private String webUrl;
    private String imgUrl;
    private String snippet;
    private String headline;
    @Json(name="pub_date") private String pubDate;
    private String byLine;
    @Json(name="document_type") private String documentType;
    @Json(name="news_desk") private String newsDesk; // Category?

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getImgUrl() {
        return String.format("https://www.nytimes.com/%s", imgUrl);
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getByLine() {
        return byLine;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getNewsDesk() {
        return newsDesk;
    }

    public void setNewsDesk(String newDesk) {
        this.newsDesk = newDesk;
    }
}
