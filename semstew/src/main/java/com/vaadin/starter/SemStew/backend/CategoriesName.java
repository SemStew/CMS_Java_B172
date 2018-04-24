package com.vaadin.starter.SemStew.backend;


public class CategoriesName {

  private long idCategory;
  private long idLanguage;
  private String description;


  public long getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(long idCategory) {
    this.idCategory = idCategory;
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
