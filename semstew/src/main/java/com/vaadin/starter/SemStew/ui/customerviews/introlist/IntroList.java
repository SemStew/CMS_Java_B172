package com.vaadin.starter.SemStew.ui.customerviews.introlist;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.backend.IntroConfig;
import com.vaadin.starter.SemStew.ui.CustomerLayout;

@Route(value = "", layout = CustomerLayout.class)
@PageTitle("Home")
public class IntroList extends VerticalLayout {
    private final Text introText = new Text("");
    private final H2 header = new H2();
    private final Grid<IntroConfig> actualities = new Grid<>();

    public IntroList()
    {
        init();
        addContent();
    }

    private void init()
    {
        addClassName("intro-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setAlignItems(Alignment.STRETCH);

        introText.setText("Temporary description");

        header.setText("Actualities");

        actualities.setSelectionMode(Grid.SelectionMode.NONE);

        content.add(introText, header, actualities);
        add(content);
    }
}
