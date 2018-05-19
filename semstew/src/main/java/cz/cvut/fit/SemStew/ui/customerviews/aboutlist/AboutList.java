package cz.cvut.fit.SemStew.ui.customerviews.aboutlist;

import JOOQ.tables.records.AboutUsConfigRecord;
import JOOQ.tables.records.ImagesRecord;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.AboutUsConfigService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.ImageServis;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
@Route(value = "about", layout = CustomerLayout.class)
@PageTitle("About | Home")
public class AboutList extends VerticalLayout {
    /**
     * Page header
     */
    private final H2 header = new H2();
    /**
     * Page description
     */
    private final Text description = new Text("");
    /**
     * Page sub-header
     */
    private final H3 subheader = new H3();
    /**
     * Page grid for pictures
     */
    private final Grid<ImagesRecord> recordGrid = new Grid<>();
    /**
     * Page management
     */
    private final AboutUsConfigService aboutUsConfigService = new AboutUsConfigService();
    /**
     * Language management
     */
    private final LanguagesService languagesService = new LanguagesService();
    /**
     * Images management
     */
    private final ImageServis imageServis = new ImageServis();

    /**
     * AboutList constructor
     *
     * Use {@link #AboutList()} to create instance of AboutList page and initialize it's content
     *
     */
    public AboutList()
    {
        init();
        addContent();
    }

    /**
     * Initializes page
     *
     * Use {@link #init()} to initialize page
     *
     */
    private void init()
    {
        setClassName("about");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    /**
     * Loads page contents
     *
     * Use {@link #addContent()} to load and set content of this page
     *
     */
    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);

        String name = "English";

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") != null) {
            name = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString();
        }

        int id = languagesService.GetIdByName(name);

        List<ImagesRecord> recordList = imageServis.getConfigs();

        AboutUsConfigRecord aboutUsConfigRecord = aboutUsConfigService.GetByLanguageId(id);

        header.setText(aboutUsConfigRecord.getHeader());

        description.setText(aboutUsConfigRecord.getDescription());

        subheader.setText(aboutUsConfigRecord.getFotogalleryHeader());

        recordGrid.setItems(recordList);
        recordGrid.addColumn(new ComponentRenderer<>(imagesRecord -> {
            Image tmp = new Image();
            tmp.setSrc(imagesRecord.getImage());
            tmp.setClassName("picture_alone");
            return tmp;
        }));




        content.add(header, description, subheader, recordGrid);

        add(content);
    }
}
