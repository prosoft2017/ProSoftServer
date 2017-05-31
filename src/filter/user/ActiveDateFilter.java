/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter.user;

import domain.AppUser;
import java.time.LocalDate;

/**
 *
 * @author Filip
 */
public class ActiveDateFilter implements BasicFilter {

    private final BasicFilter nextFilter;

    public ActiveDateFilter() {
        nextFilter = new UserStatusFilter();
    }

    @Override
    public boolean filter(AppUser appUser, UserFilter filter) {
        LocalDate dateFrom = filter.getDataActiveFrom();
        LocalDate dateTo = filter.getDataActiveTo();

        if (dateFrom != null || dateTo != null) {

            if (dateFrom != null && dateTo != null) {
                return appUser.getLastActive().isAfter(dateFrom)
                        && appUser.getLastActive().isBefore(dateTo)
                        && nextFilter.filter(appUser, filter);
            }

            return (dateFrom != null ? appUser.getLastActive().isAfter(dateFrom) : appUser.getLastActive().isBefore(dateTo))
                    && nextFilter.filter(appUser, filter);

        }

        return nextFilter.filter(appUser, filter);
    }

}
