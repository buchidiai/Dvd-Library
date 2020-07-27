/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.dvdlibrary.ui;

import java.util.Scanner;
import org.springframework.stereotype.Component;

/**
 *
 * @author louie
 */
@Component
public class UserIOConsoleImpl implements UserIO {

    final private Scanner in = new Scanner(System.in);

    @Override
    public void print(String message) {

        System.out.println(message);
    }

    @Override
    public String readString(String prompt) {

        this.print(prompt);
        return in.nextLine();

    }

    @Override
    public int readInt(String prompt) {

        int response = 0;
        boolean valid = true;

        while (valid) {

            try {

                String stringValue = this.readString(prompt);
                response = Integer.parseInt(stringValue);

                valid = false;

            } catch (Exception e) {

                this.print("Value must be a number");

            }

        }

        return response;

    }

    @Override
    public int readInt(String prompt, int min, int max) {

        int result = 0;

        try {

            do {
                result = readInt(prompt + " (" + min + "-" + max + ")");
            } while (result < min || result > max);

        } catch (Exception e) {

            this.print("Value must be a numberdsd");

        }

        return result;
    }

    @Override
    public double readDouble(String prompt) {
        double response = 0;
        boolean valid = true;

        while (valid) {

            try {

                print(prompt);
                response = Double.parseDouble(in.nextLine());

                return response;

            } catch (Exception e) {
                System.out.println("Must be a number " + e.getMessage());

            }

        }

        return response;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        double response = 0;
        boolean valid = true;

        while (valid) {

            print(prompt + "(" + min + "-" + max + ")");
            response = Double.parseDouble(in.nextLine());

            if (!(response >= min && response <= max)) {
                System.out.println("Must be between " + "(" + min + "-" + max + ")");
                continue;
            }

            return response;

        }

        return response;
    }

    @Override
    public float readFloat(String prompt) {
        float response = 0;
        boolean valid = true;

        while (valid) {

            try {

                print(prompt);
                response = Float.parseFloat(in.nextLine());

                return response;

            } catch (Exception e) {
                System.out.println("Must be a number " + e.getMessage());

            }

        }

        return response;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        float response = 0;
        boolean valid = true;

        while (valid) {

            print(prompt + "(" + min + "-" + max + ")");
            response = Float.parseFloat(in.nextLine());

            if (!(response >= min && response <= max)) {
                System.out.println("Must be between " + "(" + min + "-" + max + ")");
                continue;
            }

            return response;

        }

        return response;
    }

    @Override
    public long readLong(String prompt) {
        long response = 0;
        boolean valid = true;

        while (valid) {

            try {

                print(prompt);
                response = Long.parseLong(in.nextLine());

                return response;

            } catch (Exception e) {
                System.out.println("Must be a number " + e.getMessage());

            }

        }

        return response;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        long response = 0;
        boolean valid = true;

        while (valid) {

            print(prompt + "(" + min + "-" + max + ")");
            response = Long.parseLong(in.nextLine());

            if (!(response >= min && response <= max)) {
                System.out.println("Must be between " + "(" + min + "-" + max + ")");
                continue;
            }

            return response;

        }

        return response;
    }

}
