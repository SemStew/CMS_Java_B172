package cz.cvut.fit.SemStew.backend;

import JOOQ.tables.records.NewsNameRecord;
import JOOQ.tables.records.NewsRecord;

import java.sql.Date;
import java.time.LocalDate;


public class NewsRepresentation {

    // News
    private Integer newsId;
    private Integer restaurantId;
    private Date nDate;

    // NewsName
    private Integer languageId;
    private String header;
    private String description;


    public NewsRepresentation() {}

    public void Load(NewsRecord newsRecord, NewsNameRecord newsNameRecord){
        newsId = newsRecord.getIdNews();
        restaurantId = newsRecord.getIdRestaurant();
        nDate = newsRecord.getNDate();

        languageId = newsNameRecord.getIdLanguage();
        header = newsNameRecord.getHeader();
        description = newsNameRecord.getDescription();
    }

    public NewsRecord getNews(){
        NewsRecord tmp = new NewsRecord();
        tmp.setIdNews(newsId);
        tmp.setIdRestaurant(restaurantId);
        tmp.setNDate(nDate);

        return tmp;
    }

    public NewsNameRecord getNewsName(){
        NewsNameRecord tmp = new NewsNameRecord();
        tmp.setIdNews(newsId);
        tmp.setIdLanguage(languageId);
        tmp.setHeader(header);
        tmp.setDescription(description);

        return tmp;
    }

    public Integer getNewsId() {
        return newsId;
    }

    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    public Integer getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    public LocalDate getnDate() {
        return nDate.toLocalDate();
    }

    public void setnDate(LocalDate nDate) {
        this.nDate = Date.valueOf(nDate);
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
