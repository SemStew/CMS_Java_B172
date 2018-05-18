package cz.cvut.fit.SemStew.backend.Controllers;

import JOOQ.tables.records.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author DreamTeam SemStew
 * @version 1.0
 * @since 0.5
 */
public class MenuItemRepresentation {

    /**
     * MenuItemNameRecord name
     */
    private String name;
    /**
     * MenuItemNameRecord description
     */
    private String description;
    /**
     * MenuItemNameRecord idLanguage
     */
    private Integer idLanguage;

    /**
     * MenuItemRecord amount
     */
    private BigDecimal amount;
    /**
     * MenuItemRecord idCategory
     */
    private Integer idCategory;
    /**
     * MenuItemRecord idMenuItem
     */
    private Integer idMenuItem;
    /**
     * MenuItemRecord idUnit
     */
    private Integer idUnit;
    /**
     * MenuItemRecord imageAddress
     */
    private String imageAddress;
    /**
     * MenuItemRecord price
     */
    private Integer price;
    /**
     * MenuItemRecord idMenu
     */
    private Integer idMenu;

    /**
     * CategoryName descrition
     */
    private String categoryDescription;

    /**
     * Units description
     */
    private String unitDescription;

    /**
     * Allergens
     */
    private List<String> allergens = new ArrayList<>();

    /**
     * MenuItemRepresentation constructor
     */
    public MenuItemRepresentation()
    {

    }

    /**
     * Load data to the representation
     *
     * Use {@link #Load(MenuItemNameRecord, MenuItemRecord, CategoriesNameRecord, UnitsRecord, List)} to load data into representation
     *
     * @param inName MenuItemName data part
     * @param inItem MenuItem data part
     * @param inCategoryName Category data part
     * @param unitsIn Unit data part
     * @param allergensIn Allergens data part
     */
    public void Load(MenuItemNameRecord inName, MenuItemRecord inItem, CategoriesNameRecord inCategoryName,
                     UnitsRecord unitsIn, List<AllergensNameRecord> allergensIn)
    {
        name = inName.getName();
        description = inName.getName();
        idLanguage = inName.getIdLanguage();

        amount = inItem.getAmount();
        idCategory = inItem.getIdCategory();
        idMenuItem = inItem.getIdMenuItem();
        idUnit = inItem.getIdUnit();
        imageAddress = inItem.getImageName();
        price = inItem.getPrice();
        idMenu = inItem.getIdMenu();

        categoryDescription = inCategoryName.getName();

        unitDescription = unitsIn.getName();
        for(AllergensNameRecord rec : allergensIn){
            allergens.add(rec.getAllergen());
        }
    }

    /**
     * Get ItemRecord
     *
     * Use {@link #GetItemRecord()} to extract MenuItemRecord from MenuItemRepresentation
     *
     * @return MenuItemRecord extracted from MenuItemRepresentation
     */
    public MenuItemRecord GetItemRecord()
    {
        MenuItemRecord item = new MenuItemRecord();

        item.setAmount(amount);
        item.setIdCategory(idCategory);
        item.setIdMenuItem(idMenuItem);
        item.setIdUnit(idUnit);
        item.setImageName(imageAddress);
        item.setPrice(price);
        item.setIdMenu(idMenu);

        return item;
    }

    /**
     * Get ItemNameRecord
     *
     * Use {@link #GetItemNameRecord()} to extract MenuItemNameRecord from MenuItemRepresentation
     *
     * @return MenuItemName extracted from MenuItemRepresentation
     */
    public MenuItemNameRecord GetItemNameRecord()
    {
        MenuItemNameRecord itemName = new MenuItemNameRecord();

        itemName.setIdLanguage(idLanguage);
        itemName.setIdMenuItem(idMenuItem);
        itemName.setDescription(description);
        itemName.setName(name);

        return itemName;
    }

    /**
     * Getter name
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter description
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Getter idLanguage
     * @return idLanguage
     */
    public Integer getIdLanguage() {
        return idLanguage;
    }

    /**
     * Getter amount
     * @return amount
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Getter idCategory
     * @return idCategory
     */
    public Integer getIdCategory() {
        return idCategory;
    }

    /**
     * Getter idMenuItem
     * @return idMenuItem
     */
    public Integer getIdMenuItem() {
        return idMenuItem;
    }

    /**
     * Getter idUnit
     * @return idUnit
     */
    public Integer getIdUnit() {
        return idUnit;
    }

    /**
     * Getter imageAddress
     * @return imageAddress
     */
    public String getImageAddress() {
        return imageAddress;
    }

    /**
     * Getter price
     * @return price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * Getter categoryDescription
     * @return
     */
    public String getCategoryDescription() {
        return categoryDescription;
    }

    /**
     * Setter for name
     * @param name string to set name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Setter for description
     * @param description string to set description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Setter for idLanguage
     * @param idLanguage integer to set idLanguage
     */
    public void setIdLanguage(Integer idLanguage) {
        this.idLanguage = idLanguage;
    }

    /**
     * Setter for amount
     * @param amount BigDecimal to set amount
     */
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    /**
     * Setter for idCategory
     * @param idCategory integer to set idCategory
     */
    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    /**
     * Setter for idMenuItem
     * @param idMenuItem integer to set idMenuItem
     */
    public void setIdMenuItem(Integer idMenuItem) {
        this.idMenuItem = idMenuItem;
    }

    /**
     * Setter for idUnit
     * @param idUnit integer to set idUnit
     */
    public void setIdUnit(Integer idUnit) {
        this.idUnit = idUnit;
    }

    /**
     * Setter for imageAddress
     * @param imageAddress string to set imageAddress
     */
    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    /**
     * Setter for price
     * @param price integer to det price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }

    /**
     * Setter for categoryDescription
     * @param categoryDescription string to set categoryDescription
     */
    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    /**
     * Getter unitDescription
     * @return unitsDescription
     */
    public String getUnitDescription() {
        return unitDescription;
    }

    /**
     * Setter for unitDescription
     * @param unitDescription string to set unitDescription
     */
    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }

    /**
     * Getter allergens
     * @return list of allergens
     */
    public List<String> getAllergens() {
        return allergens;
    }

    /**
     * Setter for allergens
     * @param allergens list of strings to set allergens
     */
    public void setAllergens(List<String> allergens) {
        this.allergens = allergens;
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
}
