package cz.cvut.fit.SemStew.ui.views.reservationslist;

import JOOQ.tables.records.ReservationRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.cvut.fit.SemStew.backend.ReservationController;
import cz.cvut.fit.SemStew.backend.ReservationRepresentation;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.ReservationService;
import cz.cvut.fit.SemStew.ui.MainLayout;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

import java.sql.Time;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.List;

@Route(value = "admin/reservations", layout = MainLayout.class)
@PageTitle("Reservations List | Admin")
public class ReservationsList extends GeneralAdminList {

    private final H2 header = new H2();
    private final Grid<ReservationRepresentation> recordGrid = new Grid<>();
    private final Dialog editDialog = new Dialog();
    private final ReservationController reservationController = new ReservationController();
    List<ReservationRepresentation> reservationRecords;

    public ReservationsList() {
        init();
        addContent();
    }

    private void init() {
        super.addClassName("reservations-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Reservations");

        Refresh();

        recordGrid.addColumn(ReservationRepresentation::getPerson).setComparator(Comparator.comparing(ReservationRepresentation::getPerson)).
                setHeader("Person").setSortable(true);
        recordGrid.addColumn(ReservationRepresentation::getEmail).setHeader("Email");
        recordGrid.addColumn(ReservationRepresentation::getTableNum).setHeader("Table");
        recordGrid.addColumn(new LocalDateTimeRenderer<>(ReservationRepresentation::getTimeDate,
                DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM))).setComparator(Comparator.comparing(ReservationRepresentation::getTimeDate)).
                setHeader("Date and time").setSortable(true);
        recordGrid.addColumn(ReservationRepresentation::getStatus).setComparator(Comparator.comparing(ReservationRepresentation::getStatus)).
                setHeader("Status").setSortable(true);

        editDialog.setCloseOnOutsideClick(false);
        editDialog.setCloseOnEsc(true);

        content.add(header, recordGrid);

        super.add(content);
    }

    private void Refresh(){
        reservationRecords = reservationController.getConfigs();
        recordGrid.setItems(reservationRecords);
    }



}