package com.vaadin.starter.SemStew.backend;


public class Rezervation {

  private java.sql.Date rDate;
  private java.sql.Time timeFrom;
  private long nTable;


  public java.sql.Date getRDate() {
    return rDate;
  }

  public void setRDate(java.sql.Date rDate) {
    this.rDate = rDate;
  }


  public java.sql.Time getTimeFrom() {
    return timeFrom;
  }

  public void setTimeFrom(java.sql.Time timeFrom) {
    this.timeFrom = timeFrom;
  }


  public long getNTable() {
    return nTable;
  }

  public void setNTable(long nTable) {
    this.nTable = nTable;
  }

}
