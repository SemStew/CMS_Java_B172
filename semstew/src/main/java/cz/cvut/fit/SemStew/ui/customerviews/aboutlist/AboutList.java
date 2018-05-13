package cz.cvut.fit.SemStew.ui.customerviews.aboutlist;

import JOOQ.tables.records.AboutUsConfigRecord;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.AboutUsConfigService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

@Route(value = "about", layout = CustomerLayout.class)
@PageTitle("About | Home")
public class AboutList extends VerticalLayout {
    private final H2 header = new H2();
    private final Text description = new Text("");
    private final H3 subheader = new H3();
    // Todo picture grid
    private final AboutUsConfigService aboutUsConfigService = new AboutUsConfigService();
    private final LanguagesService languagesService = new LanguagesService();

    public AboutList()
    {
        init();
        addContent();
    }

    private void init()
    {
        setClassName("about");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        String name = "English";

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") != null) {
            name = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString();
        }

        int id = languagesService.GetIdByName(name);

        AboutUsConfigRecord aboutUsConfigRecord = aboutUsConfigService.GetByLanguageId(id);

        header.setText(aboutUsConfigRecord.getHeader());

        description.setText(aboutUsConfigRecord.getDescription());

        subheader.setText(aboutUsConfigRecord.getFotogalleryHeader());

        content.add(header, description, subheader);

        add(content);
    }
}
