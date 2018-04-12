package com.vaadin.starter.SemStew.ui.views.aboutlist;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.ui.MainLayout;

@Route(value = "about",layout = MainLayout.class)
@PageTitle("About List")
public class AboutList extends VerticalLayout {
    private final H2 header = new H2("About us");
    private final TextField description = new TextField();
    private final H3 subHeader = new H3();
    // TODO grid with pictures

    public AboutList()
    {
        init();
        addContent();
    }


    private void init()
    {
        addClassName("about-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout container  = new VerticalLayout();
        container.setClassName("view-container");
        container.setAlignItems(Alignment.STRETCH);

        description.setValue("description placeholder");
        description.setEnabled(false);
        subHeader.setText("Photos");

        container.add(header,description,subHeader);
        add(container);
    }

}
