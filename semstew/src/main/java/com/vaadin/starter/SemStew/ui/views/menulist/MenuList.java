package com.vaadin.starter.SemStew.ui.views.menulist;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.backend.Menus;
import com.vaadin.starter.SemStew.ui.MainLayout;

@Route(value = "menu", layout = MainLayout.class)
@PageTitle("Menu List")
public class MenuList extends VerticalLayout {
        private final H2 header = new H2("Menu");
        private final Grid<Menus> grid = new Grid<>();

        public MenuList()
        {
            this.init();
            this.addContent();
        }

        private void init()
        {
            addClassName("menu-list");
            setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        }

        private void addContent() {
            VerticalLayout container = new VerticalLayout();
            container.setClassName("view-container");
            container.setAlignItems(Alignment.STRETCH);

            //grid.addColumn(MenuItem::getName).setHeader("Name").setWidth("8em").setResizable(true);
            grid.setSelectionMode(Grid.SelectionMode.NONE);

            container.add(header, grid);
            add(container);
        }
}
