/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.ui;

import com.aspire.dvdlibrary.dao.DvdLibraryDaoException;
import com.aspire.dvdlibrary.dto.Dvd;
import java.util.Collection;
import java.util.Map;

/**
 *
 * @author louie
 */
public class DvdLibraryView {

    private UserIO io;

    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add Dvd");
        io.print("2. Find Dvd by Title");
        io.print("3. Edit Dvd");
        io.print("4. Delete Dvd");
        io.print("5. List All Dvds");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Dvd getnewDvdInfo() throws DvdLibraryDaoException {
        //set instance of dvd
        Dvd newDvd = new Dvd();
        try {
            //get address info
            String title = io.readString("Please Enter Dvd title:");
            String releaseDate = io.readString("Please Enter Dvd relase date:");
            String MpaaRating = io.readString("Please Enter it's Mpaa Rating: (G, PG, PG-13, R, or NC-17)");
            String DirectorName = io.readString("Please Enter Director's name:");
            String Studio = io.readString("Please Enter Studio's name:");
            String userRating = io.readString("Please Enter User rating:");

            //set values for address
            newDvd.setTitle(title);
            newDvd.setReleaseDate(releaseDate);
            newDvd.setMpaaRating(MpaaRating);
            newDvd.setDirectorName(DirectorName);
            newDvd.setStudio(Studio);
            newDvd.setUserRating(userRating);

            return newDvd;

        } catch (DvdLibraryDaoException e) {

        }

        return newDvd;

    }

    public String getUserDvdChoice(Map<String, Dvd> dvds, String dvdTitle) {

        int collectionSize = dvds.size();
        String keyForDvd = "";

        displayMatchingDvds(dvdTitle);

        if (!(dvds.isEmpty())) {

            keyForDvd = askUserForDvdChoice(dvds, collectionSize);

        } else {
            io.print("No such Dvd found.");

            io.readString("Press Enter to go to Main Menu.");
        }

        return keyForDvd;

    }

    public String askUserForDvdChoice(Map<String, Dvd> dvds, int collectionSize) {

        int index = 0;
        int indexChoice = 0;
        String[] keys = new String[collectionSize];

        String amountMovies = "";

        io.print("");

        for (Map.Entry<String, Dvd> e : dvds.entrySet()) {
            displayDvdIndexIncremented(index);
            displayCurrentDvd(e.getValue());
            keys[index] = e.getKey();

            io.print("");

            if ((index + 1) == (collectionSize)) {

                amountMovies = index == 1 ? " movie" : " movies";

                io.print(collectionSize + amountMovies + " found.");

                indexChoice = getIndexOfDvdChoice(1, collectionSize);

                return keys[collectionSize == 1 ? indexChoice - 1 : indexChoice];
            }

            index++;
        }

        return keys[indexChoice];
    }

    public void displayFoundDvd(Map<String, Dvd> dvds, String dvdTitle) {

        int collectionSize = dvds.size();

        displayMatchingDvds(dvdTitle);
        if (!(dvds.isEmpty())) {

            viewDvdInfo(dvds, collectionSize);
        } else {
            io.print("No such Dvd found.");

            io.readString("Press Enter to go to Main Menu.");
        }

    }

    public void viewDvdInfo(Map<String, Dvd> dvds, int collectionSize) {
        int index = 1;

        String amountMovies = "";

        io.print("");

        for (Map.Entry<String, Dvd> e : dvds.entrySet()) {
            displayDvdIndex(index);
            displayCurrentDvd(e.getValue());
            io.print("");
            index++;
        }
        amountMovies = index == 1 ? " movie" : " movies";

        io.print(collectionSize + amountMovies + " found.");

        io.readString("Press Enter to go to Main Menu.");

        index = 1;
    }

    public int getIndexOfDvdChoice(int startIndex, int endIndex) {
        return io.readInt("Please enter a Dvd number from above list.", startIndex, endIndex);
    }

    public void displayEditDvdEnterNewInfo() {
        io.print("Enter Info to be Updated");
    }

    public void displayAllDvds(Collection<Dvd> dvds) {
        io.print("");
        int count = 0;
        for (Dvd i : dvds) {
            io.print(i.toString());
            io.print("");
            count++;
        }

        displayCountOfDvds(count);
        io.print("");

    }

    public void displayCurrentDvd(Dvd dvd) {

        System.out.println(dvd);
    }

    public void displayDvdIndex(int index) {
        io.print("=== Dvd number " + (index) + " ===");
    }

    public void displayDvdIndexIncremented(int index) {
        io.print("=== Dvd number (" + (index + 1) + ") ===");
    }

    public String getDvdTitle() {
        return io.readString("Please enter Dvd title.");
    }

    public void displayMatchingDvds(String dvdTitle) {
        io.print("=== Search Results for " + dvdTitle + " ===");
    }

    public void displayAddDvdBanner() {
        io.print("=== Add a Dvd ===");
    }

    public void displayEditDvdBanner() {
        io.print("=== Edit a Dvd ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("Dvd successfully added.  Press Enter to go to Main Menu.");
    }

    public void displayRemoveSuccessBanner() {
        io.readString("Dvd successfully removed.  Press Enter to go to Main Menu.");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove a Dvd ===");
    }

    public void displayFindDvdBanner() {
        io.print("=== Find a Dvd by title ===");
    }

    public void displayEditSuccessMessage() {
        io.print("=== Edit Was Successful ===");

        io.readString("Press Enter to go to Main Menu.");
    }

    public void displayListAllDvdBanner() {
        io.print("=== List All Dvds ===");
    }

    public void displayCountOfDvds(int count) {

        io.print("There are " + count + " dvds in this Collection.");

        io.readString("Press Enter to go to Main Menu.");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }

    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

}
