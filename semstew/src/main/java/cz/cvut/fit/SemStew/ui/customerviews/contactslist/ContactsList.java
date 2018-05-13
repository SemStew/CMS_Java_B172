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

@Route(value = "contacts", layout = CustomerLayout.class)
@PageTitle("Contacts | Home")
public class ContactsList extends VerticalLayout {
    private final H2 header = new H2();
    private final Text description = new Text("");
    private final Image image1 = new Image();
    private final Image image2 = new Image();
    private ContactConfigService contactsService = new ContactConfigService();
    private LanguagesService languagesService = new LanguagesService();

    public ContactsList(){
        init();
        addContent();
    }

    private void init()
    {
        addClassName("contacts");
        setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

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
        description.setText(configSet.getDescription());

        //image1.setSrc(configSet.getUrlImage1());
        //image2.setSrc(configSet.getUrlImage2());

        imageContainer.add(image1,image2);
        subcontent.add(description, imageContainer);
        content.add(header,subcontent);
        add(content);
    }
}
