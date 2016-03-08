package com.team3997.frc2016.util;

import com.team3997.frc2016.Params;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 
 * 
 * 
 * PURPOSE:
 * This is used to update constants (such as PID) to the robot without redeploying.
 * 
 * HOW TO USE:
 * Add a Constant object to the Params.java file, then
 * add that constant to the text file via FTP on the roboRio @ "/home/admin/params.txt"
 *
 * The constants in the text file use the following format:
 * nameOfParameter valueOfParameter
 * shooterMotorLeft 2
 *
 */

public class UpdateParameters {

    /**
     * Constants loaded from params file.
     */
    public static ArrayList<Constant> constants = new ArrayList<>();

    private static File paramsFile;

    public static void makeFile() {
        paramsFile = new File(Params.CONSTANTS_FILE);
        try {
            paramsFile.createNewFile();
        } catch (IOException e) {
            System.out.println("Error creating params.txt file");
        }
    }

    /**
     * Load the parameters file using this syntax:
     *
     * nameOfParameter valueOfParameter
     * shooterMotorLeft 2
     *
     * Constants listed in the file override hardcoded constants.
     */
    public static void update() {
    	makeFile();
        try (BufferedReader br = new BufferedReader(new FileReader(paramsFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                int pos = line.indexOf(" ");
                if (pos != -1) {
                    for (Constant c : constants) {
                        if (c.getKey().equals(line.substring(0, pos))) {
                            c.value = Double.parseDouble(line.substring(pos));
                        }
                    }
                } else {
                    System.out.println("Invalid params.txt line");
                }
            }
        } catch (Exception e) {
            System.out.println("Error reading params.txt");
        }
    }

    public static class Constant {

        private final String key;
        private volatile double value;

        /**
         * Make a final Constant.
         *
         * @param key Name of value.
         * @param value Value.
         */
        public Constant(String key, double value) {
            this.key = key;
            this.value = value;
            
            constants.add(this);
        }

        public String getKey() {
            return key;
        }

        public Double getDouble() {
            return value;
        }
        
        public int getInt() {
            return (int)value;
        }

        public boolean getBoolean() {
            return value == 1;
        }

        @Override
        public String toString() {
            return key + ": " + value;
        }
    }
}