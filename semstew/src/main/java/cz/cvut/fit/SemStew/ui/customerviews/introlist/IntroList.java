package cz.cvut.fit.SemStew.ui.customerviews.introlist;

import JOOQ.tables.records.IntroConfigRecord;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.PostgreSQLConnection;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.IntroConfigService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

@Route(value = "", layout = CustomerLayout.class)
@PageTitle("Home")
public class IntroList extends VerticalLayout {
    private final Text introText = new Text("");
    private final H2 header = new H2();
    private final IntroConfigService introConfigService = new IntroConfigService();
    private final LanguagesService languagesService = new LanguagesService();

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

        content.add(introText, header);
        add(content);
    }
}
