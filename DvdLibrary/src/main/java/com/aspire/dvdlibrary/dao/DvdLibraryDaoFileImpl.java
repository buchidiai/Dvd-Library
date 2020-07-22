/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.dao;

import com.aspire.dvdlibrary.dto.Dvd;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author louie
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private Map<String, Dvd> dvdCollection = new HashMap<String, Dvd>();

    public final String ROSTER_FILE;
    public static final String DELIMITER = "::";

    public DvdLibraryDaoFileImpl() {
        ROSTER_FILE = "dvdcollection.txt";
    }

    public DvdLibraryDaoFileImpl(String testFile) {
        ROSTER_FILE = testFile;
    }

    @Override
    public Dvd addDvd(Dvd dvd, String key) throws DvdLibraryDaoException {
//        loadRoster();
        // add dvd (titleId is generated on object instantiation : name) (key) and dvd object (value) to hashMap
        Dvd dvdToAdd = dvdCollection.put(key, dvd);

//        writeRoster();
        //return hashMap
        return dvdToAdd;
    }

    @Override
    public Map<String, Dvd> findDvd(String title) throws DvdLibraryDaoException {
        Map<String, Dvd> foundDvd = new HashMap<>();

        //loop through collection and check if it ends with or starts with value
        for (Map.Entry<String, Dvd> e : dvdCollection.entrySet()) {
            if (e.getKey().matches("(.*)" + title + "(.*)")) {
                foundDvd.put(e.getKey(), e.getValue());
            }
        }

        //return map of values
        return foundDvd;

    }

    @Override
    public Dvd removeDvd(String dvdKey) throws DvdLibraryDaoException {
//        loadRoster();

        Dvd removedDvd = dvdCollection.remove(dvdKey);

        if (removedDvd != null) {

//            writeRoster();
        }

        //remove by key of value
        return removedDvd;
    }

    @Override
    public Dvd updateDvd(Dvd dvd, String titleId) throws DvdLibraryDaoException {
        // add dvd (titleId is generated on object instantiation : name + id) (key) and dvd object (value) to hashMap
        Dvd dvdToUpdate = dvdCollection.put(titleId, dvd);
        //return hashMap
        return dvdToUpdate;
    }

    @Override
    public List<Dvd> findDvdByYear(int key) throws DvdLibraryDaoException {

        List<Dvd> dvdsByYear = new ArrayList<>();

        //loop through collection and check if it ends with or starts with value
        for (Map.Entry<String, Dvd> e : dvdCollection.entrySet()) {
//            if (e.getValue().getReleaseDate() == key) {
//
//                dvdsByYear.add(e.getValue());
//            }
        }
        //return hashMap
        return dvdsByYear;
    }

    @Override
    public Collection<Dvd> getAllDvd() throws DvdLibraryDaoException {
//        loadRoster(); //return all values
        return dvdCollection.values();
    }

    private Dvd unmarshallDvd(String dvdAsText) {

        String[] dvdTokens = dvdAsText.split(DELIMITER);

        Dvd dvdFromFile = new Dvd();

        //not good practice
        dvdFromFile.setId(dvdTokens[0]);

        // Index 1 - title
        dvdFromFile.setTitle(dvdTokens[1]);

        // Index 2 - release
        dvdFromFile.setReleaseDate("1900");

        // Index 3 - dvd
        dvdFromFile.setMpaaRating(dvdTokens[3]);
        // Index 4 - city
        dvdFromFile.setDirectorName(dvdTokens[4]);
        // Index 5 - state
        dvdFromFile.setStudio(dvdTokens[5]);
        // Index 6 - zip
        dvdFromFile.setUserRating(dvdTokens[6]);
        // We have now created a dvd! Return it!
        return dvdFromFile;
    }

    private void loadRoster() throws DvdLibraryDaoException {

        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(ROSTER_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load roster data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentDvd holds the most recent dvd unmarshalled
        Dvd currentDvd;
        // Go through ROSTER_FILE line by line, decoding each line into a
        // Dvd object by calling the unmarshallDvd method.
        // Process while we have more lines in the file

        // Creating an object of Iterator
        Iterator<String> iterate = dvdCollection.keySet().iterator();
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Dvd
            currentDvd = unmarshallDvd(currentLine);

            // check data in file is not already in hashmap
            while (iterate.hasNext()) {

                if (!(dvdCollection.isEmpty())) {

                    System.out.println("collection not empty = check for duplicates");

                    if (!(iterate.next().matches("(.*)" + currentLine.split(DELIMITER)[0] + "(.*)"))) {
                        System.out.println("doesnt exist in dvd collection - so add from file ");
                        dvdCollection.put(currentDvd.getId(), currentDvd);

                    }
                    //"main" java.util.ConcurrentModificationException
                } else {

                    System.out.println("collection empty = add some stuff");
                    dvdCollection.put(currentDvd.getId(), currentDvd);
                }
            }
//            dvdCollection.put(currentDvd.getId(), currentDvd);
        }

        // close scanner
        scanner.close();
    }

    private String marshallDvd(Dvd aDvd) {

        String dvdAsText = aDvd.getId() + DELIMITER;

        // add the rest of the properties in the correct order:
        // title
        dvdAsText += aDvd.getTitle() + DELIMITER;

        // release date
        dvdAsText += aDvd.getReleaseDate() + DELIMITER;

        // mpaarating
        dvdAsText += aDvd.getMpaaRating() + DELIMITER;

        // directorname
        dvdAsText += aDvd.getDirectorName() + DELIMITER;
        // studio
        dvdAsText += aDvd.getStudio() + DELIMITER;
        // userrating
        dvdAsText += aDvd.getUserRating();

        // We have now turned a dvd to text! Return it!
        return dvdAsText;
    }

    /**
     * Writes all dvds in the roster out to a ROSTER_FILE. See loadRoster for file format.
     *
     * @throws ClassRosterDaoException if an error occurs writing to the file
     */
    private void writeRoster() throws DvdLibraryDaoException {

//        System.out.println("wrote to file roster");
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to
        // handle any errors that occur.
        PrintWriter out;
        Scanner scanner;

        try {

            out = new PrintWriter(new FileWriter(ROSTER_FILE));

        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save dvd data.", e);
        }

        // Write out the Dvd objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of Dvds and iterate over them but we've
        // already created a method that gets a List of Dvds so
        // we'll reuse it.
        String dvdAsText;
        List<Dvd> dvdList = new ArrayList(dvdCollection.values());
        for (Dvd currentDvd : dvdList) {
            // turn a Dvd into a String
            dvdAsText = marshallDvd(currentDvd);
            // write the Dvd object to the file
            out.println(dvdAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

}
