package com.vaadin.starter.SemStew.ui.views.reservationslist;

import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.ui.MainLayout;

@Route(value = "reservations",layout = MainLayout.class)
@PageTitle("ReservationsList")
public class ReservationsList extends VerticalLayout {
    private final H2 header = new H2();
    private final DatePicker dateSelect = new DatePicker();
    private final TextField timeSelect = new TextField();
    private final TextField tableSelect = new TextField();

    public ReservationsList(){
        init();
        addContent();
    }


    private void init()
    {
        addClassName("reservations-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("view-container");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Reservations");

        dateSelect.setLabel("Date:");
        timeSelect.setLabel("Time:");
        tableSelect.setLabel("Table:");

        content.add(header, dateSelect, timeSelect, tableSelect);
        add(content);
    }
}
