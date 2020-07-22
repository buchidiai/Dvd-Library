/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.dao;

import com.aspire.dvdlibrary.dto.Dvd;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author louie
 */
public class DvdLibraryDaoFileImplTest {

    DvdLibraryDao testDao;

    public DvdLibraryDaoFileImplTest() {

    }

    @BeforeEach
    public void setUp() throws IOException {

        String testFile = "testdvd.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new DvdLibraryDaoFileImpl(testFile);
    }

    @Test
    public void testAddGetDvd() throws Exception {
        // Create our method test inputs

        String title = "Lion King";
        String MpaaRating = "G";
        int releaseDate = 1987;
        String DirectorName = "Peter Johnson";
        String Studio = "Pixar";
        String userRating = "Awesome movie i cried";
        String dvdId = "random_generated_id";
        String titleId = (title + "-" + dvdId);

        Dvd newDvd = new Dvd();
        //set values for address
        newDvd.setId(dvdId);
        newDvd.setTitle(title);
        newDvd.setReleaseDate(releaseDate);
        newDvd.setMpaaRating(MpaaRating);
        newDvd.setDirectorName(DirectorName);
        newDvd.setStudio(Studio);
        newDvd.setUserRating(userRating);

        //  Add the dvd to the DAO
        testDao.addDvd(newDvd, titleId);
        // Get the dvd from the DAO
        Map<String, Dvd> retrievedDvd = testDao.findDvd(titleId);

        // Check the data is equal
        assertEquals(newDvd.getId(),
                retrievedDvd.get(titleId).getId(),
                "Checking title id.");

        assertEquals(newDvd.getTitle(),
                retrievedDvd.get(titleId).getTitle(),
                "Checking dvd title.");

        assertEquals(newDvd.getMpaaRating(),
                retrievedDvd.get(titleId).getMpaaRating(),
                "Checking dvd Mpaa.");

        assertEquals(newDvd.getReleaseDate(),
                retrievedDvd.get(titleId).getReleaseDate(),
                "Checking dvd releaseDate.");

        assertEquals(newDvd.getDirectorName(),
                retrievedDvd.get(titleId).getDirectorName(),
                "Checking dvd DirectorName.");

        assertEquals(newDvd.getStudio(),
                retrievedDvd.get(titleId).getStudio(),
                "Checking dvd Studio.");
        assertEquals(newDvd.getUserRating(),
                retrievedDvd.get(titleId).getUserRating(),
                "Checking dvd userRating.");

    }

    @Test
    public void testAddGetAllDvds() throws Exception {
        // Create our first dvd
        String title = "Dance like a wolf";
        String MpaaRating = "PG";
        int releaseDate = 2000;
        String DirectorName = "Mike Smith";
        String Studio = "Evolve";
        String userRating = "Nice";
        String dvdId = "2kbjsdv87r398324";
        String titleId = (title + "-" + dvdId);

        Dvd newDvd = new Dvd();
        //set values for address
        newDvd.setId(dvdId);
        newDvd.setTitle(title);
        newDvd.setReleaseDate(releaseDate);
        newDvd.setMpaaRating(MpaaRating);
        newDvd.setDirectorName(DirectorName);
        newDvd.setStudio(Studio);
        newDvd.setUserRating(userRating);

        // Create our second dvd
        String title1 = "Life is good";
        String MpaaRating1 = "R";
        int releaseDate1 = 2020;
        String DirectorName1 = "Jaden Smith";
        String Studio1 = "kwilth";
        String userRating1 = "Nice like really";
        String dvdId1 = "7gv87dfv893h4r";
        String titleId1 = (title + "-" + dvdId);

        Dvd newDvd2 = new Dvd();
        //set values for address
        newDvd.setId(dvdId1);
        newDvd.setTitle(title1);
        newDvd.setReleaseDate(releaseDate1);
        newDvd.setMpaaRating(MpaaRating1);
        newDvd.setDirectorName(DirectorName1);
        newDvd.setStudio(Studio1);
        newDvd.setUserRating(userRating1);

        // Add both our dvds to the DAO
        testDao.addDvd(newDvd, newDvd.getId());
        testDao.addDvd(newDvd2, newDvd2.getId());

        // Retrieve the list of all dvds within the DAO
        Collection<Dvd> allDvds = testDao.getAllDvd();

        // First check the general contents of the list
        assertNotNull(allDvds, "The list of dvds must not null");
        assertEquals(2, allDvds.size(), "List of dvds should have 2 dvds.");

        // Then the specifics
        assertTrue(testDao.getAllDvd().contains(newDvd),
                "The list of dvds should include Ada.");
        assertTrue(testDao.getAllDvd().contains(newDvd2),
                "The list of dvds should include Charles.");

    }

