package com.adonis.labexer4;

public class ItemData {
    private int logo;
    private String name, country, industry, ceo, description;

    public ItemData(int logo, String name, String country, String industry, String ceo) {
        this.logo = logo;
        this.name = name;
        this.country = country;
        this.industry = industry;
        this.ceo = ceo;
    }

    public int getLogo() {
        return logo;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getIndustry() {
        return industry;
    }

    public String getCeo() {
        return ceo;
    }
}
