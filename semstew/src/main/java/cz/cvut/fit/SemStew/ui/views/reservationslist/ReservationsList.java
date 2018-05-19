package cz.cvut.fit.SemStew.ui.views.reservationslist;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateTimeRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.cvut.fit.SemStew.backend.Controllers.EmailController;
import cz.cvut.fit.SemStew.backend.Controllers.ReservationController;
import cz.cvut.fit.SemStew.backend.Controllers.ReservationRepresentation;
import cz.cvut.fit.SemStew.ui.MainLayout;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
@Route(value = "admin/reservations", layout = MainLayout.class)
@PageTitle("Reservations List | Admin")
public class ReservationsList extends GeneralAdminList {

    /**
     * page header
     */
    private final H2 header = new H2();
    /**
     * grid for displaying Reservations
     */
    private final Grid<ReservationRepresentation> recordGrid = new Grid<>();
    /**
     * Informative texts label
     */
    private final Label infoLabel = new Label();
    /**
     * Reservations management
     */
    private final ReservationController reservationController = new ReservationController();
    /**
     * Email management
     */
    private final EmailController emailController = new EmailController();
    /**
     * list of all ReservationRepresentation
     */
    private List<ReservationRepresentation> reservationRecords;

    /**
     * ReservationsList constructor
     *
     * Use {@link #ReservationsList()} to create and initialize page
     *
     */
    public ReservationsList() {
        init();
        addContent();
    }

    /**
     * Initialize page
     *
     * Use {@link #init()} to initialize page
     *
     */
    private void init() {
        super.addClassName("reservations-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    /**
     * Load content
     *
     * Use {@link #addContent()} to load and set up page content
     *
     */
    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Reservations");

        Refresh();

        recordGrid.addColumn(ReservationRepresentation::getPerson).setComparator(Comparator.comparing(ReservationRepresentation::getPerson)).
                setHeader("Person").setSortable(true).setResizable(true);
        recordGrid.addColumn(ReservationRepresentation::getEmail).setHeader("Email");
        recordGrid.addColumn(ReservationRepresentation::getTableNum).setHeader("Table").setResizable(true);
        recordGrid.addColumn(new LocalDateTimeRenderer<>(ReservationRepresentation::getTimeDate,
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))).setComparator(Comparator.comparing(ReservationRepresentation::getTimeDate)).
                setHeader("Date").setSortable(true).setResizable(true);
        recordGrid.addColumn(new LocalDateTimeRenderer<>(ReservationRepresentation::getTimeDate,
                DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM))).setHeader("Time").setResizable(true);
        recordGrid.addColumn(ReservationRepresentation::getStatus).setComparator(Comparator.comparing(ReservationRepresentation::getStatus)).
                setHeader("Status").setSortable(true).setResizable(true);
        recordGrid.addColumn(new ComponentRenderer<>(selectedItem ->{
            Button tmp = new Button(new Icon(VaadinIcons.CHECK));
            tmp.setText("Accept");
            tmp.addClickListener(buttonClickEvent -> {
               Accept(selectedItem);
            });
            return tmp;
        }));
        recordGrid.addColumn(new ComponentRenderer<>(selectedItem ->{
            Button tmp = new Button(new Icon(VaadinIcons.CLOSE_CIRCLE));
            tmp.setText("Decline");
            tmp.addClickListener(buttonClickEvent -> {
                Decline(selectedItem);
            });
            return tmp;
        }));
        recordGrid.addColumn(new ComponentRenderer<>(selectedItem -> {
            Button tmp = new Button(new Icon(VaadinIcons.CLOSE));
            tmp.addClickListener(buttonClickEvent -> {
                Delete(selectedItem);
            });
            return tmp;
        }));


        content.add(header, recordGrid, infoLabel);

        super.add(content);
    }

    /**
     * Refresh values
     *
     * Use {@link #Refresh()} to refresh values in grid
     *
     */
    private void Refresh(){
        reservationRecords = reservationController.getConfigs();
        recordGrid.setItems(reservationRecords);
    }

    /**
     * Accept ReservationRepresentation
     *
     * Use {@link #Accept(ReservationRepresentation)} to accept given ReservationRepresentation and send acceptance message
     *
     * @param toAccept ReservationRepresentation to be accepted
     */
    private void Accept(ReservationRepresentation toAccept){
        if(toAccept.getStatus().equals("Accepted")){
            infoLabel.setText("Already accepted");
            return;
        }
        toAccept.setStatus("Accepted");
        reservationController.Update(toAccept);

        infoLabel.setText(emailController.AcceptMessage(toAccept.getEmail(),toAccept.getReservationId(), "Reservation"));

        Refresh();
    }

    /**
     * Decline ReservationRepresentation
     *
     * Use {@link #Decline(ReservationRepresentation)} to decline given ReservationRepresentation and send declination message
     *
     * @param toDecline ReservationRepresentation to be declined
     */
    private void Decline(ReservationRepresentation toDecline){
        if(toDecline.getStatus().equals("Declined")){
            infoLabel.setText("Already declined");
            return;
        }
        toDecline.setStatus("Declined");
        reservationController.Update(toDecline);

        infoLabel.setText(emailController.DeclineMessage(toDecline.getEmail(), toDecline.getReservationId(), "Reservation"));
        Refresh();
    }

    /**
     * Delete ReservationRepresentation
     *
     * Use {@link #Delete(ReservationRepresentation)} to delete given ReservationRepresentation and send deletion message
     *
     * @param toDelete ReservationRepresentation to be deleted
     */
    private void Delete(ReservationRepresentation toDelete){
        reservationController.Delete(toDelete);

        infoLabel.setText(emailController.DeleteMessage(toDelete.getEmail(), toDelete.getReservationId(), "Reservation"));

        Refresh();
    }
}