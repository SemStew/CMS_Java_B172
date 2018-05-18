package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.NewsNameRecord;
import JOOQ.tables.records.NewsRecord;

import java.sql.Date;
import java.time.LocalDate;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class NewsRepresentation {

    /**
     * News id
     */
    private Integer newsId;
    /**
     * News restaurant id
     */
    private Integer restaurantId;
    /**
     * News Date
     */
    private Date nDate;

    /**
     * NewsName language id
     */
    private Integer languageId;
    /**
     * NewsName header
     */
    private String header;
    /**
     * NewsName description
     */
    private String description;

    /**
     * NewsRepresentation constructor
     */
    public NewsRepresentation() {}

    /**
     * Load data into NewsRepresentation
     *
     * Use {@link #Load(NewsRecord, NewsNameRecord)} to load given data into NewsRepresentation from database
     *
     * @param newsRecord NewsRecord data part
     * @param newsNameRecord NewsNameRecord data part
     */
    public void Load(NewsRecord newsRecord, NewsNameRecord newsNameRecord){
        newsId = newsRecord.getIdNews();
        restaurantId = newsRecord.getIdRestaurant();
        nDate = newsRecord.getNDate();

        languageId = newsNameRecord.getIdLanguage();
        header = newsNameRecord.getHeader();
        description = newsNameRecord.getDescription();
    }

    /**
     * Get NewsRecord date part
     *
     * Use {@link #getNews()} to get NewsRecord data part from NewsRepresentation
     *
     * @return NewsRecord data part from NewsRepresentation
     */
    public NewsRecord getNews(){
        NewsRecord tmp = new NewsRecord();
        tmp.setIdNews(newsId);
        tmp.setIdRestaurant(restaurantId);
        tmp.setNDate(nDate);

        return tmp;
    }

    /**
     * Get NewsNameRecord data part
     *
     * Use {@link #getNewsName()} to get NewsNameRecord data part from NewsRepresentation
     *
     * @return NewsNameRecord data part from NewsRepresentation
     */
    public NewsNameRecord getNewsName(){
        NewsNameRecord tmp = new NewsNameRecord();
        tmp.setIdNews(newsId);
        tmp.setIdLanguage(languageId);
        tmp.setHeader(header);
        tmp.setDescription(description);

        return tmp;
    }

    /**
     * Getter newsId
     * @return newsId
     */
    public Integer getNewsId() {
        return newsId;
    }

    /**
     * Setter for newsId
     * @param newsId integer to set newsId
     */
    public void setNewsId(Integer newsId) {
        this.newsId = newsId;
    }

    /**
     * Getter restaurantId
     * @return restaurantId
     */
    public Integer getRestaurantId() {
        return restaurantId;
    }

    /**
     * Setter for restaurantId
     * @param restaurantId integer to set restaurantId
     */
    public void setRestaurantId(Integer restaurantId) {
        this.restaurantId = restaurantId;
    }

    /**
     * Getter nDate
     * @return nDate
     */
    public LocalDate getnDate() {
        return nDate.toLocalDate();
    }

    /**
     * Setter for nDate
     * @param nDate LocalDate to set nDate
     */
    public void setnDate(LocalDate nDate) {
        this.nDate = Date.valueOf(nDate);
    }

    /**
     * Getter languageId
     * @return languageId
     */
    public Integer getLanguageId() {
        return languageId;
    }

    /**
     * Setter for languageId
     * @param languageId integer to set languageId
     */
    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    /**
     * Getter header
     * @return header
     */
    public String getHeader() {
        return header;
    }

    /**
     * Setter for header
     * @param header string to set header
     */
    public void setHeader(String header) {
        this.header = header;
    }

    /**
     * Getter description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description string to set description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
