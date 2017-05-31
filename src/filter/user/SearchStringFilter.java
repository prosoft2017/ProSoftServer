/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter.user;

import domain.AppUser;

/**
 *
 * @author Filip
 */
public class SearchStringFilter implements BasicFilter {

    private final BasicFilter nextFilter;

    public SearchStringFilter() {
        nextFilter = new ActiveDateFilter();
    }

    @Override
    public boolean filter(AppUser appUser, UserFilter filter) {
        String hint = filter.getSearchString();

        if (hint != null && !hint.isEmpty()) {
            if (!appUser.getFirstname().toLowerCase().contains(hint.toLowerCase())
                    && !appUser.getLastname().toLowerCase().contains(hint.toLowerCase())
                    && !appUser.getUsername().toLowerCase().contains(hint.toLowerCase())) {
                return false;
            }
        }

        return nextFilter.filter(appUser, filter);
    }

}