    @Test
    public void testRemoveDvd() throws Exception {
        // Create two new dvds
        // Create our first dvd
        String title = "Dance like a wolf";
        String MpaaRating = "PG";
        int releaseDate = 2000;
        String DirectorName = "Mike Smith";
        String Studio = "Evolve";
        String userRating = "Nice";
        String dvdId = "2kbjsdv87r398324";
        String titleId = (title + "-" + dvdId);

        Dvd newDvd = new Dvd();
        //set values for address
        newDvd.setId(dvdId);
        newDvd.setTitle(title);
        newDvd.setReleaseDate(releaseDate);
        newDvd.setMpaaRating(MpaaRating);
        newDvd.setDirectorName(DirectorName);
        newDvd.setStudio(Studio);
        newDvd.setUserRating(userRating);

        // Create our second dvd
        String title1 = "Life is good";
        String MpaaRating1 = "R";
        int releaseDate1 = 2020;
        String DirectorName1 = "Jaden Smith";
        String Studio1 = "kwilth";
        String userRating1 = "Nice like really";
        String dvdId1 = "7gv87dfv893h4r";
        String titleId1 = (title + "-" + dvdId);

        Dvd newDvd2 = new Dvd();
        //set values for address
        newDvd.setId(dvdId1);
        newDvd.setTitle(title1);
        newDvd.setReleaseDate(releaseDate1);
        newDvd.setMpaaRating(MpaaRating1);
        newDvd.setDirectorName(DirectorName1);
        newDvd.setStudio(Studio1);
        newDvd.setUserRating(userRating1);

        // Add both our dvds to the DAO
        testDao.addDvd(newDvd, newDvd.getId());
        testDao.addDvd(newDvd2, newDvd2.getId());

        // remove the first dvd - Ada
        Dvd removedDvd = testDao.removeDvd(newDvd.getId());

        // Check that the correct object was removed.
        assertEquals(removedDvd, newDvd, "The removed dvd should be Ada.");

        // Get all the dvds
        Collection<Dvd> allDvds = testDao.getAllDvd();

        // First check the general contents of the list
        assertNotNull(allDvds, "All dvds list should be not null.");
        assertEquals(1, allDvds.size(), "All dvds should only have 1 dvd.");

        // Then the specifics
        assertFalse(allDvds.contains(newDvd), "All dvds should NOT include Ada.");
        assertTrue(allDvds.contains(newDvd2), "All dvds should NOT include Charles.");

        // Remove the second dvd
        removedDvd = testDao.removeDvd(newDvd2.getId());
        // Check that the correct object was removed.
        assertEquals(removedDvd, newDvd2, "The removed dvd should be Charles.");

        // retrieve all of the dvds again, and check the list.
        allDvds = testDao.getAllDvd();

        // Check the contents of the list - it should be empty
        assertTrue(allDvds.isEmpty(), "The retrieved list of dvds should be empty.");

        // Try to 'get' both dvds by their old id - they should be null!
        Map<String, Dvd> retrievedDvd = testDao.findDvd(newDvd.getTitle());
        System.out.println("retrievedDvd " + retrievedDvd);

        assertTrue(retrievedDvd.isEmpty(), "Ada was removed, should be null.");

        Map<String, Dvd> retrievedDvd2 = testDao.findDvd(newDvd2.getTitle());
        assertTrue(retrievedDvd2.isEmpty(), "Charles was removed, should be null.");

    }

}
