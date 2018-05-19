package cz.cvut.fit.SemStew.ui.views.orderslist;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Controllers.EmailController;
import cz.cvut.fit.SemStew.backend.Controllers.OrdersController;
import cz.cvut.fit.SemStew.backend.Controllers.OrdersRepresentation;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.RestaurantService;
import cz.cvut.fit.SemStew.ui.MainLayout;
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
@Route(value = "admin/orders", layout = MainLayout.class)
@PageTitle("Orders List | Admin")
public class OrdersList extends GeneralAdminList {

    /**
     * page header
     */
    private final H2 header = new H2();
    /**
     * grid for displaying Orders
     */
    private final Grid<OrdersRepresentation> representationGrid = new Grid<>();
    /**
     * Informational texts label
     */
    private final Label infoLabel = new Label();
    /**
     * Orders management
     */
    private final OrdersController ordersController = new OrdersController();
    /**
     * Email management
     */
    private final EmailController emailController = new EmailController();
    /**
     * list of all available orders
     */
    private List<OrdersRepresentation> ordersRepresentations;

    /**
     * OrdersList constructor
     *
     * Use {@link #OrdersList()} to create and initialize page
     *
     */
    public OrdersList() {
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
        super.addClassName("orders-list");
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

        header.setText("Orders");

        // grid set up
        Refresh();
        representationGrid.addColumn(OrdersRepresentation::getPerson).setComparator(Comparator.comparing(OrdersRepresentation::getPerson)).
                setHeader("Person").setSortable(true);
        representationGrid.addColumn(OrdersRepresentation::getAddress).setComparator(Comparator.comparing(OrdersRepresentation::getAddress)).
                setHeader("Address").setSortable(true);
        representationGrid.addColumn(new LocalDateRenderer<>(OrdersRepresentation::getDate,
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))).setComparator(Comparator.comparing(OrdersRepresentation::getDate)).
                setHeader("Date").setSortable(true);
        representationGrid.addColumn(OrdersRepresentation::getEmail).setHeader("Email");
        representationGrid.addColumn(OrdersRepresentation::getStatus).setHeader("Status");
        representationGrid.addColumn(new ComponentRenderer<>(selectedItem ->{
            Button tmp = new Button(new Icon(VaadinIcons.AREA_SELECT));
            tmp.addClickListener(buttonClickEvent -> {
                VaadinService.getCurrentRequest().getWrappedSession().setAttribute("order_detail", selectedItem.getIdOrder().toString());
                tmp.getUI().ifPresent(ui -> ui.navigate("admin/orders/detail"));
            });
            return tmp;
        }));
        representationGrid.addColumn(new ComponentRenderer<>(selectedItem ->{
            Button tmp = new Button(new Icon(VaadinIcons.CHECK));
            tmp.setText("Accept");
            tmp.addClickListener(buttonClickEvent -> {
                Accept(selectedItem);
            });
            return tmp;
        }));
        representationGrid.addColumn(new ComponentRenderer<>(selectedItem -> {
            Button tmp = new Button(new Icon(VaadinIcons.CLOSE_CIRCLE));
            tmp.setText("Decline");
            tmp.addClickListener(buttonClickEvent -> {
               Decline(selectedItem);
            });
            return tmp;
        }));
        representationGrid.addColumn(new ComponentRenderer<>(selectedItem -> {
            Button tmp = new Button(new Icon(VaadinIcons.CLOSE));
            tmp.setText("Delete");
            tmp.addClickListener(buttonClickEvent -> {
                Delete(selectedItem);
            });
            return tmp;
        }));


        content.add(header, representationGrid, infoLabel);

        super.add(content);
    }

    /**
     * Refresh values
     *
     * Use {@link #Refresh()} to refresh values in grid
     *
     */
    private void Refresh(){
        ordersRepresentations = ordersController.getConfigs();
        representationGrid.setItems(ordersRepresentations);
    }

    /**
     * Accept Order
     *
     * Use {@link #Accept(OrdersRepresentation)} to accept given OrdersRepresentation and send acceptance message
     *
     * @param toAccept OrdersRepresentation to be accepted
     */
    private void Accept(OrdersRepresentation toAccept){
        if(toAccept.getStatus().equals("Accepted")){
            infoLabel.setText("Already accepted");
            return;
        }
        toAccept.setStatus("Accepted");
        ordersController.Update(toAccept);

        infoLabel.setText(emailController.AcceptMessage(toAccept.getEmail(), toAccept.getIdOrder(),"Order"));
        Refresh();
    }

    /**
     * Decline Order
     *
     * Use {@link #Decline(OrdersRepresentation)} to decline given OrdersRepresentation and send decline message
     *
     * @param toDecline OrdersRepresentation to be declined
     */
    private void Decline(OrdersRepresentation toDecline){
        if(toDecline.getStatus().equals("Declined")){
            infoLabel.setText("Already declined");
        }
        toDecline.setStatus("Declined");
        ordersController.Update(toDecline);

        infoLabel.setText(emailController.DeclineMessage(toDecline.getEmail(), toDecline.getIdOrder(), "Order"));
        Refresh();
    }

    /**
     * Delete Order
     *
     * Use {@link #Delete(OrdersRepresentation)} to delete given OrdersRepresentation and send deletion message
     *
     * @param toDelete OrdersRepresentation to be deleted
     */
    private void Delete(OrdersRepresentation toDelete){
        ordersController.Delete(toDelete);

        infoLabel.setText(emailController.DeleteMessage(toDelete.getEmail(), toDelete.getIdOrder(), "Order"));
        Refresh();
    }
}