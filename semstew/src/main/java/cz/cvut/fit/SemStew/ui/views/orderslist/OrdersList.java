package cz.cvut.fit.SemStew.ui.views.orderslist;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

@Route(value = "admin/orders", layout = MainLayout.class)
@PageTitle("Orders List | Admin")
public class OrdersList extends GeneralAdminList {

    private final H2 header = new H2();

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

        content.add(header);

        super.add(content);
    }
}