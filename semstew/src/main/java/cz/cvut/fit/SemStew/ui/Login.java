package cz.cvut.fit.SemStew.ui;

import JOOQ.tables.records.AdminsRecord;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.AdminsService;
import cz.cvut.fit.SemStew.ui.customerviews.introlist.IntroList;
import cz.cvut.fit.SemStew.ui.views.branchlist.BranchList;

import java.util.List;


@Route(value = "login", layout = MainLayout.class)
@PageTitle("Login")
public class Login extends VerticalLayout
    implements RouterLayout, BeforeEnterObserver {
    private RouterLink register;
    private RouterLink back;
    private final H2 header = new H2();
    private final TextField userName = new TextField();
    private final PasswordField password = new PasswordField();
    private final Button loginButton = new Button();
    private final Button registrationButton = new Button();
    private final Label infoLabel = new Label();
    private final AdminsService admin = new AdminsService();

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
        userName.setErrorMessage("Enter your username");
        userName.setRequired(true);
        password.setLabel("Password:");
        password.setErrorMessage("Enter your password");
        password.setRequired(true);
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
            List<AdminsRecord> admins = admin.getConfigs();
            boolean found = false;
            if(userName.isEmpty() || password.isEmpty()){
                if(userName.isEmpty())
                    userName.setInvalid(true);
                if(password.isEmpty())
                    password.setInvalid(true);
                return;
            }

            for(AdminsRecord adminsRecord : admins)
            {
                if(adminsRecord.getName().equals(userName.getValue())
                        && adminsRecord.getPassword().equals(password.getValue()))
                {
                    found = true;
                    break;
                }
            }
            if(found) {
                VaadinService.getCurrentRequest().getWrappedSession().setAttribute("logged_in", "logged");
                loginButton.getUI().ifPresent(ui -> ui.navigate("admin"));
            } else {
                infoLabel.setText("User name or password incorrect");
            }
        });

        Div buttons = new Div ();
        buttons.addClassName("buttons");
        buttons.add(register,loginButton);

        topHead.add(header, back);
        content.add(topHead, userName, password, buttons, infoLabel);
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

    @Override
    public void beforeEnter(BeforeEnterEvent event)
    {
        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("logged_in") != null) {
            String res = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("logged_in").toString();
            if(res.equals("logged")){
                event.rerouteTo("admin");
            }
        }
    }
}
