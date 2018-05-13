package cz.cvut.fit.SemStew.ui;

import JOOQ.tables.records.AdminsRecord;
import JOOQ.tables.records.RestaurantRecord;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import cz.cvut.fit.SemStew.backend.CorrectnessController;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.AdminsService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.RestaurantService;

import java.math.BigDecimal;
import java.util.List;


@Route(value = "registration", layout = MainLayout.class)
@PageTitle("Registration")
public class Registration extends VerticalLayout
    implements RouterLayout {
    private final H2 header = new H2();
    private final TextField userName = new TextField();
    private final PasswordField password = new PasswordField();
    private final PasswordField passwordRepeat = new PasswordField();
    private final TextField forname = new TextField();
    private final TextField surname = new TextField();
    private final TextField ico = new TextField();
    private final TextField email = new TextField();
    private final PasswordField emailPassword = new PasswordField();
    private final PasswordField emailPasswordRepeat = new PasswordField();
    private final TextField restaurantName = new TextField();
    private final Button registrationButton = new Button();
    private final Label infoLabel = new Label();
    private RouterLink back;
    private AdminsService admin = new AdminsService();
    private RestaurantService rest = new RestaurantService();

    public Registration()
    {
        init();
        addContent();
        addFoot();
    }

    private void init()
    {
        addClassName("register");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setAlignItems(Alignment.STRETCH);
        content.addClassName("logreg_style");

        HorizontalLayout passwords = new HorizontalLayout();
        passwords.setAlignItems(Alignment.STRETCH);

        HorizontalLayout emailPasswords = new HorizontalLayout();
        emailPasswords.setAlignItems(Alignment.STRETCH);

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
        emailPassword.setLabel("Gmail password:");
        emailPasswordRepeat.setLabel("Confirm gmail password");
        restaurantName.setLabel("Restaurant name:");
        registrationButton.setText("Register");
        registrationButton.addClassName("btn_style");

        Button backButton = new Button("Back");

        back = new RouterLink(null,Login.class);
        back.add(backButton);
        back.addClassName("btn_style");

        Div buttons = new Div ();
        buttons.addClassName("buttons");
        buttons.add(back,registrationButton);

        registrationButton.addClickListener(buttonClickEvent -> {
            if(!password.getValue().equals(passwordRepeat.getValue())) {
                infoLabel.setText("Passwords are different");
                return;
            }
            if(!emailPassword.getValue().equals(emailPasswordRepeat.getValue())){
                infoLabel.setText("Email passwords are different");
                return;
            }
            if(!CorrectnessController.OnlyNumbers(ico.getValue())){
                infoLabel.setText("ICO is numbers only");
                return;
            }
            if(!CorrectnessController.ValidEmail(email.getValue())){
                infoLabel.setText("Enter valid email address");
                return;
            }
            if(UINotFilled()){
                infoLabel.setText("Fill all fields");
                return;
            }
            AdminsRecord tmp = new AdminsRecord();
            tmp.setName(userName.getValue());
            tmp.setPassword(password.getValue());
            admin.InsertAdminsService(tmp);
            RestaurantRecord tmp2 = new RestaurantRecord();
            tmp2.setIco(new BigDecimal(ico.getValue()));
            tmp2.setName(restaurantName.getValue());
            tmp2.setImage("none");
            tmp2.setEmail(email.getValue());
            tmp2.setEmailPassword(emailPassword.getValue());
            List<AdminsRecord> admins = admin.getConfigs();
            for(AdminsRecord it : admins) {
                if (it.getName().equals(tmp.getName()) && it.getPassword().equals(tmp.getPassword())) {
                    tmp2.setIdAdmin(it.getIdAdmin());
                    break;
                }
            }
            registrationButton.getUI().ifPresent(ui -> ui.navigate("login"));
        });

        passwords.add(password, passwordRepeat);

        emailPasswords.add(emailPassword, emailPasswordRepeat);

        names.add(forname, surname);

        content.add(header, userName, passwords, names, ico, email, emailPasswords,restaurantName, buttons,infoLabel);
        add(content);
    }

    private boolean UINotFilled()
    {
        return (userName.isEmpty() || password.isEmpty() || passwordRepeat.isEmpty() || forname.isEmpty()
                || surname.isEmpty() || ico.isEmpty() || email.isEmpty() || restaurantName.isEmpty()
                || emailPassword.isEmpty() || emailPasswordRepeat.isEmpty());
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
