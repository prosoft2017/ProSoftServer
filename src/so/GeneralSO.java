/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so;

import db.DatabaseBroker;

/**
 *
 * @author student1
 */
public abstract class GeneralSO {

    public final void executeSO(Object obj) throws Exception {
        try {
            loadDriver();
            openConnection();
            executeSpecificOperation(obj);
            commitTransaction();
        } catch (Exception ex) {
            rollbackTransaction();
            throw ex;
        } finally {
            closeConection();
        }

    }

    private void loadDriver() throws Exception {
        DatabaseBroker.getInstance().loadDriver();
    }

    private void openConnection() throws Exception {
        DatabaseBroker.getInstance().openConnection();
    }

    // Specificno za svaku sistemsku operaciju
    protected abstract void executeSpecificOperation(Object obj) throws Exception;

    private void commitTransaction() throws Exception {
        DatabaseBroker.getInstance().commitTransaction();
    }

    private void rollbackTransaction() throws Exception {
        DatabaseBroker.getInstance().rollbackTransaction();
    }

    private void closeConection() throws Exception {
        DatabaseBroker.getInstance().closeConection();
    }

}
