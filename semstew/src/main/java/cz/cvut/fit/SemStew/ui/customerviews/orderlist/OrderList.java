package cz.cvut.fit.SemStew.ui.customerviews.orderlist;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

@Route(value = "orders",layout = CustomerLayout.class)
@PageTitle("Orders | Home")
public class OrderList extends VerticalLayout {
    private final H2 header = new H2();

    public OrderList(){
        init();
        addContent();
    }

    private void init(){
        setClassName("orders");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent(){
        VerticalLayout content = new VerticalLayout();
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        header.setText("Orders");

        content.add(header);

        add(content);
    }
}
