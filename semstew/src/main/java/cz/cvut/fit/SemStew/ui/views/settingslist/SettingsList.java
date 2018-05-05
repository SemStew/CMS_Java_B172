package cz.cvut.fit.SemStew.ui.views.settingslist;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

@Route(value = "admin/settings", layout = MainLayout.class)
@PageTitle("Settings List | Admin")
public class SettingsList extends GeneralAdminList {

    private final H2 header = new H2();

    public SettingsList() {
        init();
        addContent();
    }

    private void init() {
        super.addClassName("settings-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Settings");

        content.add(header);

        super.add(content);
    }
}