package com.vaadin.starter.SemStew.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.backend.IntroConfig;
import com.vaadin.starter.SemStew.ui.MainLayout;


@Route(value = "login", layout = MainLayout.class)
@PageTitle("Login")
public class Login extends VerticalLayout {
    private final H2 header = new H2();
    private final TextField userName = new TextField();
    private final PasswordField password = new PasswordField();

    public Login()
    {
        init();
        addContent();
    }

    private void init()
    {
        addClassName("login");
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Login");
        userName.setLabel("User name:");
        password.setLabel("Password:");

        content.add(header, userName, password);
        add(content);
    }
}
