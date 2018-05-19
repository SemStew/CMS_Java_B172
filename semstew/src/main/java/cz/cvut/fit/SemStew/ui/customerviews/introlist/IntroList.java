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

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
@Route(value = "", layout = CustomerLayout.class)
@PageTitle("Home")
public class IntroList extends VerticalLayout {
    /**
     * Page intro text
     */
    private final Text introText = new Text("");
    /**
     * Page header
     */
    private final H2 header = new H2();
    /**
     * Page grid for displaying news
     */
    private final Grid<NewsRepresentation> representationGrid = new Grid<>();
    /**
     * Page management
     */
    private final IntroConfigService introConfigService = new IntroConfigService();
    /**
     * Page language management
     */
    private final LanguagesService languagesService = new LanguagesService();
    /**
     * Page news management
     */
    private final NewsController newsController = new NewsController();

    /**
     * IntroList constructor
     *
     * Use {@link #IntroList()} to initialize page and it's content
     *
     */
    public IntroList()
    {
        init();
        addContent();
    }

    /**
     * Initialize page
     *
     * Use {@link #init()} to initialize page
     *
     */
    private void init()
    {
        addClassName("intro-list");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    /**
     * Load content
     *
     * Use {@link #addContent()} to load and set up content of the page
     *
     */
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
