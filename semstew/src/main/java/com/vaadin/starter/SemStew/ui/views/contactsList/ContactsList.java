package com.vaadin.starter.SemStew.ui.views.contactsList;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.ui.MainLayout;

@Route(value = "contacts", layout = MainLayout.class)
@PageTitle("Contacts List")
public class ContactsList extends VerticalLayout {
    private final TextField adress = new TextField();
    private final TextField phoneNumber = new TextField();
    private final H2 header = new H2("Contacts");

    public ContactsList(){
        initView();

        addContent();
    }

    private void initView() {
        addClassName("contacts-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent(){
        VerticalLayout vertical_container = new VerticalLayout();
        HorizontalLayout contacts_container = new HorizontalLayout();

        vertical_container.setClassName("vertical-container");
        contacts_container.setClassName("contacts-container");

        vertical_container.setAlignItems(Alignment.STRETCH);
        contacts_container.setAlignItems(Alignment.STRETCH);

        adress.setValue("example adress");
        adress.setLabel("Adress:");
        adress.setReadOnly(true);
        phoneNumber.setValue("example number");
        phoneNumber.setLabel("Phone number: ");
        phoneNumber.setReadOnly(true);
        contacts_container.add(adress,phoneNumber);
        vertical_container.add(header,contacts_container);
        add(vertical_container);
    }
}
