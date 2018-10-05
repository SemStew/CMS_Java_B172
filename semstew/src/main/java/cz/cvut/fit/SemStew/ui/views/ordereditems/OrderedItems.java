package cz.cvut.fit.SemStew.ui.views.ordereditems;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import cz.cvut.fit.SemStew.backend.Controllers.MenuItemRepresentation;
import cz.cvut.fit.SemStew.backend.Controllers.OrdersController;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;
import cz.cvut.fit.SemStew.ui.views.orderslist.OrdersList;

import java.util.Comparator;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
@Route(value = "admin/orders/detail", layout = MainLayout.class)
@PageTitle("Orders List Detail | Admin")
public class OrderedItems extends GeneralAdminList
    implements RouterLayout {
    /**
     * page header
     */
    private final H2 header = new H2();
    /**
     * grid for displaying MenuItems
     */
    private final Grid<MenuItemRepresentation> showGrid = new Grid<>();
    /**
     * Orders management
     */
    private final OrdersController ordersController = new OrdersController();
    /**
     * Route back
     */
    private RouterLink backLink;

    /**
     * OrderedItems constructor
     *
     * Use {@link #OrderedItems()} to create and initialize page
     *
     */
    public OrderedItems(){
        init();
        addContent();
    }

    /**
     * Initialize page
     *
     * Use {@link #init()} to initialize page
     *
     */
    private void init(){
        super.addClassName("orders-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    /**
     * Load content
     *
     * Use {@link #addContent()} to load and set up page content
     *
     */
    private void addContent(){
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        showGrid.setItems(ordersController.LoadByID().getOrderedItems());

        header.setText("Order detail");

        showGrid.addColumn(new ComponentRenderer<>(menuItemRepresentation -> {
            Image tmp = new Image();
            tmp.setClassName("picture_grid");
            tmp.setSrc(menuItemRepresentation.getImageAddress());
            return tmp;
        }));
        showGrid.addColumn(MenuItemRepresentation::getName).setComparator(Comparator.comparing(MenuItemRepresentation::getName)).setHeader("Name").setSortable(true);
        showGrid.addColumn(MenuItemRepresentation::getDescription).setHeader("Description");
        showGrid.addColumn(MenuItemRepresentation::getAmount).setHeader("Amount");
        showGrid.addColumn(MenuItemRepresentation::getUnitDescription).setHeader("Units");
        showGrid.addColumn(MenuItemRepresentation::getCategoryDescription).setComparator(Comparator.comparing(MenuItemRepresentation::getCategoryDescription)).setHeader("Category").setSortable(true);
        showGrid.addColumn(MenuItemRepresentation::getPrice).setComparator(Comparator.comparing(MenuItemRepresentation::getPrice)).setHeader("Price").setSortable(true);
        showGrid.addColumn(MenuItemRepresentation::getAllergens).setHeader("Alergens");

        backLink = new RouterLink(null, OrdersList.class);
        backLink.add(new Button("Back"));

        content.add(header, showGrid, backLink);

        super.add(content);
    }
}
