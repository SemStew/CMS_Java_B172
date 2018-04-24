package com.vaadin.starter.SemStew.backend;


public class ContactConfig {

  private long idLanguage;
  private String header;
  private String description;
  private String urlImage1;
  private String urlImage2;


  public long getIdLanguage() {
    return idLanguage;
  }

  public void setIdLanguage(long idLanguage) {
    this.idLanguage = idLanguage;
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


  public String getUrlImage1() {
    return urlImage1;
  }

  public void setUrlImage1(String urlImage1) {
    this.urlImage1 = urlImage1;
  }


  public String getUrlImage2() {
    return urlImage2;
  }

  public void setUrlImage2(String urlImage2) {
    this.urlImage2 = urlImage2;
  }

}
