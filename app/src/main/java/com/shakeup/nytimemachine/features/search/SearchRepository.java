package com.shakeup.nytimemachine.features.search;

import com.shakeup.nytimemachine.commons.models.Article;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jayson on 9/20/2017.
 *
 * Handles fetching and providing of Article Searches
 */

class SearchRepository {

    public List<Article> getSearchArticles(){
        return null;
    }

    /**
     * Creates a dummy list of Articles
     * @return list of Articles
     */
    public List<Article> getDummySearchArticles(){
        List<Article> articleList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            Article article = new Article();

            article.setWebUrl("https://www.nytimes.com/aponline/2017/09/20/sports/ap-car-indycar-ganassi-downsizing.html");
            article.setImgUrl("images/2017/09/21/arts/21jumanji-trailer/21jumanji-trailer-thumbWide.jpg");
            article.setHeadline("Chip Ganassi Racing to Downsize to 2 Cars in IndyCar in 2018.");
            article.setSnippet("Chip Ganassi Racing will return to a two-car team in the IndyCar Series next season.");
            article.setByLine("Jayson Dela Cruz");
            article.setDocumentType("Article");
            article.setNewsDesk("None");
            article.setPubDate("09-20-2017");

            articleList.add(article);
        }

        return articleList;
    }

}
