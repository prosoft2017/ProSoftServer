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
public interface BasicFilter {

    boolean filter(AppUser appUser, UserFilter filter);
}
