package cz.cvut.fit.SemStew.ui.views.articlelist;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

@Route(value = "admin/articles", layout = MainLayout.class)
@PageTitle("Article List | Admin")
public class ArticleList extends GeneralAdminList {
    private final H2 header = new H2();

    public ArticleList() {
        init();
        addContent();
    }

    private void init() {
        super.addClassName("article-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Articles");

        content.add(header);

        super.add(content);
    }
}