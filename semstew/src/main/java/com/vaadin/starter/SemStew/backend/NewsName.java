package com.vaadin.starter.SemStew.backend;


public class NewsName {

  private long idNews;
  private long idLanguage;
  private String description;


  public long getIdNews() {
    return idNews;
  }

  public void setIdNews(long idNews) {
    this.idNews = idNews;
  }


  public long getIdLanguage() {
    return idLanguage;
  }

  public void setIdLanguage(long idLanguage) {
    this.idLanguage = idLanguage;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
