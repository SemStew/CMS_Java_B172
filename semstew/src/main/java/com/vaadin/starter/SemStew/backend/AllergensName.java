package com.vaadin.starter.SemStew.backend;


public class AllergensName {

  private long idAllergen;
  private long idLanguage;
  private String allergen;


  public long getIdAllergen() {
    return idAllergen;
  }

  public void setIdAllergen(long idAllergen) {
    this.idAllergen = idAllergen;
  }


  public long getIdLanguage() {
    return idLanguage;
  }

  public void setIdLanguage(long idLanguage) {
    this.idLanguage = idLanguage;
  }


  public String getAllergen() {
    return allergen;
  }

  public void setAllergen(String allergen) {
    this.allergen = allergen;
  }

}
