package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.MenusNameRecord;
import JOOQ.tables.records.MenusRecord;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class MenuRepresentation {

    /**
     * menus id
     */
    Integer idMenu;
    /**
     * menus branch ID
     */
    Integer idBranch;
    /**
     * menus image address
     */
    String imageAddress;

    /**
     * menus name language id
     */
    Integer idLanguage;
    /**
     * menus name description
     */
    String description;

    /**
     * MenuRepresentation constructor
     */
    public MenuRepresentation(){}

    /**
     * Load data into MenuRepresentation
     *
     * Use {@link #Load(MenusRecord, MenusNameRecord)} to load data into MenuRepresentation
     *
     * @param menusIn Menus data part
     * @param menusNameIn MenusName data part
     */
    public void Load(MenusRecord menusIn, MenusNameRecord menusNameIn)
    {
        idMenu = menusIn.getIdMenu();
        idBranch = menusIn.getIdBranch();
        imageAddress = menusIn.getUrlImage();

        idLanguage = menusNameIn.getIdLanguage();
        description = menusNameIn.getDescription();
    }

    /**
     * Get MenusRecord data part
     *
     * Use {@link #GetMenus()} to extract MenusRecord data part from MenuRepresentation
     *
     * @return MenusRecord extracted from MenuRepresentation
     */
    public MenusRecord GetMenus()
    {
        MenusRecord tmp = new MenusRecord();

        tmp.setIdMenu(idMenu);
        tmp.setIdBranch(idBranch);
        tmp.setUrlImage(imageAddress);

        return tmp;
    }

    /**
     * Get MenusNameRecord data part
     *
     * Use {@link #GetMenusName()} to extract MenusNameRecord from MenuRepresentation
     *
     * @return MenusNameRecord extracted from MenuRepresentation
     */
    public MenusNameRecord GetMenusName()
    {
        MenusNameRecord tmp = new MenusNameRecord();

        tmp.setIdMenu(idMenu);
        tmp.setIdLanguage(idLanguage);
        tmp.setDescription(description);

        return tmp;
    }

    /**
     * Getter idMenu
     * @return idMenu
     */
    public Integer getIdMenu() {
        return idMenu;
    }

    /**
     * Setter for idMenu
     * @param idMenu integer to set idMenu
     */
    public void setIdMenu(Integer idMenu) {
        this.idMenu = idMenu;
    }

    /**
     * Getter idBranch
     * @return idBranch
     */
    public Integer getIdBranch() {
        return idBranch;
    }

    /**
     * Setter for idBranch
     * @param idBranch integer to set idBranch
     */
    public void setIdBranch(Integer idBranch) {
        this.idBranch = idBranch;
    }

    /**
     * Getter imageAddress
     * @return imageAddress
     */
    public String getImageAddress() {
        return imageAddress;
    }

    /**
     * Setter for imageAddress
     * @param imageAddress string to set imageAddress
     */
    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    /**
     * Getter idLanguage
     * @return idLanguage
     */
    public Integer getIdLanguage() {
        return idLanguage;
    }

    /**
     * Setter for idLanguage
     * @param idLanguage integer to set idLanguage
     */
    public void setIdLanguage(Integer idLanguage) {
        this.idLanguage = idLanguage;
    }

    /**
     * Getter description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setter for description
     * @param description string to set description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
