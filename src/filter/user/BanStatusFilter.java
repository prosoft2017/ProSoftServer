/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter.user;

import domain.user.AppUser;

/**
 *
 * @author Filip
 */
public class BanStatusFilter implements BasicFilter{

    private final BasicFilter nextFilter;

    public BanStatusFilter() {
        nextFilter = new CountryFilter();
    }

    @Override
    public boolean filter(AppUser appUser, UserFilter filter) {
        boolean banStatus = filter.isBanned();

        if (banStatus) {
           return appUser.isBaned() && nextFilter.filter(appUser, filter);
        }

        return nextFilter.filter(appUser, filter);
    }
}
