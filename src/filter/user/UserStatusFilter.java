/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter.user;

import domain.AppUser;
import domain.StatusType;
import java.util.List;

/**
 *
 * @author Filip
 */
public class UserStatusFilter implements BasicFilter {

    private final BasicFilter nextFilter;

    public UserStatusFilter() {
        nextFilter = new BanStatusFilter();
    }

    @Override
    public boolean filter(AppUser appUser, UserFilter filter) {
        List<StatusType> statusTypes = filter.getStatusTypes();

        if (!statusTypes.isEmpty()) {
            if (!statusTypes.contains(appUser.getStatus())) {
                return false;
            }
        }

        return nextFilter.filter(appUser, filter);
    }

}
