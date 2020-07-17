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
import java.util.SortedMap;

/**
 *
 * @author louie
 */
public class DvdLibraryController {

    private DvdLibraryView view;
    private DvdLibraryDao dao;

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
                        editDVD();
                        break;
                    case 4:
                        removeDvd();
                        break;
                    case 5:
                        listAllDvds();
                        break;
                    case 6:
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
        String title = view.getDvdTitle();

        //find Dvd or dvds by title
        SortedMap<String, Dvd> dvds = dao.findDvd(title);

        //dispay address if found
        view.displayFoundDvd(dvds, title);
    }

    private void removeDvd() throws DvdLibraryDaoException {
//       //display remove dvd banner message
//        view.displayRemoveDvdBanner();
//
//        //get dvd title
//        String title = view.getDvdTitle();
//
//        //find dvd by title
//        Dvd address = dao.findDvd(title);
//
//        //ask to confirm delete
//        boolean toDelete = view.askToDelete(address);
//        //if yes
//        if (toDelete) {
//            //remove address from hashMap by key ~> (lastname)
//            Address removedAddress = dao.removeAddress(lastname);
//
//            // check if address was sucessfully deleted then display message
//            view.displayRemoveResult(removedAddress);
//        } else {
//            address = null;
//            view.displayRemoveResult(address);
//
//        }
    }

    private void editDVD() throws DvdLibraryDaoException {
//        //display find address banner message
//        view.displayFindAddressBanner();
//        //get last name
//        String lastname = view.getAddressLastNameChoice();
//        //find address by last name
//        Address address = dao.getAddressByLastName(lastname);
//        //dispay address if found
//        view.displayFoundAddress(address);
    }

    private void listAllDvds() throws DvdLibraryDaoException {
//        //display list count address banner message
//        view.displayCountOfAddressBanner();
//        //get num of addresses
//        int numOfAddresses = dao.getCountOfAddresses();
//        //dispay count
//        view.displayCountOfAddresses(numOfAddresses);
    }

    private void unknownCommand() {
//        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
//        view.displayExitBanner();
    }

}
