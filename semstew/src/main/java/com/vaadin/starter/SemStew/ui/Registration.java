package com.vaadin.starter.SemStew.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Div;
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
        addFoot();
    }

    private void init()
    {
        addClassName("logreg");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setAlignItems(Alignment.STRETCH);
        content.addClassName("logreg_style");

        HorizontalLayout passwords = new HorizontalLayout();
        passwords.setAlignItems(Alignment.STRETCH);

        HorizontalLayout names = new HorizontalLayout();
        names.setAlignItems(Alignment.STRETCH);

        header.setText("Registration");
        userName.setLabel("Username:");
        password.setLabel("Password:");
        passwordRepeat.setLabel("Confirm password:");
        forname.setLabel("Forename:");
        surname.setLabel("Surname:");
        ico.setLabel("ICO:");
        email.setLabel("Email:");
        email.setPrefixComponent(new Icon(VaadinIcons.AT));
        restaurantName.setLabel("Restaurant name:");
        registrationButton.setText("Register");
        Div buttons = new Div ();
        buttons.addClassName("buttons");
        buttons.add(registrationButton);

        passwords.add(password, passwordRepeat);

        names.add(forname, surname);

        content.add(header, userName, passwords, names, ico, email, restaurantName, buttons);
        add(content);
    }

    private void addFoot () {
        HorizontalLayout bottom = new HorizontalLayout();
        bottom.addClassName("main-layout__bottom");
        Div foot = new Div();
        foot.addClassName("foot");

        Text foot__text = new Text("Design and developed by DreamTeam © SemStew CMS");
        foot.add(foot__text);
        bottom.add(foot);

        add(bottom);
    }
}
