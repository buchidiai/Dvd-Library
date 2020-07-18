/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.dao;

import com.aspire.dvdlibrary.dto.Dvd;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author louie
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private Map<String, Dvd> dvdCollection = new HashMap<String, Dvd>();

    @Override
    public Dvd addDvd(Dvd dvd, String titleId) throws DvdLibraryDaoException {
        // add dvd (id is generated on object instantiation) (key) and dvd object (value) to hashMap
        Dvd dvdToAdd = dvdCollection.put(titleId, dvd);
        //return hashMap
        return dvdToAdd;
    }

    @Override
    public Map<String, Dvd> findDvd(String title) throws DvdLibraryDaoException {
        Map<String, Dvd> foundDvd = new HashMap<>();
        for (Map.Entry<String, Dvd> e : dvdCollection.entrySet()) {

            if (e.getKey().startsWith(title)) {
                foundDvd.put(e.getKey(), e.getValue());
            }
        }

        return foundDvd;
    }

    @Override
    public Dvd removeDvd(String dvdKey) throws DvdLibraryDaoException {

        return dvdCollection.remove(dvdKey);
    }

    @Override
    public Dvd updateDvd() throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Dvd> getAllDvd() throws DvdLibraryDaoException {
        return dvdCollection.values();
    }

}
