package cz.cvut.fit.SemStew.ui.views.reservationslist;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.cvut.fit.SemStew.ui.MainLayout;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

@Route(value = "admin/reservations", layout = MainLayout.class)
@PageTitle("Reservations List | Admin")
public class ReservationsList extends GeneralAdminList {

    private final H2 header = new H2();

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

        content.add(header);

        super.add(content);
    }
}