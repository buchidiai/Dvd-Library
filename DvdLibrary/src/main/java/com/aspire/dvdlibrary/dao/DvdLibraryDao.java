/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.dao;

import com.aspire.dvdlibrary.dto.Dvd;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author louie
 */
public interface DvdLibraryDao {

    Dvd addDvd(Dvd dvd, String titleId) throws DvdLibraryDaoException;

    Map<String, Dvd> findDvd(String title) throws DvdLibraryDaoException;

    Dvd removeDvd(String title) throws DvdLibraryDaoException;

    Dvd updateDvd(Dvd dvd, String titleId) throws DvdLibraryDaoException;

    Collection<Dvd> getAllDvd() throws DvdLibraryDaoException;

}
