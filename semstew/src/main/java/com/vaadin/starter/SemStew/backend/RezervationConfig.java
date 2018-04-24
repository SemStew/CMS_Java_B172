package com.vaadin.starter.SemStew.backend;


public class RezervationConfig {

  private long idLanguage;
  private String header;
  private String tableNumber;
  private String timeFromDesc;


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


  public String getTableNumber() {
    return tableNumber;
  }

  public void setTableNumber(String tableNumber) {
    this.tableNumber = tableNumber;
  }


  public String getTimeFromDesc() {
    return timeFromDesc;
  }

  public void setTimeFromDesc(String timeFromDesc) {
    this.timeFromDesc = timeFromDesc;
  }

}
