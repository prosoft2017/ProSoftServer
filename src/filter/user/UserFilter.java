/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter.user;

import domain.user.AppUser;
import domain.user.Country;
import domain.user.StatusType;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Filip
 */
public class UserFilter {

    private String searchString;
    private LocalDate dataActiveFrom;
    private LocalDate dataActiveTo;
    private List<StatusType> statusTypes;
    private boolean banned;
    private List<Country> countryList;
    private List<String> cityList;
    private final BasicFilter filter;

    public UserFilter() {
        statusTypes = new LinkedList<>();
        countryList = new LinkedList<>();
        cityList = new LinkedList<>();
        filter = new SearchStringFilter();
    }

    public boolean doFiltration(AppUser appUser) {
        return filter.filter(appUser, this);
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public LocalDate getDataActiveFrom() {
        return dataActiveFrom;
    }

    public void setDataActiveFrom(LocalDate dataActiveFrom) {
        this.dataActiveFrom = dataActiveFrom;
    }

    public LocalDate getDataActiveTo() {
        return dataActiveTo;
    }

    public void setDataActiveTo(LocalDate dataActiveTo) {
        this.dataActiveTo = dataActiveTo;
    }

    public List<StatusType> getStatusTypes() {
        return statusTypes;
    }

    public void setStatusTypes(List<StatusType> statusTypes) {
        this.statusTypes = statusTypes;
    }

    public void addStatusType(StatusType statusType) {
        this.statusTypes.add(statusType);
    }

    public void removeStatusType(StatusType statusType) {
        this.statusTypes.remove(statusType);
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }

    public void addCountyFilter(List<Country> countryList) {
        this.countryList.clear();
        this.countryList.addAll(countryList);
    }

    public void removeCountryFilter(List<Country> countryList) {
        this.countryList.removeAll(countryList);
    }

    public List<String> getCityList() {
        return cityList;
    }

    public void setCityList(List<String> cityList) {
        this.cityList = cityList;
    }

    public void resetAllFilters() {
        searchString = "";
        dataActiveFrom = null;
        dataActiveTo = null;
        statusTypes.clear();
        banned = false;
        countryList.clear();
        cityList.clear();
    }

}
