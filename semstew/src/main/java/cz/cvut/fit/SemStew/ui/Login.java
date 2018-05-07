package cz.cvut.fit.SemStew.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.ui.customerviews.introlist.IntroList;
import cz.cvut.fit.SemStew.ui.views.branchlist.BranchList;


@Route(value = "login", layout = MainLayout.class)
@PageTitle("Login")
public class Login extends VerticalLayout
    implements RouterLayout {
    private RouterLink register;
    private RouterLink back;
    private final H2 header = new H2();
    private final TextField userName = new TextField();
    private final PasswordField password = new PasswordField();
    private final Button loginButton = new Button();
    private final Button registrationButton = new Button();

    public Login()
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

        HorizontalLayout topHead = new HorizontalLayout();

        header.setText("Login");
        userName.setLabel("Username:");
        password.setLabel("Password:");
        loginButton.setText("Log in");
        loginButton.addClassName("btn_style");
        registrationButton.setText("Register");
        registrationButton.addClassName("btn_style");

        back = new RouterLink(null,IntroList.class);
        back.add(new Icon(VaadinIcons.CLOSE));
        back.addClassName("back_style");

        register = new RouterLink(null, Registration.class);
        register.add(registrationButton);

        loginButton.addClickListener(buttonClickEvent ->
        {
           userName.setValue("Test");
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("myvalue","logged");
           loginButton.getUI().ifPresent(ui -> ui.navigate("admin"));
        });

        Div buttons = new Div ();
        buttons.addClassName("buttons");
        buttons.add(register,loginButton);

        topHead.add(header, back);
        content.add(topHead, userName, password, buttons);
        add(content);
    }

    private void addFoot () {
        HorizontalLayout bottom = new HorizontalLayout();
        bottom.addClassName("main-layout__bottom");
        Div foot = new Div ();
        foot.addClassName("foot");

        Text foot__text = new Text("Design and developed by DreamTeam © SemStew CMS");
        foot.add(foot__text);
        bottom.add(foot);

        add(bottom);
    }
}
