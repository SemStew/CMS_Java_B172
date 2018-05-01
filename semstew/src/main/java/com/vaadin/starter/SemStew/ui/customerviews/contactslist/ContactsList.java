package com.vaadin.starter.SemStew.ui.customerviews.contactslist;

import JOOQ.tables.records.ContactConfigRecord;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.backend.Services.ContactConfigService;
import com.vaadin.starter.SemStew.ui.CustomerLayout;

import java.util.List;

@Route(value = "contacts", layout = CustomerLayout.class)
@PageTitle("Contacts | Home")
public class ContactsList extends VerticalLayout {
    private final H2 header = new H2();
    private final Text description = new Text("");
    private final Image image1 = new Image();
    private final Image image2 = new Image();
    private ContactConfigService contactsService = new ContactConfigService();

    public ContactsList(){
        init();
        addContent();
    }

    private void init()
    {
        addClassName("contacts");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.addClassName("content");
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        HorizontalLayout subcontent = new HorizontalLayout();
        VerticalLayout imageContainer = new VerticalLayout();
        imageContainer.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        imageContainer.addClassName("pictures");

        List<ContactConfigRecord> configRecords = contactsService.getConfigs();
        ContactConfigRecord configSet = configRecords.get(0);

        header.setText(configSet.getHeader());
        description.setText(configSet.getDescription());

        //image1.setSrc(configSet.getUrlImage1());
        //image2.setSrc(configSet.getUrlImage2());

        imageContainer.add(image1,image2);
        subcontent.add(description, imageContainer);
        content.add(header,subcontent);
        add(content);
    }
}
