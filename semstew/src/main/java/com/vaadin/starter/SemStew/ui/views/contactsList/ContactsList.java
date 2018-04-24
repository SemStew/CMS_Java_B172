package com.vaadin.starter.SemStew.ui.views.contactsList;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.ui.MainLayout;

@Route(value = "contacts", layout = MainLayout.class)
@PageTitle("Contacts List")
public class ContactsList extends VerticalLayout {
    private final H2 header = new H2();
    private final Image picture1 = new Image();
    private final Image picture2 = new Image();

    public ContactsList(){
        initView();
        addContent();
    }

    private void initView() {
        addClassName("contacts-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent(){
        VerticalLayout main_container = new VerticalLayout();
        HorizontalLayout layer_container = new HorizontalLayout();

        header.setText("Contacts");

        main_container.setClassName("view-container");
        layer_container.setClassName("contacts-container");

        main_container.setAlignItems(Alignment.STRETCH);
        layer_container.setAlignItems(Alignment.STRETCH);

        layer_container.add(new Text("Temporaty placeholder"),picture1);
        main_container.add(header,layer_container,picture2);
        add(main_container);
    }
}
