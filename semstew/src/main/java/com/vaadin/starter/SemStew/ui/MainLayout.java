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
import com.vaadin.flow.component.textfield.TextField;

import java.util.Collection;

@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainLayout extends Div
        implements RouterLayout, PageConfigurator {

    public MainLayout() {
        Image image = new Image();
        image.setSrc("https://scontent-frt3-2.xx.fbcdn.net/v/t35.0-12/s2048x2048/29680738_2052341935036421_876125089_o.png?_nc_cat=0&oh=72dbec5b54c6fb1e576570ecfe3c14aa&oe=5AD6807A");
        image.addClassName("main-layout__picture");

        ComboBox<String> languages = new ComboBox<>("Languages");
        languages.setItems("Czech","English");
        languages.setValue("English");
        languages.addClassName("main-layout__combo");

        HorizontalLayout top = new HorizontalLayout();
        HorizontalLayout bottom = new HorizontalLayout();
        top.addClassName("main-layout__top");
        bottom.addClassName("main-layout__bottom");

        Div foot = new Div ();
        foot.addClassName("foot");

        TextField foot__text = new TextField("Design and developed by DreamTeam © SemStew CMS");
        foot.add(foot__text);

        top.add(image,languages);
        bottom.add(foot);
        add(top,bottom);

        addClassName("main-layout");
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }
}
