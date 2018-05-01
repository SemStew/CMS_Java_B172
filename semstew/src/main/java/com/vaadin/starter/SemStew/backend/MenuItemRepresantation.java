package com.vaadin.starter.SemStew.backend;

import JOOQ.tables.records.*;

import java.math.BigDecimal;

public class MenuItemRepresantation {

    // MenuItemNameRecord
    private String name;
    private String description;
    private Integer idLanguage;

    // MenuItemRecord
    private BigDecimal amount;
    private Integer idCategory;
    private Integer idMenuItem;
    private Integer idUnit;
    private String imageAddress;
    private Integer price;

    // CategoryName
    private String categoryDescription;

    // Units
    private String unitDescription;


    public MenuItemRepresantation()
    {

    }

    public void Load(MenuItemNameRecord inName, MenuItemRecord inItem, CategoriesNameRecord inCategoryName,
                     UnitsRecord unitsIn)
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

        categoryDescription = inCategoryName.getDescription();

        unitDescription = unitsIn.getDescription();
    }

    public MenuItemRecord GetItemRecord()
    {
        MenuItemRecord item = new MenuItemRecord();

        item.setAmount(amount);
        item.setIdCategory(idCategory);
        item.setIdMenuItem(idMenuItem);
        item.setIdUnit(idUnit);
        item.setImageName(imageAddress);
        item.setPrice(price);

        return item;
    }

    public MenuItemNameRecord GetItemNameRecord()
    {
        MenuItemNameRecord itemName = new MenuItemNameRecord();

        itemName.setIdLanguage(idLanguage);
        itemName.setIdMenuItem(idMenuItem);
        itemName.setDescription(description);
        itemName.setName(name);

        return itemName;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getIdLanguage() {
        return idLanguage;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getIdCategory() {
        return idCategory;
    }

    public Integer getIdMenuItem() {
        return idMenuItem;
    }

    public Integer getIdUnit() {
        return idUnit;
    }

    public String getImageAddress() {
        return imageAddress;
    }

    public Integer getPrice() {
        return price;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdLanguage(Integer idLanguage) {
        this.idLanguage = idLanguage;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setIdCategory(Integer idCategory) {
        this.idCategory = idCategory;
    }

    public void setIdMenuItem(Integer idMenuItem) {
        this.idMenuItem = idMenuItem;
    }

    public void setIdUnit(Integer idUnit) {
        this.idUnit = idUnit;
    }

    public void setImageAddress(String imageAddress) {
        this.imageAddress = imageAddress;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public String getUnitDescription() {
        return unitDescription;
    }

    public void setUnitDescription(String unitDescription) {
        this.unitDescription = unitDescription;
    }
}
