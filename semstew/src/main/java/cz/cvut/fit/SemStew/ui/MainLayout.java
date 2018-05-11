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
package cz.cvut.fit.SemStew.ui;

import JOOQ.tables.records.LanguagesRecord;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.HtmlImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.*;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PageConfigurator;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.GeneralConfigService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;

import java.util.ArrayList;
import java.util.List;

@HtmlImport("frontend://styles/shared-styles.html")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
public class MainLayout extends Div
        implements RouterLayout, PageConfigurator {

        private final GeneralConfigService generalService = new GeneralConfigService();
        private final LanguagesService languagesService = new LanguagesService();

    public MainLayout() {
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
        languages.addClassName("main-layout__combo");

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") == null)
            VaadinService.getCurrentRequest().getWrappedSession().setAttribute("language",languageNames.get(0));
        else
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

        HorizontalLayout top = new HorizontalLayout();
        top.addClassName("main-layout__top");

        top.add(image,languages);
        add(top);

        addClassName("main-layout");
    }

    @Override
    public void configurePage(InitialPageSettings settings) {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }
}