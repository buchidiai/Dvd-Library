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

    private String id;

    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userRating;

    public Dvd() {
        this.id = Util.UUID();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {

        return this.id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {

        this.title = Util.capitalizeEachWord(title);
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {

        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {

        if (mpaaRating.isEmpty() || mpaaRating.equals("null")) {

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

        if (directorName.isEmpty() || directorName.equals("null")) {

            directorName = null;
        } else {
            this.directorName = Util.capitalizeEachWord(directorName);
        }

    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {

        if (studio.isEmpty() || studio.equals("null")) {

            studio = null;
        } else {
            this.studio = Util.capitalizeEachWord(studio);
        }

    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {

        if (userRating.isEmpty() || userRating.equals("null")) {

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
