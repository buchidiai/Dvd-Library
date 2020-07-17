/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.dto;

import com.aspire.dvdlibrary.dao.DvdLibraryDaoException;

/**
 *
 * @author louie
 */
public class Dvd {

    private int id;
    private static int idCounter = 0;

    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userRating;

    public Dvd() {
        this.id = idCounter++;
    }

    public int getId() {
        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) throws DvdLibraryDaoException {
//        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
//        int releaseYear = Integer.parseInt(releaseDate);
//
//        System.out.println("releaseYear" + releaseYear);
//        System.out.println("currentYear" + currentYear);
//
//        if (!(releaseYear >= 0 && releaseYear <= currentYear)) {
//
//            throw new DvdLibraryDaoException("Must be a valid year");
//        }

        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

    @Override
    public String toString() {

        return ("Title: " + title + '\n' + "Release Date: " + releaseDate + '\n' + "Mpaa-Rating: " + mpaaRating + '\n' + "Director's Name: " + directorName + '\n' + "Studio: " + studio + '\n' + "User-Rating: " + userRating);
    }

}
