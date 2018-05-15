package cz.cvut.fit.SemStew.ui.customerviews.selectedmenu;

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
import cz.cvut.fit.SemStew.backend.Controllers.MenuItemController;
import cz.cvut.fit.SemStew.backend.Controllers.MenuItemRepresentation;
import cz.cvut.fit.SemStew.ui.CustomerLayout;
import cz.cvut.fit.SemStew.ui.customerviews.menuslist.MenusList;

import java.util.Comparator;
import java.util.List;

@Route(value = "menus/selected", layout = CustomerLayout.class)
@PageTitle("Selected | Menu")
public class SelectedMenu extends VerticalLayout
    implements RouterLayout {
    private final H2 header = new H2();
    private final Grid<MenuItemRepresentation> gridMenu = new Grid<>();
    private MenuItemController menuItemController = new MenuItemController();
    private List<MenuItemRepresentation> menuItems;
    private RouterLink back;

    public SelectedMenu()
    {
        init();
        addContent();
    }

    private void init()
    {
        setClassName("selectedMenu");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        header.setText("Menu");

        menuItems = menuItemController.getItems();

        back = new RouterLink(null, MenusList.class);
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

        content.add(header, gridMenu, back);

        add(content);
    }

}
