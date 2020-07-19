/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.dto;

import com.aspire.dvdlibrary.util.Util;

/**
 *
 * @author louie
 */
public class Dvd {

    private int id;
    private static int idCounter = 1;

    private String title;
    private int releaseDate;
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

        this.title = Util.capitalizeEachWord(title);
    }

    public int getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(int releaseDate) {

        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {

        if (mpaaRating.isEmpty()) {

            mpaaRating = null;
        } else {

            if (mpaaRating.length() == 4) {
                mpaaRating = mpaaRating.substring(0, 2) + "-" + mpaaRating.substring(2);
            }

            this.mpaaRating = mpaaRating.toUpperCase();
        }

    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {

        if (directorName.isEmpty()) {

            directorName = null;
        } else {
            this.directorName = Util.capitalizeEachWord(directorName);
        }

    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {

        if (studio.isEmpty()) {

            studio = null;
        } else {
            this.studio = Util.capitalizeEachWord(studio);
        }

    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {

        if (userRating.isEmpty()) {

            this.userRating = null;
        } else {
            this.userRating = Util.capitalizeEachWord(userRating);
        }

    }

    @Override
    public String toString() {

        return ("Title: " + title + '\n' + "Release Date: " + releaseDate + '\n' + "Mpaa-Rating: " + mpaaRating + '\n' + "Director's Name: " + directorName + '\n' + "Studio: " + studio + '\n' + "User-Rating: " + userRating);
    }

}
