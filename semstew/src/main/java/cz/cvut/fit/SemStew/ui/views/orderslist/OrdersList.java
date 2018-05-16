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

@Route(value = "admin/orders", layout = MainLayout.class)
@PageTitle("Orders List | Admin")
public class OrdersList extends GeneralAdminList {

    private final H2 header = new H2();
    private final Grid<OrdersRepresentation> representationGrid = new Grid<>();
    private final Label infoLabel = new Label();
    private final OrdersController ordersController = new OrdersController();
    private final EmailController emailController = new EmailController();
    private List<OrdersRepresentation> ordersRepresentations;

    public OrdersList() {
        init();
        addContent();
    }

    private void init() {
        super.addClassName("orders-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

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


    private void Refresh(){
        ordersRepresentations = ordersController.getConfigs();
        representationGrid.setItems(ordersRepresentations);
    }

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

    private void Decline(OrdersRepresentation toDecline){
        if(toDecline.getStatus().equals("Declined")){
            infoLabel.setText("Already declined");
        }
        toDecline.setStatus("Declined");
        ordersController.Update(toDecline);

        infoLabel.setText(emailController.DeclineMessage(toDecline.getEmail(), toDecline.getIdOrder(), "Order"));
        Refresh();
    }

    private void Delete(OrdersRepresentation toDelete){
        ordersController.Delete(toDelete);

        infoLabel.setText(emailController.DeleteMessage(toDelete.getEmail(), toDelete.getIdOrder(), "Order"));
        Refresh();
    }
}