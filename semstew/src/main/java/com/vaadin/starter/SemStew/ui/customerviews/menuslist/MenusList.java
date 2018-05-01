package com.vaadin.starter.SemStew.ui.customerviews.menuslist;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.backend.MenuItemControler;
import com.vaadin.starter.SemStew.backend.MenuItemRepresantation;
import com.vaadin.starter.SemStew.ui.CustomerLayout;

import java.util.List;

@Route(value = "menus", layout = CustomerLayout.class)
@PageTitle("Menu | Home")
public class MenusList extends VerticalLayout {
    private final H2 header = new H2();
    private final Grid<MenuItemRepresantation> gridMenu = new Grid<>();
    private MenuItemControler menuItemControler = new MenuItemControler();
    private List<MenuItemRepresantation> menuItems;

    public MenusList()
    {
        init();
        addContent();

    }

    private void init()
    {
        setClassName("menu");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        header.setText("Menu");

        menuItems = menuItemControler.getItems();

        gridMenu.setItems(menuItems);
        gridMenu.addColumn(MenuItemRepresantation::getName).setHeader("Name");
        gridMenu.addColumn(MenuItemRepresantation::getDescription).setHeader("Description");
        gridMenu.addColumn(MenuItemRepresantation::getAmount).setHeader("Amount");
        gridMenu.addColumn(MenuItemRepresantation::getUnitDescription).setHeader("Units");
        gridMenu.addColumn(MenuItemRepresantation::getCategoryDescription).setHeader("Category");
        gridMenu.addColumn(MenuItemRepresantation::getPrice).setHeader("Price");

        content.add(header,gridMenu);

        add(content);
    }
}
