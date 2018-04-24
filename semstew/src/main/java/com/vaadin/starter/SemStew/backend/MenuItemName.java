package com.vaadin.starter.SemStew.backend;


public class MenuItemName {

  private long idMenuItem;
  private long idLanguage;
  private String name;
  private String description;


  public long getIdMenuItem() {
    return idMenuItem;
  }

  public void setIdMenuItem(long idMenuItem) {
    this.idMenuItem = idMenuItem;
  }


  public long getIdLanguage() {
    return idLanguage;
  }

  public void setIdLanguage(long idLanguage) {
    this.idLanguage = idLanguage;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
