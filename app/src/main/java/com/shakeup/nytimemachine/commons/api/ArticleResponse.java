package com.shakeup.nytimemachine.commons.api;

import java.util.List;

/**
 * Created by Jayson on 9/22/2017.
 *
 * Models used to map Api Responses to POJOs
 */

class NytSearchResponse {
    public NytSearchResponseData response;
}

class NytSearchResponseData {
    public List<ArticleResponse> docs;
}

public class ArticleResponse {
    public String web_url;
    public String snippet;
    public String source;
    public HeadlineResponse headline;
    public String pub_date;
    public String document_type;
    public String new_desk;
    public String section_name;
    public BylineResponse byline;
    public String type_of_material;
    public String _id;
    public List<MultimediaResponse> multimedia;
}

