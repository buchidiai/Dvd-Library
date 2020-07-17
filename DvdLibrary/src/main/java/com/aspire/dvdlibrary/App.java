package com.aspire.dvdlibrary;

import com.aspire.dvdlibrary.controller.DvdLibraryController;
import com.aspire.dvdlibrary.dao.DvdLibraryDao;
import com.aspire.dvdlibrary.dao.DvdLibraryDaoFileImpl;
import com.aspire.dvdlibrary.ui.DvdLibraryView;
import com.aspire.dvdlibrary.ui.UserIO;
import com.aspire.dvdlibrary.ui.UserIOConsoleImpl;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author louie
 */
public class App {

    public static void main(String[] args) {

        //Declare a UserIO variable and initialize it with a UserIOConsoleImpl reference
        UserIO myIo = new UserIOConsoleImpl();
        //Declare and instantiate a DvdView object, passing the UserIO created in the previous step into the constructor.
        DvdLibraryView myView = new DvdLibraryView(myIo);
        //Declare a DvdDao variable and initialize it with a DvdLibraryDaoFileImpl reference.
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        //Instantiate a DvdLibraryController, passing the DvdLibraryDao and DvdLibraryView object into the constructor.

        DvdLibraryController controller = new DvdLibraryController(myView, myDao);
        //Call the run method on the controller to kick things off.
        controller.run();
    }

}
