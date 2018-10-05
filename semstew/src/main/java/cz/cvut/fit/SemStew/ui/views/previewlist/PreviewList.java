package cz.cvut.fit.SemStew.ui.views.previewlist;

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
@Route(value = "admin/preview", layout = MainLayout.class)
@PageTitle("Preview List | Admin")
public class PreviewList extends GeneralAdminList {

    /**
     * page header
     */
    private final H2 header = new H2();

    /**
     * PreviewList constructor
     *
     * Use {@link #PreviewList()} to create and initialize page
     *
     */
    public PreviewList() {
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
        super.addClassName("preview-list");
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

        header.setText("Preview");

        content.add(header);

        add(content);
    }
}