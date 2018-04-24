package com.vaadin.starter.SemStew.backend;


public class MenuItem {

  private long idMenuItem;
  private long price;
  private String imageName;
  private String amount;
  private long idUnit;
  private long idCategory;


  public long getIdMenuItem() {
    return idMenuItem;
  }

  public void setIdMenuItem(long idMenuItem) {
    this.idMenuItem = idMenuItem;
  }


  public long getPrice() {
    return price;
  }

  public void setPrice(long price) {
    this.price = price;
  }


  public String getImageName() {
    return imageName;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }


  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }


  public long getIdUnit() {
    return idUnit;
  }

  public void setIdUnit(long idUnit) {
    this.idUnit = idUnit;
  }


  public long getIdCategory() {
    return idCategory;
  }

  public void setIdCategory(long idCategory) {
    this.idCategory = idCategory;
  }

}
