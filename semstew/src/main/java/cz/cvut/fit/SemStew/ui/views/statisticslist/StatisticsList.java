package cz.cvut.fit.SemStew.ui.views.statisticslist;

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
@Route(value = "admin/statistics", layout = MainLayout.class)
@PageTitle("Statistics List | Admin")
public class StatisticsList extends GeneralAdminList {

    /**
     * page header
     */
    private final H2 header = new H2();

    /**
     * StatisticsList constructor
     *
     * Use {@link #StatisticsList()} to create and initialize page
     *
     */
    public StatisticsList() {
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
        super.addClassName("statistics-list");
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

        header.setText("Statistics");

        content.add(header);

        super.add(content);
    }
}