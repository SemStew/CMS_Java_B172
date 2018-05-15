package cz.cvut.fit.SemStew.ui.views.articlelist;

import JOOQ.tables.records.LanguagesRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.LocalDateRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.backend.Controllers.NewsController;
import cz.cvut.fit.SemStew.backend.Controllers.NewsRepresentation;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.LanguagesService;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Route(value = "admin/articles", layout = MainLayout.class)
@PageTitle("Article List | Admin")
public class ArticleList extends GeneralAdminList {
    private final H2 header = new H2();
    private final Grid<NewsRepresentation> representationGrid = new Grid<>();
    private final Button add = new Button();
    private final Dialog editDialog = new Dialog();
    private final NewsController newsController = new NewsController();
    private final LanguagesService languagesService = new LanguagesService();
    private List<NewsRepresentation> representationList;

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

        representationList = newsController.getItems();

        representationGrid.setItems(representationList);
        representationGrid.addColumn(NewsRepresentation::getHeader).setComparator(Comparator.comparing(NewsRepresentation::getHeader))
                .setHeader("News name").setSortable(true);
        representationGrid.addColumn(NewsRepresentation::getDescription);
        representationGrid.addColumn(new LocalDateRenderer<>(NewsRepresentation::getnDate,
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))).setComparator(Comparator.comparing(NewsRepresentation::getnDate)).
                setHeader("Date").setSortable(true);
        representationGrid.addColumn(new ComponentRenderer<>(clickedItem ->{
            Button tmp = new Button(new Icon(VaadinIcons.EDIT));
            tmp.addClickListener(buttonClickEvent -> {
                SetUpEditDialog(clickedItem);
                editDialog.open();
            });
            return tmp;
        }));
        representationGrid.addColumn(new ComponentRenderer<>(clickedItem ->{
            Button tmp = new Button(new Icon(VaadinIcons.CLOSE));
            tmp.addClickListener(buttonClickEvent -> {
                newsController.Delete(clickedItem);
                Refresh();
            });
            return tmp;
        }));

        editDialog.setCloseOnOutsideClick(false);
        editDialog.setCloseOnEsc(true);

        add.setText("Add");

        add.addClickListener(buttonClickEvent -> {
            SetUpAddDialog();
            editDialog.open();
        });

        content.add(header, representationGrid, add);

        super.add(content);
    }

    private void Refresh(){
        representationList = newsController.getItems();
        representationGrid.setItems(representationList);
    }

    private void SetUpEditDialog(NewsRepresentation update){
        editDialog.removeAll();
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label headLabel = new Label("Update");

        TextField header = new TextField();
        header.setLabel("News header");
        header.setValue(update.getHeader());
        TextField description = new TextField();
        description.setLabel("Message");
        description.setValue(update.getDescription());

        Label infoLabel = new Label();

        Button add = new Button("Update");
        Button close = new Button("Close");

        add.addClickListener(buttonClickEvent -> {
            if(header.isEmpty() || description.isEmpty()){
                infoLabel.setText("Fill all fields");
                return;
            }
            update.setDescription(description.getValue());
            update.setHeader(header.getValue());
            update.setnDate(LocalDate.now());
            newsController.Update(update);
            Refresh();
            infoLabel.setText("Updated");
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(close, add);
        content.add(headLabel,header,description,buttons,infoLabel);
        editDialog.add(content);
    }

    private void SetUpAddDialog(){
        editDialog.removeAll();
        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        List<NewsRepresentation> newsRepresentations = new ArrayList<>();

        Label headLabel = new Label("Add");

        List<LanguagesRecord> languagesRecords = languagesService.getConfigs();

        List<String> languageNames = new ArrayList<>();
        for(LanguagesRecord rec : languagesRecords){
            languageNames.add(rec.getName());
        }

        ComboBox<String> language = new ComboBox<>();
        language.setItems(languageNames);
        language.setValue(languageNames.get(0));

        TextField header = new TextField();
        header.setLabel("News header");
        TextField description = new TextField();
        description.setLabel("Message");

        Label infoLabel = new Label();

        Button add = new Button("Add");
        Button close = new Button("Close");

        language.addValueChangeListener(valueChangeEvent -> {
            if(languageNames.stream().filter(nameLang -> nameLang.equals(language.getValue())).count() == 0){
                language.setErrorMessage("Select existing language");
                language.setInvalid(true);
                return;
            }
            if(header.isEmpty() || description.isEmpty()){
                infoLabel.setText("Fill all fields");
                return;
            }
            int languageId = languagesService.GetIdByName(valueChangeEvent.getOldValue());
            if(newsRepresentations.stream().filter(news -> news.getLanguageId().equals(languageId)).count() == 0){
                NewsRepresentation tmp = new NewsRepresentation();
                tmp.setDescription(description.getValue());
                tmp.setLanguageId(languageId);
                tmp.setRestaurantId(1);
                tmp.setHeader(header.getValue());
                tmp.setnDate(LocalDate.now());
                newsRepresentations.add(tmp);
            }
        });

        add.addClickListener(buttonClickEvent -> {
           if(header.isEmpty() || description.isEmpty()){
               infoLabel.setText("Fill all fields");
               return;
           }
           if(newsRepresentations.size() < languageNames.size() - 1){
               infoLabel.setText("Fill all languages");
               return;
           }
           int languageId = languagesService.GetIdByName(language.getValue());
           NewsRepresentation tmp = new NewsRepresentation();
           tmp.setDescription(description.getValue());
           tmp.setHeader(header.getValue());
           tmp.setRestaurantId(1);
           tmp.setLanguageId(languageId);
           tmp.setnDate(LocalDate.now());
           if(newsRepresentations.stream().filter(news -> news.getLanguageId().equals(languageId)).count() !=0){
               if(newsRepresentations.size() == languageNames.size()){
                   newsController.InsertMultilingual(newsRepresentations);
                   Refresh();
                   infoLabel.setText("Added");
                   return;
               }
           } else {
               if(newsRepresentations.size() + 1 == languageNames.size()){
                   newsRepresentations.add(tmp);
                   newsController.InsertMultilingual(newsRepresentations);
                   Refresh();
                   infoLabel.setText("Added");
                   return;
               }
           }
           infoLabel.setText("Fill all languages");
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(close, add);
        content.add(headLabel,language,header,description,buttons,infoLabel);
        editDialog.add(content);
    }
}