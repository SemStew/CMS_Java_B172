package cz.cvut.fit.SemStew.ui;

import JOOQ.tables.records.LanguagesRecord;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.GeneralConfigService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.ui.customerviews.aboutlist.AboutList;
import cz.cvut.fit.SemStew.ui.customerviews.contactslist.ContactsList;
import cz.cvut.fit.SemStew.ui.customerviews.introlist.IntroList;
import cz.cvut.fit.SemStew.ui.customerviews.menuslist.MenusList;
import cz.cvut.fit.SemStew.ui.customerviews.orderlist.OrderList;
import cz.cvut.fit.SemStew.ui.customerviews.reservationlist.ReservationList;

import java.util.ArrayList;
import java.util.List;

@HtmlImport("frontend://styles/customer-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class CustomerLayout extends Div
        implements RouterLayout, PageConfigurator, AfterNavigationObserver {
    private static final String ACTIVE_ITEM_STYLE = "main-layout__nav-item--selected";
    private final H2 name = new H2();
    private RouterLink intro;
    private RouterLink menus;
    private RouterLink contacts;
    private RouterLink about;
    private RouterLink reservations;
    private RouterLink orders;
    private final HorizontalLayout bottom = new HorizontalLayout();
    private final GeneralConfigService generalService = new GeneralConfigService();
    private final LanguagesService languagesService = new LanguagesService();

    public CustomerLayout() {
        PostgreSQLConnection postgre = new PostgreSQLConnection();
        Image image = new Image();
        image.setSrc(generalService.GetInstance().getUrlMainImage());
        image.addClassName("main-layout__picture");

        List<LanguagesRecord> languagesRecords = languagesService.getConfigs();

        List<String> languageNames = new ArrayList<>();

        for(LanguagesRecord rec : languagesRecords)
            languageNames.add(rec.getName());

        ComboBox<String> languages = new ComboBox<>("Languages");
        languages.setItems(languageNames);
        languages.setValue(languageNames.get(0));

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") == null) {
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("language", languageNames.get(0));
        }else
            languages.setValue(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString());

        languages.addValueChangeListener(valueChangeEvent -> {
            if(languageNames.stream().filter(name -> name.equals(languages.getValue())).count() == 0){
                languages.setErrorMessage("Select existing language");
                languages.setInvalid(true);
                return;
            }
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("language",languages.getValue());
            this.getUI().ifPresent(ui -> ui.getPage().reload());
        });

        Button loginButton = new Button("Login");

        VerticalLayout comboLogin = new VerticalLayout();
        comboLogin.addClassName("main-layout__combo");

        RouterLink login = new RouterLink(null,Login.class);
        login.add(loginButton);

        comboLogin.add(languages, login);

        HorizontalLayout top = new HorizontalLayout();
        top.addClassName("main-layout__top");

        VerticalLayout body = new VerticalLayout();
        body.setDefaultHorizontalComponentAlignment(FlexComponent.Alignment.STRETCH);

        HorizontalLayout nav = new HorizontalLayout();
        nav.addClassName("navigation");
        name.setText("SemStew Restaurant");

        Div names = new Div();
        names.addClassName("title");
        names.add(name);

        Div navigation = new Div();
        navigation.addClassName("nav");

        intro = new RouterLink(null,IntroList.class);
        intro.add(new Icon(VaadinIcons.GLOBE), new Text("Home"));
        intro.addClassName("nav-item");

        menus = new RouterLink(null, MenusList.class);
        menus.add(new Icon(VaadinIcons.CROSS_CUTLERY), new Text("Menu"));
        menus.addClassName("nav-item");

        contacts = new RouterLink(null, ContactsList.class);
        contacts.add(new Icon(VaadinIcons.PHONE), new Text("Contacts"));
        contacts.addClassName("nav-item");

        about = new RouterLink(null, AboutList.class);
        about.add(new Icon(VaadinIcons.INFO), new Text("About"));
        about.addClassName("nav-item");

        reservations = new RouterLink(null,ReservationList.class);
        reservations.add(new Icon(VaadinIcons.NOTEBOOK), new Text("Reservations"));
        reservations.addClassName("nav-item");

        orders = new RouterLink(null,OrderList.class);
        orders.add(new Icon(VaadinIcons.BOOK), new Text("Orders"));
        orders.addClassName("nav-item");

        navigation.add(intro, menus, contacts, about, reservations, orders);

        nav.add(names, navigation);

        body.add(nav);

        bottom.addClassName("main-layout__bottom");
        Div foot = new Div();
        foot.addClassName("foot");



        Text foot__text = new Text("Design and developed by DreamTeam © SemStew CMS");
        foot.add(foot__text);
        bottom.add(foot);

        top.add(image,comboLogin);

        add(top, body, bottom);

        addClassName("main-layout");
    }


    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        // updating the active menu item based on if either of views is active
        // (note that this is triggered even for the error view)

        remove(bottom);

        String segment = event.getLocation().getFirstSegment();
        boolean introActive = segment.equals(intro.getHref());
        boolean menusActive = segment.equals(menus.getHref());
        boolean contactsActive = segment.equals(contacts.getHref());
        boolean aboutActive = segment.equals(about.getHref());
        boolean reservationsActive = segment.equals(reservations.getHref());
        boolean ordersActive = segment.equals(orders.getHref());

        intro.setClassName(ACTIVE_ITEM_STYLE, introActive);
        menus.setClassName(ACTIVE_ITEM_STYLE, menusActive);
        contacts.setClassName(ACTIVE_ITEM_STYLE, contactsActive);
        contacts.setClassName(ACTIVE_ITEM_STYLE, aboutActive);
        reservations.setClassName(ACTIVE_ITEM_STYLE, reservationsActive);
        orders.setClassName(ACTIVE_ITEM_STYLE, ordersActive);

        add(bottom);
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }
}
