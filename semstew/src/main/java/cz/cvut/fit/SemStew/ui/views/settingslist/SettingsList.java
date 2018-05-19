package cz.cvut.fit.SemStew.ui.views.settingslist;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
@Route(value = "admin/settings", layout = MainLayout.class)
@PageTitle("Settings List | Admin")
public class SettingsList extends GeneralAdminList {

    /**
     * page header
     */
    private final H2 header = new H2();

    /**
     * SettingsList constructor
     *
     * Use {@link #SettingsList()} to create and initialize page
     *
     */
    public SettingsList() {
        init();
        addContent();
    }

    /**
     * Initialize page
     *
     * Use {@link #init()} to initialize page
     *
     */
    private void init() {
        super.addClassName("settings-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    /**
     * Load content
     *
     * Use {@link #addContent()} to load and set up page content
     *
     */
    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Settings");

        content.add(header);

        super.add(content);
    }
}