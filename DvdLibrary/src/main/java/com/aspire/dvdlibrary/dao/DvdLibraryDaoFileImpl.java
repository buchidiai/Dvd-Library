/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.dao;

import com.aspire.dvdlibrary.dto.Dvd;
import java.util.NavigableMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author louie
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private TreeMap<String, Dvd> dvdCollection = new TreeMap<String, Dvd>();

    @Override
    public Dvd addDvd(Dvd dvd, String titleId) throws DvdLibraryDaoException {
        // add dvd (id is generated on object instantiation) (key) and dvd object (value) to hashMap
        Dvd dvdToAdd = dvdCollection.put(titleId, dvd);
        //return hashMap
        return dvdToAdd;
    }

    @Override
    public SortedMap<String, Dvd> findDvd(String title) throws DvdLibraryDaoException {

        return getByPrefix(dvdCollection, title);

    }

    @Override
    public Dvd removeDvd() throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dvd updateDvd() throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Dvd getAllDvd() throws DvdLibraryDaoException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private static SortedMap<String, Dvd> getByPrefix(NavigableMap<String, Dvd> dvdCollection, String titleToFind) {
        return dvdCollection.subMap(titleToFind, titleToFind + Character.MAX_VALUE);
    }

}
