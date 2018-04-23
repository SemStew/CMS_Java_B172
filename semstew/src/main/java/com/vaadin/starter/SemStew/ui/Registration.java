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
import com.vaadin.starter.SemStew.ui.views.appearancelist.AppearanceList;
import com.vaadin.starter.SemStew.ui.views.articlelist.ArticleList;
import com.vaadin.starter.SemStew.ui.views.gallerylist.GalleryList;
import com.vaadin.starter.SemStew.ui.views.introlist.IntroList;
import com.vaadin.starter.SemStew.ui.views.menulist.MenuList;
import com.vaadin.starter.SemStew.ui.views.previewlist.PreviewList;
import com.vaadin.starter.SemStew.ui.views.reservationslist.ReservationsList;
import com.vaadin.starter.SemStew.ui.views.settingslist.SettingsList;
import com.vaadin.starter.SemStew.ui.views.statisticslist.StatisticsList;

import java.util.Collection;

@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class Registration extends Div
        implements RouterLayout, PageConfigurator {

    public Registration() {

    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }
}
