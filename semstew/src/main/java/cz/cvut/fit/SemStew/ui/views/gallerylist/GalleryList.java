package cz.cvut.fit.SemStew.ui.views.gallerylist;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

@Route(value = "admin/gallery", layout = MainLayout.class)
@PageTitle("Gallery List | Admin")
public class GalleryList extends GeneralAdminList {

    private final H2 header = new H2();

    public GalleryList() {
        init();
        addContent();
    }

    private void init() {
        super.addClassName("gallery-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Gallery");

        content.add(header);

        add(content);
    }
}