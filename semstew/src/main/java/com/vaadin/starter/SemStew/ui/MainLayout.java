/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.vaadin.starter.SemStew.ui;

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

/**
 * The main layout contains the header with the navigation buttons, and the
 * child views below that.
 */
@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainLayout extends Div
        implements RouterLayout, AfterNavigationObserver, PageConfigurator {

    private static final String ACTIVE_ITEM_STYLE = "main-layout__nav-item--selected";
    //private RouterLink categories;
    //private RouterLink reviews;
    private RouterLink intro;
    private RouterLink menu;
    private RouterLink about;
    private RouterLink contacts;
    private RouterLink reservations;

    public MainLayout() {
        H2 title = new H2("SemStew restaurant example");
        title.addClassName("main-layout__title");

        Image image = new Image();
        image.setSrc("https://scontent-frt3-2.xx.fbcdn.net/v/t35.0-12/s2048x2048/29680738_2052341935036421_876125089_o.png?_nc_cat=0&oh=72dbec5b54c6fb1e576570ecfe3c14aa&oe=5AD6807A");
        image.addClassName("main-layout__picture");

        ComboBox<String> languages = new ComboBox<>("Languages");
        languages.setItems("Czech","English");
        languages.setValue("English");
        languages.addClassName("main-layout__combo");

        HorizontalLayout top = new HorizontalLayout();
        top.addClassName("main-layout__top");

        /*reviews = new RouterLink(null, ReviewsList.class);
        reviews.add(new Icon(VaadinIcons.LIST), new Text("Reviews"));
        reviews.addClassName("main-layout__nav-item");*/

        /*categories = new RouterLink(null, CategoriesList.class);
        categories.add(new Icon(VaadinIcons.ARCHIVES), new Text("Categories"));
        categories.addClassName("main-layout__nav-item");*/

        intro = new RouterLink(null,IntroList.class);
        intro.add(new Icon(VaadinIcons.BULLETS),new Text("Intro"));
        intro.addClassName("main-layout__nav-item");

        menu = new RouterLink(null,MenuList.class);
        menu.add(new Icon(VaadinIcons.CROSS_CUTLERY), new Text("Menu"));
        menu.addClassName("main-layout__nav-item");

        about = new RouterLink(null, AboutList.class);
        about.add(new Icon(VaadinIcons.INFO_CIRCLE),new Text("About us"));
        about.addClassName("main-layout__nav-item");

        contacts = new RouterLink(null,ContactsList.class);
        contacts.add(new Icon(VaadinIcons.BOOK),new Text("Contacts"));
        contacts.addClassName("main-layout__nav-item");

        reservations = new RouterLink(null,ReservationsList.class);
        reservations.add(new Icon(VaadinIcons.NOTEBOOK),new Text("Reservations"));
        reservations.addClassName("main-layout__nav-item");

        Div navigation = new Div(intro, menu, about, contacts,reservations);
        navigation.addClassName("main-layout__nav");

        Div header = new Div(title, navigation);
        header.addClassName("main-layout__header");
        top.add(image,languages);
        add(top, header);

        addClassName("main-layout");
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
        intro.setClassName(ACTIVE_ITEM_STYLE,introActive);
        menu.setClassName(ACTIVE_ITEM_STYLE,menuActive);
        about.setClassName(ACTIVE_ITEM_STYLE,aboutActive);
        contacts.setClassName(ACTIVE_ITEM_STYLE,contactsActive);
        reservations.setClassName(ACTIVE_ITEM_STYLE,reservationsActive);
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }
}
