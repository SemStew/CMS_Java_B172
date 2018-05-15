package cz.cvut.fit.SemStew.ui.customerviews.introlist;

import JOOQ.tables.records.IntroConfigRecord;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Controllers.NewsController;
import cz.cvut.fit.SemStew.backend.Controllers.NewsRepresentation;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.IntroConfigService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Comparator;
import java.util.List;

@Route(value = "", layout = CustomerLayout.class)
@PageTitle("Home")
public class IntroList extends VerticalLayout {
    private final Text introText = new Text("");
    private final H2 header = new H2();
    private final Grid<NewsRepresentation> representationGrid = new Grid<>();
    private final IntroConfigService introConfigService = new IntroConfigService();
    private final LanguagesService languagesService = new LanguagesService();
    private final NewsController newsController = new NewsController();

    public IntroList()
    {
        init();
        addContent();
    }

    private void init()
    {
        addClassName("intro-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        PostgreSQLConnection postgre = new PostgreSQLConnection();
        VerticalLayout content = new VerticalLayout();
        content.setAlignItems(Alignment.STRETCH);

        String name = "English";

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") != null) {
            name = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString();
        }
        int id = languagesService.GetIdByName(name);

        IntroConfigRecord rec = introConfigService.GetByLanquageID(id);

        introText.setText(rec.getHeader());

        header.setText(rec.getNewsHeader());

        List<NewsRepresentation> items = newsController.getItems();

        representationGrid.setItems(items);
        representationGrid.addColumn(NewsRepresentation::getHeader).setComparator(Comparator.comparing(NewsRepresentation::getHeader))
                .setHeader("News name").setSortable(true);
        representationGrid.addColumn(NewsRepresentation::getDescription);
        representationGrid.addColumn(new LocalDateRenderer<>(NewsRepresentation::getnDate,
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))).setComparator(Comparator.comparing(NewsRepresentation::getnDate)).
                setHeader("Date").setSortable(true);


        content.add(introText, header, representationGrid);
        add(content);
    }
}
