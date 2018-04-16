package com.vaadin.starter.SemStew.backend;


public class Employee {

  private long idEmployee;
  private long perform;
  private String name;
  private String surname;
  private String phone;
  private String mail;


  public long getIdEmployee() {
    return idEmployee;
  }

  public void setIdEmployee(long idEmployee) {
    this.idEmployee = idEmployee;
  }


  public long getPerform() {
    return perform;
  }

  public void setPerform(long perform) {
    this.perform = perform;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getMail() {
    return mail;
  }

  public void setMail(String mail) {
    this.mail = mail;
  }

}
