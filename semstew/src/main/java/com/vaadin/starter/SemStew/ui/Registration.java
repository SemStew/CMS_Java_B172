package com.vaadin.starter.SemStew.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.backend.IntroConfig;
import com.vaadin.starter.SemStew.ui.MainLayout;


@Route(value = "registration", layout = MainLayout.class)
@PageTitle("Registration")
public class Registration extends VerticalLayout {
    private final H2 header = new H2();
    private final TextField userName = new TextField();
    private final PasswordField password = new PasswordField();
    private final PasswordField passwordRepeat = new PasswordField();
    private final TextField forname = new TextField();
    private final TextField surname = new TextField();
    private final TextField ico = new TextField();
    private final TextField email = new TextField();
    private final TextField restaurantName = new TextField();
    private final Button registrationButton = new Button();

    public Registration()
    {
        init();
        addContent();
    }

    private void init()
    {
        addClassName("registration");
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setAlignItems(Alignment.STRETCH);

        HorizontalLayout passwords = new HorizontalLayout();
        passwords.setAlignItems(Alignment.STRETCH);

        HorizontalLayout names = new HorizontalLayout();
        names.setAlignItems(Alignment.STRETCH);


        header.setText("Registration");
        userName.setLabel("User name:");
        password.setLabel("Password:");
        passwordRepeat.setLabel("Repeat password:");
        forname.setLabel("Forname:");
        surname.setLabel("Surname:");
        ico.setLabel("ICO:");
        email.setLabel("Email:");
        email.setPrefixComponent(new Icon(VaadinIcons.AT));
        restaurantName.setLabel("Restaurant name:");
        registrationButton.setText("Register");

        passwords.add(password, passwordRepeat);

        names.add(forname, surname);

        content.add(header, userName, passwords, names, ico, email, restaurantName, registrationButton);
        add(content);
    }
}
