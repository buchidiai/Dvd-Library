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
        // add dvd (titleId is generated on object instantiation : name + id) (key) and dvd object (value) to hashMap
        Dvd dvdToAdd = dvdCollection.put(titleId, dvd);
        //return hashMap
        return dvdToAdd;
    }

    @Override
    public Map<String, Dvd> findDvd(String title) throws DvdLibraryDaoException {
        Map<String, Dvd> foundDvd = new HashMap<>();
        //loop through collection and check if it ends with or starts with value
        for (Map.Entry<String, Dvd> e : dvdCollection.entrySet()) {

            if (e.getKey().startsWith(title) || e.getKey().endsWith(title)) {
                foundDvd.put(e.getKey(), e.getValue());
            }
        }
        //return map of values
        return foundDvd;
    }

    @Override
    public Dvd removeDvd(String dvdKey) throws DvdLibraryDaoException {
        //remove by key of value
        return dvdCollection.remove(dvdKey);
    }

    @Override
    public Dvd updateDvd(Dvd dvd, String titleId) throws DvdLibraryDaoException {
        // add dvd (titleId is generated on object instantiation : name + id) (key) and dvd object (value) to hashMap
        Dvd dvdToUpdate = dvdCollection.put(titleId, dvd);
        //return hashMap
        return dvdToUpdate;
    }

    @Override
    public Collection<Dvd> getAllDvd() throws DvdLibraryDaoException {
        //return all values
        return dvdCollection.values();
    }

}
