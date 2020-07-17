/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.ui;

import com.aspire.dvdlibrary.dto.Dvd;

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
        io.print("2. Delete Dvd");
        io.print("3. Edit Dvd");
        io.print("4. List All Dvds");
        io.print("5. Find Dvd by Title");
        io.print("6. Exit");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public Dvd getnewDvdInfo() {
        //get address info
        String title = io.readString("Please Enter Dvd title:");
        String releaseDate = io.readString("Please Enter Dvd relase date:");
        String MpaaRating = io.readString("Please Enter it's Mpaa Rating: (G, PG, PG-13, R, or NC-17)");
        String DirectorName = io.readString("Please Enter Director's name:");
        String Studio = io.readString("Please Enter Studio's name:");
        String userRating = io.readString("Please Enter User rating:");

        //create new instance of dvd
        Dvd newDvd = new Dvd();

        //set values for address
        newDvd.setTitle(title);
        newDvd.setReleaseDate(releaseDate);
        newDvd.setMpaaRating(MpaaRating);
        newDvd.setDirectorName(DirectorName);
        newDvd.setStudio(Studio);
        newDvd.setUserRating(userRating);

        return newDvd;
    }

    public void displayAddDvdBanner() {
        io.print("=== Add a Dvd ===");
    }

    public void displayEditDvdBanner() {
        io.print("=== Edit a Dvd ===");
    }

    public void displayCreateSuccessBanner() {
        io.readString("Dvd successfully added.  Press 1 to go to Main Menu.");
    }

    public void displayRemoveDvdBanner() {
        io.print("=== Remove a Dvd ===");
    }

    public void displayFindDvdBanner() {
        io.print("=== Find a Dvd by title ===");
    }

    public void displayEditSuccessMessage() {
        io.print("=== Edit Was Successful ===");

        io.readString("Press 1 to go to Main Menu.");
    }

    public void displayListAllDvdBanner() {
        io.print("=== List All Dvds ===");
    }

    public void displayCountOfDvds(int count) {

        io.print("There are " + count + " dvds in this Collection.");

        io.readString("Press 1 to go to Main Menu.");
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
