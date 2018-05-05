package cz.cvut.fit.SemStew.ui.customerviews.reservationlist;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

@Route(value = "reservations", layout = CustomerLayout.class)
@PageTitle("Reservations | Home")
public class ReservationList extends VerticalLayout {
    private final H2 header = new H2();
    private final DatePicker date = new DatePicker();
    private final TextField time = new TextField();
    private final TextField person = new TextField();
    private final TextField table = new TextField();
    private final Button confirm = new Button();

    public ReservationList()
    {
        init();
        addContent();

    }

    private void init()
    {
        setClassName("reservations");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.addClassName("form");
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        header.setText("Reservations");

        date.setLabel("Date:");
        time.setLabel("Time:");
        person.setLabel("Name and Surname:");
        table.setLabel("Table number:");

        confirm.setText("Confirm");
        confirm.addClassName("btn_style");

        content.add(header, date, time, person, table, confirm);

        add(content);
    }
}
