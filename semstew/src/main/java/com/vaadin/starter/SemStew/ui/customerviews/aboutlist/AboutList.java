package com.vaadin.starter.SemStew.ui.customerviews.aboutlist;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.ui.CustomerLayout;

@Route(value = "about", layout = CustomerLayout.class)
@PageTitle("About")
public class AboutList extends VerticalLayout {
    private final H2 header = new H2();
    private final Text description = new Text("");
    private final H3 subheader = new H3();
    // Todo picture grid

    public AboutList()
    {
        init();
        addContent();
    }

    private void init()
    {
        setClassName("about");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        header.setText("About us");

        description.setText("Temporary description");

        subheader.setText("Photos");

        content.add(header, description, subheader);

        add(content);
    }
}
