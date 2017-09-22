package com.shakeup.nytimemachine.commons.models;

import com.shakeup.nytimemachine.commons.api.ArticleResponse;
import com.shakeup.nytimemachine.commons.api.MultimediaResponse;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Jayson on 9/20/2017.
 * <p>
 * Model for New York Times Articles
 */

public final class Article {
    private String webUrl;
    private HashMap<String, String> imgUrlMap = new HashMap<>();
    private String snippet;
    private String headline;
    private String pubDate;
    private String byLine;
    private String documentType;
    private String newsDesk;

    private final String IMG_BASE_URL = "www.nytimes.com/";

    public Article(ArticleResponse articleResponse) {
        this.webUrl = articleResponse.web_url;
        parseImageUrl(articleResponse.multimedia);
        this.snippet = articleResponse.snippet;
        this.headline = articleResponse.headline.main;
        this.pubDate = articleResponse.pub_date;
        this.byLine = articleResponse.byline.original;
        this.documentType = articleResponse.document_type;
        this.newsDesk = articleResponse.new_desk;
    }

    private void parseImageUrl(List<MultimediaResponse> multimediaResponseList) {
        if (multimediaResponseList.isEmpty()) {
            return;
        }
        for (MultimediaResponse media : multimediaResponseList) {
            imgUrlMap.put(media.subtype, media.url);
        }
    }

    public boolean hasImages() {
        return !imgUrlMap.isEmpty();
    }



    /*
     * GETTERS AND SETTERS
     */

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getImgUrlThumbnail() {
        return formatImgUrl(imgUrlMap.get("thumbnail"));
    }

    public String getImgUrlWide() {
        return formatImgUrl(imgUrlMap.get("wide"));
    }

    public String getImgUrlXLarge() {
        return formatImgUrl(imgUrlMap.get("xlarge"));
    }

    private String formatImgUrl(String url) {
        return String.format("%s%s", IMG_BASE_URL, url);
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
