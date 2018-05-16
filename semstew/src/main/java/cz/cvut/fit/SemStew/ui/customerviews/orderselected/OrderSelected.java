package cz.cvut.fit.SemStew.ui.customerviews.orderselected;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Controllers.MenuItemController;
import cz.cvut.fit.SemStew.backend.Controllers.MenuItemRepresentation;
import cz.cvut.fit.SemStew.backend.Controllers.OrdersController;
import cz.cvut.fit.SemStew.ui.CustomerLayout;
import cz.cvut.fit.SemStew.ui.customerviews.menuslist.MenusList;
import cz.cvut.fit.SemStew.ui.customerviews.orderlist.OrderList;

import java.util.Comparator;
import java.util.List;

@Route(value = "orders/selected",layout = CustomerLayout.class)
@PageTitle("Orders | Selected")
public class OrderSelected extends VerticalLayout
    implements RouterLayout, BeforeEnterObserver {
    private final H2 header = new H2();
    private final Grid<MenuItemRepresentation> gridMenu = new Grid<>();
    private final MenuItemController menuItemController = new MenuItemController();
    private final OrdersController ordersController = new OrdersController();
    private List<MenuItemRepresentation> menuItems;
    private RouterLink back;
    private int idOrder;


    public OrderSelected(){
        init();
        addContent();
    }

    private void init(){
        setClassName("order-selected");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent(){
        VerticalLayout content = new VerticalLayout();
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        idOrder = Integer.parseInt(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active").toString());

        header.setText("Menu");

        menuItems = menuItemController.getItems();

        back = new RouterLink(null, OrderList.class);
        back.add(new Button("Back"));

        gridMenu.setHeightByRows(true);
        gridMenu.setItems(menuItems);
        gridMenu.addColumn(new ComponentRenderer<>(menuItemRepresentation -> {
            Image tmp = new Image();
            tmp.setClassName("picture_grid");
            tmp.setSrc(menuItemRepresentation.getImageAddress());
            return tmp;
        }));
        gridMenu.addColumn(MenuItemRepresentation::getName).setComparator(Comparator.comparing(MenuItemRepresentation::getName)).setHeader("Name").setSortable(true);
        gridMenu.addColumn(MenuItemRepresentation::getDescription).setHeader("Description");
        gridMenu.addColumn(MenuItemRepresentation::getAmount).setHeader("Amount");
        gridMenu.addColumn(MenuItemRepresentation::getUnitDescription).setHeader("Units");
        gridMenu.addColumn(MenuItemRepresentation::getCategoryDescription).setComparator(Comparator.comparing(MenuItemRepresentation::getCategoryDescription)).setHeader("Category").setSortable(true);
        gridMenu.addColumn(MenuItemRepresentation::getPrice).setComparator(Comparator.comparing(MenuItemRepresentation::getPrice)).setHeader("Price").setSortable(true);
        gridMenu.addColumn(MenuItemRepresentation::getAlergens).setHeader("Alergens");
        gridMenu.addColumn(new ComponentRenderer<>(selectedItem -> {
            Checkbox tmp = new Checkbox();
            if(ordersController.IsSelected(idOrder, selectedItem.getIdMenuItem())){
                tmp.setValue(true);
            } else {
                tmp.setValue(false);
            }
            tmp.addValueChangeListener(valueChangeEvent -> {
                if(valueChangeEvent.getValue()){
                    ordersController.InsertItemIntoOrder(idOrder, selectedItem);
                } else {
                    ordersController.RemoveItem(idOrder, selectedItem);
                }
            });
            return tmp;
        }));

        content.add(header, gridMenu, back);

        add(content);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active") == null ||
                VaadinService.getCurrentRequest().getWrappedSession().getAttribute("order_active").toString().equals("none")){
            beforeEnterEvent.rerouteTo("orders");
        }
    }
}
