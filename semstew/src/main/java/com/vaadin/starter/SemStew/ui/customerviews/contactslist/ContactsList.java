package com.vaadin.starter.SemStew.ui.customerviews.contactslist;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.ui.CustomerLayout;

@Route(value = "contacts", layout = CustomerLayout.class)
@PageTitle("Contacts | Home")
public class ContactsList extends VerticalLayout {
    private final H2 header = new H2();
    private final Text description = new Text("");
    private final Image image1 = new Image();
    private final Image image2 = new Image();

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

        header.setText("Contacts");
        description.setText("Temporary text placeholder");

        image1.setSrc("https://www.theriverside.co.uk/images/Inside-Restaurant.jpg");
        image2.setSrc("http://www.realdetroitweekly.com/wp-content/uploads/2017/06/Restaurants.jpg");

        imageContainer.add(image1,image2);
        subcontent.add(description, imageContainer);
        content.add(header,subcontent);
        add(content);
    }
}
