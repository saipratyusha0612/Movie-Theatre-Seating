package main.java;

import main.java.service.MovieTheatreService;

import java.io.*;

/**
 * This is the start point for Theatre seat allocation
 * @author Sai Pratyusha Attanti
 */
public class Theatre {
    /**
     * Main function
     * accepts input file name as command line args
     * @param args
     */
    public static void main(String[] args){
        try {
            //Takes the command line args to read the file
            File inputFile = new File(args[0]);
            FileReader fileReader = new FileReader(inputFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            //creates MovieTheatreService object which allocates seats
            MovieTheatreService movieTheatreService = new MovieTheatreService();

            String reservation;
            //Creates and opens output file to write the allocated seats information
            File outputFile = new File("../output.txt");
            new FileWriter("../output.txt", false).close();
            FileWriter fw = new FileWriter("../output.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);

            while((reservation=bufferedReader.readLine())!=null){
                //call the allocateSeats method for each reservation
                String allotedSeats = movieTheatreService.allocateSeats(reservation);
                out.println(allotedSeats);
            }
            fileReader.close();
            out.close();

            //Reads the output files and prints the output to commandline
            fileReader = new FileReader(outputFile);
            bufferedReader = new BufferedReader(fileReader);
            while((reservation=bufferedReader.readLine())!=null){
                System.out.println(reservation);
            }
            fileReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
