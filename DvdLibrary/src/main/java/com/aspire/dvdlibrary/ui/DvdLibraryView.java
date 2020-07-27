/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.ui;

import com.aspire.dvdlibrary.dto.Dvd;
import com.aspire.dvdlibrary.ui.DvdLibraryView.MpaaValues;
import java.time.LocalDate;
import java.util.Calendar;
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
public class DvdLibraryView {

    private UserIO io;

    private final int currentYear = Calendar.getInstance().get(Calendar.YEAR);

    enum MpaaValues {
        G, PG, PG13, R, NC17, M, X;

        public static boolean contains(String s) {
            for (MpaaValues choice : values()) {

                if (choice.name().equals(s)) {
                    return true;
                }
            }
            return false;
        }

    }

    @Autowired
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add Dvd");
        io.print("2. Find Dvd by Title");
        io.print("3. Find Dvd by Year");
        io.print("4. Edit Dvd");
        io.print("5. Remove Dvd");
        io.print("6. List All Dvds");
        io.print("7. Exit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }

    public int getDvdYear() {

        return io.readInt("Please Enter Dvd relase date: ", 1888, currentYear);
    }

    public String getFormatedDvdYear() {

        String formattedRelease = "";

        String[] results = new String[3];

        boolean isValidDates = true;
        int month = 0;
        int day = 0;
        int year = 0;

        String realseYear = io.readString("Please Enter Dvd relase date: (01-01-2002) (m-d-y)");

        if (realseYear.contains("-")) {
            results = realseYear.split("-");
        } else if (realseYear.contains("/")) {
            results = realseYear.split("/");
        }

        if (results.length == 3) {

            while (isValidDates) {

                try {
                    month = Integer.parseInt(results[0]);
                    day = Integer.parseInt(results[1]);
                    year = Integer.parseInt(results[2]);
                    isValidDates = false;

                } catch (NumberFormatException e) {

                    System.out.println("Must be a valid date");

                } catch (NullPointerException e) {
                    System.out.println("Must be a valid date");

                }
                isValidDates = false;
            }

            LocalDate ld = LocalDate.of(year, month, day);
            formattedRelease = ld.getDayOfWeek() + ", " + ld.getMonth() + " " + ld.getDayOfMonth() + ", " + ld.getYear();
        }
        return formattedRelease;
    }

    public Dvd getnewDvdInfo() {

        boolean validRating = false;
        boolean validTitle = false;

        String MpaaRating = "";
        String title = "";

        while (!validTitle) {
            //get address info
            title = io.readString("Please Enter Dvd title:");
            //check if empty
            if (title.isEmpty()) {
                io.print("Title can not be empty");
                continue;
            }
            validTitle = true;
        }

        //validate date
//        int releaseDate = io.readInt("Please Enter Dvd relase date: ", 1888, currentYear);
        String releaseDate = getFormatedDvdYear();

        //make sure user puts a valid rating
        while (!validRating) {
            //get rating make sure it part of enum
            MpaaRating = io.readString("Please Enter it's Mpaa Rating: (G, PG, PG13, R, M, X or NC17)");

            if (!(MpaaValues.contains(MpaaRating.toUpperCase()))) {
                io.print("Must be a valid rating");
                continue;

            }

            validRating = true;

        }

        String DirectorName = io.readString("Please Enter Director's name:");
        String Studio = io.readString("Please Enter Studio's name:");
        String userRating = io.readString("Please Enter User rating:");

        //set instance of dvd
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
        String choice = "";
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

                choice = keys[indexChoice - 1];

                return choice;
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

    public void displayFoundDvdByYear(List<Dvd> dvds, int year) {

        int listSize = dvds.size();

        displayMatchingDvdsByYear(year);
        if (!(dvds.isEmpty())) {

            viewDvdInfoByYear(dvds, listSize);
        } else {
            io.print("No such Dvd found.");

            io.readString("Press Enter to go to Main Menu.");
        }

    }

    public void viewDvdInfoByYear(List<Dvd> dvds, int collectionSize) {
        int index = 1;

        String amountMovies = "";

        io.print("");

        for (Dvd d : dvds) {
            displayDvdIndex(index);
            displayCurrentDvd(d);
            io.print("");
            index++;
        }
        amountMovies = index == 1 ? " movie" : " movies";

        io.print(collectionSize + amountMovies + " found.");

        io.readString("Press Enter to go to Main Menu.");

        index = 1;
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

        boolean isValid = true;
        String title = "";

        while (isValid) {

            title = io.readString("Please enter Dvd title.");

            if (title.isEmpty()) {
                continue;
            }

            isValid = false;

        }

        return title;
    }

    public void displayMatchingDvds(String dvdTitle) {
        io.print("=== Search Results for " + dvdTitle + " ===");
    }

    public void displayMatchingDvdsByYear(int dvdYear) {
        io.print("=== Search Results for Dvd's in " + dvdYear + " ===");
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

    public void displayFindDvdByYearBanner() {
        io.print("=== Find a Dvd by Year ===");
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
