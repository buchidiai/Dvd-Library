/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.dao;

import com.aspire.dvdlibrary.dto.Dvd;

/**
 *
 * @author louie
 */
public interface DvdLibraryDao {

    Dvd addDvd() throws DvdLibraryDaoException;

    Dvd removeDvd() throws DvdLibraryDaoException;

    Dvd updateDvd() throws DvdLibraryDaoException;

    Dvd getAllDvd() throws DvdLibraryDaoException;

    Dvd findDvd() throws DvdLibraryDaoException;

}
