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
import cz.cvut.fit.SemStew.backend.MenuItemController;
import cz.cvut.fit.SemStew.backend.MenuItemRepresantation;
import cz.cvut.fit.SemStew.ui.CustomerLayout;
import cz.cvut.fit.SemStew.ui.customerviews.menuslist.MenusList;

import java.util.Comparator;
import java.util.List;

@Route(value = "menus/selected", layout = CustomerLayout.class)
@PageTitle("Selected | Menu")
public class SelectedMenu extends VerticalLayout
    implements RouterLayout {
    private final H2 header = new H2();
    private final Grid<MenuItemRepresantation> gridMenu = new Grid<>();
    private MenuItemController menuItemController = new MenuItemController();
    private List<MenuItemRepresantation> menuItems;
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
        gridMenu.addColumn(new ComponentRenderer<>(menuItemRepresantation -> {
            Image tmp = new Image();
            tmp.setClassName("picture_grid");
            tmp.setSrc(menuItemRepresantation.getImageAddress());
            return tmp;
        }));
        gridMenu.addColumn(MenuItemRepresantation::getName).setComparator(Comparator.comparing(MenuItemRepresantation::getName)).setHeader("Name").setSortable(true);
        gridMenu.addColumn(MenuItemRepresantation::getDescription).setHeader("Description");
        gridMenu.addColumn(MenuItemRepresantation::getAmount).setHeader("Amount");
        gridMenu.addColumn(MenuItemRepresantation::getUnitDescription).setHeader("Units");
        gridMenu.addColumn(MenuItemRepresantation::getCategoryDescription).setComparator(Comparator.comparing(MenuItemRepresantation::getCategoryDescription)).setHeader("Category").setSortable(true);
        gridMenu.addColumn(MenuItemRepresantation::getPrice).setComparator(Comparator.comparing(MenuItemRepresantation::getPrice)).setHeader("Price").setSortable(true);
        gridMenu.addColumn(MenuItemRepresantation::getAlergens).setHeader("Alergens");

        content.add(header, gridMenu, back);

        add(content);
    }

}
