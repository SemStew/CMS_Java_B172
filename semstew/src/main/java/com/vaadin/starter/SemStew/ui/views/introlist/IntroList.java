package com.vaadin.starter.SemStew.ui.views.introlist;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.starter.SemStew.backend.IntroConfig;
import com.vaadin.starter.SemStew.ui.MainLayout;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.starter.SemStew.ui.views.aboutlist.AboutList;
import com.vaadin.starter.SemStew.ui.views.contactsList.ContactsList;
import com.vaadin.starter.SemStew.ui.views.introlist.IntroList;
import com.vaadin.starter.SemStew.ui.views.menulist.MenuList;
import com.vaadin.starter.SemStew.ui.views.reservationslist.ReservationsList;

import java.util.Collection;

@Route(value = "", layout = MainLayout.class)
@PageTitle("Intro List")
public class IntroList extends VerticalLayout
    implements AfterNavigationObserver {

    private static final String ACTIVE_ITEM_STYLE = "main-layout__nav-item--selected";
    //private RouterLink categories;
    //private RouterLink reviews;
    private RouterLink intro;
    private RouterLink menu;
    private RouterLink about;
    private RouterLink contacts;
    private RouterLink reservations;

    private final H2 header = new H2();
    private final Grid<IntroConfig> actualities = new Grid<>();

    public IntroList() {
        init();
        addContent();
        addMenu();
    }

    private void init() {
        addClassName("intro-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Actualities");

        actualities.setSelectionMode(Grid.SelectionMode.NONE);

        content.add(header);

        add(content);
    }

    private void addMenu() {
        H2 title = new H2("SemStew CMS");
        title.addClassName("title");

        Div navigation = new Div(title);
        navigation.addClassName("nav");

        /*reviews = new RouterLink(null, ReviewsList.class);
        reviews.add(new Icon(VaadinIcons.LIST), new Text("Reviews"));
        reviews.addClassName("main-layout__nav-item");*/

        /*categories = new RouterLink(null, CategoriesList.class);
        categories.add(new Icon(VaadinIcons.ARCHIVES), new Text("Categories"));
        categories.addClassName("main-layout__nav-item");*/

        intro = new RouterLink(null, IntroList.class);
        intro.add(new Icon(VaadinIcons.BULLETS), new Text("Intro"));
        intro.addClassName("nav-item");

        menu = new RouterLink(null, MenuList.class);
        menu.add(new Icon(VaadinIcons.CROSS_CUTLERY), new Text("Menu"));
        menu.addClassName("nav-item");

        about = new RouterLink(null, AboutList.class);
        about.add(new Icon(VaadinIcons.INFO_CIRCLE), new Text("About us"));
        about.addClassName("nav-item");

        contacts = new RouterLink(null, ContactsList.class);
        contacts.add(new Icon(VaadinIcons.BOOK), new Text("Contacts"));
        contacts.addClassName("nav-item");

        reservations = new RouterLink(null, ReservationsList.class);
        reservations.add(new Icon(VaadinIcons.NOTEBOOK), new Text("Reservations"));
        reservations.addClassName("nav-item");

        navigation.add(intro, menu, about, contacts, reservations);

        add(navigation);
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        // updating the active menu item based on if either of views is active
        // (note that this is triggered even for the error view)
        String segment = event.getLocation().getFirstSegment();
        //boolean reviewsActive = segment.equals(reviews.getHref());
        /*boolean categoriesActive = segment.equals(categories.getHref());*/
        boolean introActive = segment.equals(intro.getHref());
        boolean menuActive = segment.equals(menu.getHref());
        boolean aboutActive = segment.equals(about.getHref());
        boolean contactsActive = segment.equals(contacts.getHref());
        boolean reservationsActive = segment.equals(contacts.getHref());

        //reviews.setClassName(ACTIVE_ITEM_STYLE, reviewsActive);
        /*categories.setClassName(ACTIVE_ITEM_STYLE, categoriesActive);*/
        intro.setClassName(ACTIVE_ITEM_STYLE, introActive);
        menu.setClassName(ACTIVE_ITEM_STYLE, menuActive);
        about.setClassName(ACTIVE_ITEM_STYLE, aboutActive);
        contacts.setClassName(ACTIVE_ITEM_STYLE, contactsActive);
        reservations.setClassName(ACTIVE_ITEM_STYLE, reservationsActive);
    }
}