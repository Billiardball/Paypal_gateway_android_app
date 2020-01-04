package com.shop.arram.model;

public class Languages {

    String Code,Active,Tag,CountryFlagUrl,SiteLanguage,Displanguage;
    boolean IsRtl;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String active) {
        Active = active;
    }

    public String getTag() {
        return Tag;
    }

    public void setTag(String tag) {
        Tag = tag;
    }

    public String getCountryFlagUrl() {
        return CountryFlagUrl;
    }

    public void setCountryFlagUrl(String countryFlagUrl) {
        CountryFlagUrl = countryFlagUrl;
    }

    public String getSiteLanguage() {
        return SiteLanguage;
    }

    public void setSiteLanguage(String siteLanguage) {
        SiteLanguage = siteLanguage;
    }

    public String getDisplanguage() {
        return Displanguage;
    }

    public void setDisplanguage(String displanguage) {
        Displanguage = displanguage;
    }

    public boolean isRtl() {
        return IsRtl;
    }

    public void setRtl(boolean rtl) {
        IsRtl = rtl;
    }
}
