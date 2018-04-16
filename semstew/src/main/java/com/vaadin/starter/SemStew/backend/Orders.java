package com.vaadin.starter.SemStew.backend;


public class Orders {

  private long idOrder;
  private java.sql.Date oDate;
  private String address;


  public long getIdOrder() {
    return idOrder;
  }

  public void setIdOrder(long idOrder) {
    this.idOrder = idOrder;
  }


  public java.sql.Date getODate() {
    return oDate;
  }

  public void setODate(java.sql.Date oDate) {
    this.oDate = oDate;
  }


  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

}
