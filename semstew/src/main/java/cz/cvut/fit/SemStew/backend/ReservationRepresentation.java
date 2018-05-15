package cz.cvut.fit.SemStew.backend;

import JOOQ.tables.records.ReservationRecord;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ReservationRepresentation {

    // reservation
    private Integer reservationId;
    private Integer branchId;
    private Integer tableNum;
    private String person;
    private String email;
    private String status;
    private LocalDateTime timeDate;



    public ReservationRepresentation(){}

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

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getBranchId() {
        return branchId;
    }

    public void setBranchId(Integer branchId) {
        this.branchId = branchId;
    }

    public Integer getTableNum() {
        return tableNum;
    }

    public void setTableNum(Integer tableNum) {
        this.tableNum = tableNum;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
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

    public LocalDateTime getTimeDate() {
        return timeDate;
    }

    public void setTimeDate(LocalDateTime timeDate) {
        this.timeDate = timeDate;
    }

    public void setTimeDate(LocalDate date, String time)
    {
        String[] parts = time.split(":");
        Long milis = Long.parseLong(parts[0]) * 3600000 + Long.parseLong(parts[1]) * 60000;
        LocalTime timePart = new Time(milis).toLocalTime();
        timeDate = LocalDateTime.of(date,timePart);
    }
}
