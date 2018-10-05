package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.OrdersRecord;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class OrdersRepresentation {

    /**
     * Order id
     */
    private Integer idOrder;
    /**
     * Order branch id
     */
    private Integer idBranch;
    /**
     * Order date
     */
    private Date date;
    /**
     * Order person
     */
    private String person;
    /**
     * Order address
     */
    private String address;
    /**
     * Order email
     */
    private String email;
    /**
     * Order status
     */
    private String status;

    /**
     * Ordered items
     */
    List<MenuItemRepresentation> orderedItems = new ArrayList<>();

    /**
     * OrderRepresentation constructor
     */
    public OrdersRepresentation(){}

    /**
     * Load data into OrdersRepresentation
     *
     * Use {@link #LoadData(OrdersRecord, List)} to load data into OrdersRepresentation from given data parts
     *
     * @param inOrder OrdersRecord data part
     * @param inOrderedItems list of MenuItemRepresentation
     */
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

    /**
     * Get OrderRecord
     *
     * Use {@link #getOrderRecord()} to extract OrderRecord out of OrdersRepresentation
     *
     * @return OrderRecords extracted from OrdersRepresentation
     */
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

    /**
     * Getter idOrder
     * @return idOrder
     */
    public Integer getIdOrder() {
        return idOrder;
    }

    /**
     * Setter for idOrder
     * @param idOrder integer to set idOrder
     */
    public void setIdOrder(Integer idOrder) {
        this.idOrder = idOrder;
    }

    /**
     * Getter idBranch
     * @return idBranch
     */
    public Integer getIdBranch() {
        return idBranch;
    }

    /**
     * Setter for idBranch
     * @param idBranch integer to set idBranch
     */
    public void setIdBranch(Integer idBranch) {
        this.idBranch = idBranch;
    }

    /**
     * Getter date
     * @return date
     */
    public LocalDate getDate() {
        return date.toLocalDate();
    }

    /**
     * Setter for date
     * @param date LocalDate to set date
     */
    public void setDate(LocalDate date) {
        this.date = Date.valueOf(date);
    }

    /**
     * Getter person
     * @return person
     */
    public String getPerson() {
        return person;
    }

    /**
     * Setter for person
     * @param person string to set person
     */
    public void setPerson(String person) {
        this.person = person;
    }

    /**
     * Getter address
     * @return address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter for address
     * @param address string to set address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter email
     * @param email string to set email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Getter status
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for status
     * @param status string to set status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter orderedItems
     * @return list of MenuItemRepresentation
     */
    public List<MenuItemRepresentation> getOrderedItems() {
        return orderedItems;
    }

    /**
     * Setter fot OrderedItems
     * @param orderedItems list of MenuItemRepresentations to set OrderedItems
     */
    public void setOrderedItems(List<MenuItemRepresentation> orderedItems) {
        this.orderedItems = orderedItems;
    }
}
