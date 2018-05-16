package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.OrdersRecord;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrdersRepresentation {

    // Order
    private Integer idOrder;
    private Integer idBranch;
    private Date date;
    private String person;
    private String address;
    private String email;
    private String status;

    // Ordered items
    List<MenuItemRepresentation> orderedItems = new ArrayList<>();


    public OrdersRepresentation(){}

    public void LoadData(OrdersRecord inOrder, List<MenuItemRepresentation> inOrderedItems){
        idOrder = inOrder.getIdOrder();
        idBranch = inOrder.getIdBranch();
        date = inOrder.getODate();
        person = inOrder.getPerson();
        address = inOrder.getAddress();
        email = inOrder.getEmail();
        status = inOrder.getStatus();

        orderedItems = inOrderedItems;
    }

    public OrdersRecord getOrderRecord(){
        OrdersRecord tmp = new OrdersRecord();

        tmp.setIdOrder(idOrder);
        tmp.setIdBranch(idBranch);
        tmp.setODate(date);
        tmp.setPerson(person);
        tmp.setAddress(address);
        tmp.setEmail(email);
        tmp.setStatus(status);

        return tmp;
    }

    public Integer getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    public Integer getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Integer idBranch) {
        this.idBranch = idBranch;
    }

    public LocalDate getDate() {
        return date.toLocalDate();
    }

    public void setDate(LocalDate date) {
        this.date = Date.valueOf(date);
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<MenuItemRepresentation> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(List<MenuItemRepresentation> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
