package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.MenusNameRecord;
import JOOQ.tables.records.MenusRecord;

public class MenuRepresentation {

    // menus
    Integer idMenu;
    Integer idBranch;
    String imageAddress;

    // menus name
    Integer idLanguage;
    String description;


    public MenuRepresentation(){}

    public void Load(MenusRecord menusIn, MenusNameRecord menusNameIn)
    {
        idMenu = menusIn.getIdMenu();
        idBranch = menusIn.getIdBranch();
        imageAddress = menusIn.getUrlImage();

        idLanguage = menusNameIn.getIdLanguage();
        description = menusNameIn.getDescription();
    }

    public MenusRecord GetMenus()
    {
        MenusRecord tmp = new MenusRecord();

        tmp.setIdMenu(idMenu);
        tmp.setIdBranch(idBranch);
        tmp.setUrlImage(imageAddress);

        return tmp;
    }

    public MenusNameRecord GetMenusName()
    {
        MenusNameRecord tmp = new MenusNameRecord();

        tmp.setIdMenu(idMenu);
        tmp.setIdLanguage(idLanguage);
        tmp.setDescription(description);

        return tmp;
    }

    public Integer getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    public Integer getIdBranch() {
        return idBranch;
    }

    public void setIdBranch(Integer idBranch) {
        this.idBranch = idBranch;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public Integer getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(Integer idLanguage) {
        this.idLanguage = idLanguage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
