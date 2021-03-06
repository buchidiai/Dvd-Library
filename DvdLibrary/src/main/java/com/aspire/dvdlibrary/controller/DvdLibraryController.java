/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.controller;

import com.aspire.dvdlibrary.dao.DvdLibraryDao;
import com.aspire.dvdlibrary.dao.DvdLibraryDaoException;
import com.aspire.dvdlibrary.dto.Dvd;
import com.aspire.dvdlibrary.ui.DvdLibraryView;
import com.aspire.dvdlibrary.util.Util;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author louie
 */
@Component
public class DvdLibraryController {

    private DvdLibraryView view;
    private DvdLibraryDao dao;

    @Autowired
    public DvdLibraryController(DvdLibraryView view, DvdLibraryDao dao) {
        this.view = view;
        this.dao = dao;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            while (keepGoing) {

                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        addDvd();
                        break;
                    case 2:
                        findDvdByTitle();
                        break;

                    case 3:
                        findDvdByYear();
                        break;

                    case 4:
                        editDVD();
                        break;
                    case 5:
                        removeDvd();
                        break;
                    case 6:
                        listAllDvds();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }

            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {

            view.displayErrorMessage(e.getMessage());
        }

    }

    private int getMenuSelection() {

        return view.printMenuAndGetSelection();
    }

    private void addDvd() throws DvdLibraryDaoException {
        //display add address banner message
        view.displayAddDvdBanner();

        //get new address data
        Dvd newDvd = view.getnewDvdInfo();

        //make key ~> titleId with title and id
        String titleId = (newDvd.getTitle() + "-" + newDvd.getId());

        //add dvd to hashMap key ~> (titleId) value ~> Dvd object
        dao.addDvd(newDvd, titleId);

        //display Success
        view.displayCreateSuccessBanner();
    }

    private void findDvdByTitle() throws DvdLibraryDaoException {
        //display edit address banner message
        view.displayFindDvdBanner();

        //get dvd title
        String title = Util.capitalizeEachWord(view.getDvdTitle());

        //find Dvd or dvds by title
        Map<String, Dvd> dvds = dao.findDvd(title);

        //dispay dvd if found
        view.displayFoundDvd(dvds, title);
    }

    private void findDvdByYear() throws DvdLibraryDaoException {
        //display edit address banner message
        view.displayFindDvdByYearBanner();

        //get dvd title
        int year = (view.getDvdYear());

        //find Dvd or dvds by title
        List<Dvd> dvds = dao.findDvdByYear(year);

        //dispay dvd if found
        view.displayFoundDvdByYear(dvds, year);
    }

    private void removeDvd() throws DvdLibraryDaoException {
        //display remove dvd banner message
        view.displayRemoveDvdBanner();

        //get dvd title
        String title = Util.capitalizeEachWord(view.getDvdTitle());

        //find dvd by title
        Map<String, Dvd> dvds = dao.findDvd(title);

        //ask user to select dvd to delete if matching
        String dvdToDelete = view.getUserDvdChoice(dvds, title);

        Dvd deletedDvd = dao.removeDvd(dvdToDelete);

        if (deletedDvd != null) {
            view.displayRemoveSuccessBanner();
        }

    }

    private void editDVD() throws DvdLibraryDaoException {
        //display find address banner message
        view.displayEditDvdBanner();

        //get dvd title
        String title = Util.capitalizeEachWord(view.getDvdTitle());

        //find dvd by title
        Map<String, Dvd> dvds = dao.findDvd(title);

        if (!(dvds.isEmpty())) {

            //ask user to select dvd to edit if matching
            String dvdToUpdate = view.getUserDvdChoice(dvds, title);

            //display enter new info
            view.displayEditDvdEnterNewInfo();

            //get new dvd data
            Dvd newDvd = view.getnewDvdInfo();

            //make key ~> titleId with title and id
            String titleId = (newDvd.getTitle() + "-" + newDvd.getId());

            //add dvd to hashMap key ~> (titleId) value ~> Dvd object
            dao.updateDvd(newDvd, titleId);

            Dvd deletedDvd = dao.removeDvd(dvdToUpdate);

            view.displayEditSuccessMessage();
        } else {
            //dispay dvd not found
            view.displayFoundDvd(dvds, title);

        }

    }

    private void listAllDvds() throws DvdLibraryDaoException {
        //display list count address banner message
        view.displayListAllDvdBanner();

        //get num of addresses
        Collection<Dvd> allDvds = dao.getAllDvd();
        //dispay count
        view.displayAllDvds(allDvds);
    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitBanner();
    }

}
