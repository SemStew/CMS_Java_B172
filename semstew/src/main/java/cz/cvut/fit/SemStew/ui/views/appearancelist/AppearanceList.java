package cz.cvut.fit.SemStew.ui.views.appearancelist;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

@Route(value = "admin/appearance", layout = MainLayout.class)
@PageTitle("Appearance List | Admin")
public class AppearanceList extends GeneralAdminList {
    private final H2 header = new H2();

    public AppearanceList() {
        init();
        addContent();
    }

    private void init() {
        super.addClassName("appearance-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Appearance");

        content.add(header);

        super.add(content);
    }
}