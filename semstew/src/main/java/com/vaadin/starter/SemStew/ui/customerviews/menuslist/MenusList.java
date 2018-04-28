package com.vaadin.starter.SemStew.ui.customerviews.menuslist;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.backend.Menus;
import com.vaadin.starter.SemStew.ui.CustomerLayout;

@Route(value = "menus", layout = CustomerLayout.class)
@PageTitle("Menus")
public class MenusList extends VerticalLayout {
    private final H2 header = new H2();
    private final Grid<Menus> menus = new Grid<>();

    public MenusList()
    {
        init();
        addContent();
    }

    private void init()
    {
        setClassName("menus");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        header.setText("Menus");

        content.add(header, menus);

        add(content);
    }
}
