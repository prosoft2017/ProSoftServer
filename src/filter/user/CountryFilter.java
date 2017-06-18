/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filter.user;

import domain.user.AppUser;
import domain.user.Country;
import java.util.List;

/**
 *
 * @author Filip
 */
public class CountryFilter implements BasicFilter {

    @Override
    public boolean filter(AppUser appUser, UserFilter filter) {
        List<Country> countrys = filter.getCountryList();

        if (!countrys.isEmpty()) {
            if (!countrys.contains(appUser.getAddress().getCountry())) {
                return false;
            }
        }

        return true;
    }

}
