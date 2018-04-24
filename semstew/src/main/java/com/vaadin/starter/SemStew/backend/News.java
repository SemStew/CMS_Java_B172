package com.vaadin.starter.SemStew.backend;


public class News {

  private long idNews;
  private java.sql.Date nDate;


  public long getIdNews() {
    return idNews;
  }

  public void setIdNews(long idNews) {
    this.idNews = idNews;
  }


  public java.sql.Date getNDate() {
    return nDate;
  }

  public void setNDate(java.sql.Date nDate) {
    this.nDate = nDate;
  }

}
