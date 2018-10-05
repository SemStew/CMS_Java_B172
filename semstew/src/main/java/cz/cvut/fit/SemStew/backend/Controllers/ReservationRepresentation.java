package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.ReservationRecord;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class ReservationRepresentation {

    /**
     * reservation id
     */
    private Integer reservationId;
    /**
     * reservation branch id
     */
    private Integer branchId;
    /**
     * reservation table number
     */
    private Integer tableNum;
    /**
     * reservation person
     */
    private String person;
    /**
     * reservation email
     */
    private String email;
    /**
     * reservation status
     */
    private String status;
    /**
     *  time and date
     */
    private LocalDateTime timeDate;

    /**
     * ReservationRepresentation constructor
     */
    public ReservationRepresentation(){}

    /**
     * Load date into ReservationRepresentation
     *
     * Use {@link #LoadData(ReservationRecord)} to load into ReservationRepresentation from given data part
     *
     * @param input ReservationRecord data part
     */
    public void LoadData(ReservationRecord input)
    {
        reservationId = input.getIdReservation();
        branchId = input.getIdBranch();
        tableNum = input.getNTable();
        person = input.getPerson();
        email = input.getEmail();
        status = input.getStatus();
        LocalDate datePart = input.getRDate().toLocalDate();
        LocalTime timePart = input.getTimeFrom().toLocalTime();
        timeDate = LocalDateTime.of(datePart, timePart);
    }

    /**
     * Load data into ReservationRepresentation by data entries
     *
     * Use {@link #LoadDate(int, int, String, String, String, String, LocalDate)} to load data into ReservationRepresentation
     * by data entries
     *
     * @param idBranch integer Branch identification number
     * @param tableN integer table number
     * @param person string Person name and surname
     * @param emailIn string email address
     * @param statusIn string current status
     * @param time string time from
     * @param date LocalDate reservation date
     */
    public void LoadDate(int idBranch, int tableN, String person, String emailIn,
                         String statusIn, String time, LocalDate date){
        branchId = idBranch;
        tableNum = tableN;
        this.person = person;
        email = emailIn;
        status = statusIn;
        String[] parts = time.split(":");
        Long milis = Long.parseLong(parts[0]) * 3600000 + Long.parseLong(parts[1]) * 60000;
        LocalTime timePart = new Time(milis).toLocalTime();
        timeDate = LocalDateTime.of(date,timePart);
    }

    /**
     * Get ReservationRecord from ReservationRepresentation
     *
     * Use {@link #GetReservationRecord()} to extract ReservationRecord data part from ReservationRepresentation
     *
     * @return extracted ReservationRecord
     */
    public ReservationRecord GetReservationRecord()
    {
        ReservationRecord tmp = new ReservationRecord();
        tmp.setIdReservation(reservationId);
        tmp.setIdBranch(branchId);
        tmp.setNTable(tableNum);
        tmp.setPerson(person);
        tmp.setEmail(email);
        tmp.setStatus(status);
        tmp.setRDate(Date.valueOf(timeDate.toLocalDate()));
        tmp.setTimeFrom(Time.valueOf(timeDate.toLocalTime()));

        return tmp;
    }

    /**
     * Getter reservationId
     * @return reservationId
     */
    public Integer getReservationId() {
        return reservationId;
    }

    /**
     * Setter for reservationId
     * @param reservationId integer to set reservationId
     */
    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    /**
     * Getter branchId
     * @return branchId
     */
    public Integer getBranchId() {
        return branchId;
    }

    /**
     * Setter for branchId
     * @param branchId integer to set branchId
     */
    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    /**
     * Getter tableNum
     * @return tableNum
     */
    public Integer getTableNum() {
        return tableNum;
    }

    /**
     * Setter for tableNum
     * @param tableNum integer to set tableNum
     */
    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
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
     * Getter email
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter for email
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
     * Getter timeDate
     * @return timeDate
     */
    public LocalDateTime getTimeDate() {
        return timeDate;
    }

    /**
     * Setter for timeDate
     * @param timeDate LocalDateTime to set timeDate
     */
    public void setTimeDate(LocalDateTime timeDate) {
        this.timeDate = timeDate;
    }

    /**
     * Setter for timeDate
     * @param date LocalDate to set date part of timeDate
     * @param time string to set time part of timeDate
     */
    public void setTimeDate(LocalDate date, String time)
    {
        String[] parts = time.split(":");
        Long milis = Long.parseLong(parts[0]) * 3600000 + Long.parseLong(parts[1]) * 60000;
        LocalTime timePart = new Time(milis).toLocalTime();
        timeDate = LocalDateTime.of(date,timePart);
    }
}
