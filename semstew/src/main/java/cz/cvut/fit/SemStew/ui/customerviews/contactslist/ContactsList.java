package cz.cvut.fit.SemStew.ui.customerviews.contactslist;

import JOOQ.tables.records.ContactConfigRecord;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.ContactConfigService;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.ui.CustomerLayout;

import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
@Route(value = "contacts", layout = CustomerLayout.class)
@PageTitle("Contacts | Home")
public class ContactsList extends VerticalLayout {
    /**
     * Page header
     */
    private final H2 header = new H2();
    /**
     * Page description
     */
    private final Text description = new Text("");
    /**
     * Page image number 1
     */
    private final Image image1 = new Image();
    /**
     * Page image number 2
     */
    private final Image image2 = new Image();
    /**
     * Page management
     */
    private ContactConfigService contactsService = new ContactConfigService();
    /**
     * Language management
     */
    private LanguagesService languagesService = new LanguagesService();

    /**
     * ContactsList constructor
     *
     * Use {@link #ContactsList()} to create and initialize page
     *
     */
    public ContactsList(){
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
        addClassName("contacts");
        getElement().setAttribute("itemtype", "http://schema.org/Restaurant");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    /**
     * Load content
     *
     * Use {@link #addContent()} to load content and data into the page
     *
     */
    private void addContent()
    {
        VerticalLayout content = new VerticalLayout();
        content.addClassName("content");
        content.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        HorizontalLayout subcontent = new HorizontalLayout();
        VerticalLayout imageContainer = new VerticalLayout();
        imageContainer.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
        imageContainer.addClassName("pictures");

        String name = "English";

        if(VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language") != null) {
            name = VaadinService.getCurrentRequest().getWrappedSession().getAttribute("language").toString();
        }

        int id = languagesService.GetIdByName(name);

        ContactConfigRecord configSet = contactsService.GetByLanguageId(id);

        header.setText(configSet.getHeader());
        content.getElement().setAttribute("name", configSet.getHeader());
        description.setText(configSet.getDescription());
        content.getElement().setAttribute("description", configSet.getDescription());

        //image1.setSrc(configSet.getUrlImage1());
        //image2.setSrc(configSet.getUrlImage2());

        imageContainer.add(image1,image2);
        subcontent.add(description, imageContainer);
        content.add(header,subcontent);
        add(content);
    }
}
