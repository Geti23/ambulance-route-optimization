package org.generator;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

class IO extends PrintWriter {

    public IO(OutputStream o) {
        super(new BufferedOutputStream(o));
    }

    /**
     * Writes all the information generated into the output file
     * Catches exceptions if something goes wrong
     *
     * @param outPath Path and name of the output file
     * @param content Generated environment in PDDL
     */
    public static void printPDDL(String outPath, String content) {
        IO writer;
        try {
            OutputStream output = new FileOutputStream(outPath);
            writer = new IO(output);
            writer.write(content);
            writer.close();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
