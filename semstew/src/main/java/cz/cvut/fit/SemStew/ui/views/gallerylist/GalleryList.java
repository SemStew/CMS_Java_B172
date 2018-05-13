package cz.cvut.fit.SemStew.ui.views.gallerylist;

import JOOQ.tables.records.ImagesRecord;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcons;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H2;
import cz.cvut.fit.SemStew.backend.Services.GeneralPageConfig.ImageServis;
import cz.cvut.fit.SemStew.ui.MainLayout;
import cz.cvut.fit.SemStew.ui.views.GeneralAdminList;

import java.util.List;

@Route(value = "admin/gallery", layout = MainLayout.class)
@PageTitle("Gallery List | Admin")
public class GalleryList extends GeneralAdminList {

    private final H2 header = new H2();
    private final Grid<ImagesRecord> recordGrid = new Grid<>();
    private final Dialog editDialog = new Dialog();
    private final ImageServis imageServis = new ImageServis();
    private final Button add = new Button();
    private List<ImagesRecord> imagesRecords;

    public GalleryList() {
        init();
        addContent();
    }

    private void init() {
        super.addClassName("gallery-list");
        super.setDefaultHorizontalComponentAlignment(Alignment.STRETCH);
    }

    private void addContent() {
        VerticalLayout content = new VerticalLayout();
        content.setClassName("content");
        content.setAlignItems(Alignment.STRETCH);

        header.setText("Gallery");

        imagesRecords = imageServis.getConfigs();

        recordGrid.setItems(imagesRecords);
        recordGrid.addColumn(new ComponentRenderer<>(imageRecord -> {
            Image tmp = new Image();
            tmp.setClassName("picture_alone");
            tmp.setSrc(imageRecord.getImage());
            return tmp;
        }));
        recordGrid.addColumn(new ComponentRenderer<>(imageRecord -> {
            Button tmp = new Button(new Icon(VaadinIcons.EDIT));
            tmp.addClickListener(buttonClickEvent -> {
                SetUpEditDialog(imageRecord);
                editDialog.open();
            });
            return tmp;
        }));
        recordGrid.addColumn(new ComponentRenderer<>(imageRecord ->{
            Button tmp = new Button(new Icon(VaadinIcons.CLOSE));
            tmp.addClickListener(buttonClickEvent -> {
                imageServis.Delete(imageRecord);
                Refresh();
            });
            return tmp;
        }));

        add.setText("Add");

        add.addClickListener(buttonClickEvent -> {
            SetUpAddDialog();
            editDialog.open();
        });

        editDialog.setCloseOnOutsideClick(true);
        editDialog.setCloseOnEsc(true);

        content.add(header,recordGrid, add);

        add(content);
    }

    private void Refresh(){
        imagesRecords = imageServis.getConfigs();
        recordGrid.setItems(imagesRecords);
    }

    private void SetUpEditDialog(ImagesRecord in){
        editDialog.removeAll();

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label header = new Label("Update");
        TextField imageSource = new TextField("Image source");
        imageSource.setValue(in.getImage());
        Button close = new Button("Close");
        Button update = new Button("Update");
        Label infoLabel = new Label();

        update.addClickListener(buttonClickEvent -> {
            if(imageSource.isEmpty()){
                infoLabel.setText("Fill all fields");
                return;
            }
            in.setImage(imageSource.getValue());
            imageServis.Update(in);
            Refresh();
            infoLabel.setText("Updated");
        });

        close.addClickListener(buttonClickEvent -> {
            editDialog.close();
        });

        buttons.add(close, update);
        content.add(header, imageSource, buttons, infoLabel);
        editDialog.add(content);
    }

    private void SetUpAddDialog(){
        editDialog.removeAll();

        VerticalLayout content = new VerticalLayout();
        HorizontalLayout buttons = new HorizontalLayout();

        Label header = new Label("Add");
        TextField imageSource = new TextField("Image source");
        Button close = new Button("Close");
        Button add = new Button("Add");
        Label infoLabel = new Label();

        add.addClickListener(buttonClickEvent -> {
            if(imageSource.isEmpty()){
                infoLabel.setText("Fill all fields");
                return;
            }
            ImagesRecord tmp = new ImagesRecord();
            tmp.setImage(imageSource.getValue());
            tmp.setIdRestaurant(1);
            imageServis.Insert(tmp);
            Refresh();
            infoLabel.setText("Added");
        });

        close.addClickListener(buttonClickEvent -> {
           editDialog.close();
        });

        buttons.add(close, add);
        content.add(header, imageSource, buttons, infoLabel);
        editDialog.add(content);
    }
}