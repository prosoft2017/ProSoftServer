/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.user;

import db.DatabaseBroker;
import domain.user.Country;
import java.util.List;
import so.GeneralSO;

/**
 *
 * @author Filip
 */
public class SOAllCountries extends GeneralSO {

    private List<Country> allCountries;

    @Override
    protected void executeSpecificOperation(Object obj) throws Exception {
        allCountries = DatabaseBroker.getInstance().getAllAvailableCountries();
    }

    public List<Country> getAllCountries() {
        return allCountries;
    }

}
