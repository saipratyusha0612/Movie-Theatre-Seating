package test.java;

import main.java.service.MovieTheatreService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

/**
 * Tests the movie theatre allocation
 */
public class MovieTheatreServiceTest {
    private MovieTheatreService movieTheatreService;
    @Before
    public void setUp() throws IOException {
        this.movieTheatreService = new MovieTheatreService();
    }
    @Test
    public void ExceedingRequestsTest() throws IOException {
        File inputFile = new File("src/test/data/ExceedingRequests.txt");
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File outputFile = new File("src/test/data/ExceedingRequestsOutput.txt");
        FileReader outputFileReader = new FileReader(outputFile);
        BufferedReader outputBufferedReader = new BufferedReader(outputFileReader);
        String reservation;
        String outputReservation;
        while((reservation=bufferedReader.readLine())!=null&&(outputReservation = outputBufferedReader.readLine())!=null){
            Assert.assertEquals(movieTheatreService.allocateSeats(reservation), outputReservation);
        }
    }
    @Test
    public void NormalRequestsTest() throws IOException {
        File inputFile = new File("src/test/data/NormalRequests.txt");
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File outputFile = new File("src/test/data/NormalRequestsOutput.txt");
        FileReader outputFileReader = new FileReader(outputFile);
        BufferedReader outputBufferedReader = new BufferedReader(outputFileReader);
        String reservation;
        String outputReservation;
        while((reservation=bufferedReader.readLine())!=null&&(outputReservation = outputBufferedReader.readLine())!=null){
            Assert.assertEquals(movieTheatreService.allocateSeats(reservation), outputReservation);
        }
    }
    @Test
    public void ZeroRequestsTest() throws IOException {
        File inputFile = new File("src/test/data/ZeroRequests.txt");
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File outputFile = new File("src/test/data/ZeroRequestsOutput.txt");
        FileReader outputFileReader = new FileReader(outputFile);
        BufferedReader outputBufferedReader = new BufferedReader(outputFileReader);
        String reservation;
        String outputReservation;
        while((reservation=bufferedReader.readLine())!=null&&(outputReservation = outputBufferedReader.readLine())!=null){
            Assert.assertEquals(movieTheatreService.allocateSeats(reservation), outputReservation);
        }
    }
    @Test
    public void InputTest1() throws IOException {
        File inputFile = new File("src/test/data/test1.txt");
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File outputFile = new File("src/test/data/output1.txt");
        FileReader outputFileReader = new FileReader(outputFile);
        BufferedReader outputBufferedReader = new BufferedReader(outputFileReader);
        String reservation;
        String outputReservation;
        while((reservation=bufferedReader.readLine())!=null&&(outputReservation = outputBufferedReader.readLine())!=null){
            Assert.assertEquals(movieTheatreService.allocateSeats(reservation), outputReservation);
        }
    }
    @Test
    public void InputTest2() throws IOException {
        File inputFile = new File("src/test/data/test2.txt");
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File outputFile = new File("src/test/data/output2.txt");
        FileReader outputFileReader = new FileReader(outputFile);
        BufferedReader outputBufferedReader = new BufferedReader(outputFileReader);
        String reservation;
        String outputReservation;
        while((reservation=bufferedReader.readLine())!=null&&(outputReservation = outputBufferedReader.readLine())!=null){
            Assert.assertEquals(movieTheatreService.allocateSeats(reservation), outputReservation);
        }
    }
    @Test
    public void InputTest3() throws IOException {
        File inputFile = new File("src/test/data/test3.txt");
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File outputFile = new File("src/test/data/output3.txt");
        FileReader outputFileReader = new FileReader(outputFile);
        BufferedReader outputBufferedReader = new BufferedReader(outputFileReader);
        String reservation;
        String outputReservation;
        while((reservation=bufferedReader.readLine())!=null&&(outputReservation = outputBufferedReader.readLine())!=null){
            Assert.assertEquals(movieTheatreService.allocateSeats(reservation), outputReservation);
        }
    }
    @Test
    public void InputTest4() throws IOException {
        File inputFile = new File("src/test/data/test4.txt");
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File outputFile = new File("src/test/data/output4.txt");
        FileReader outputFileReader = new FileReader(outputFile);
        BufferedReader outputBufferedReader = new BufferedReader(outputFileReader);
        String reservation;
        String outputReservation;
        while((reservation=bufferedReader.readLine())!=null&&(outputReservation = outputBufferedReader.readLine())!=null){
            Assert.assertEquals(movieTheatreService.allocateSeats(reservation), outputReservation);
        }
    }
    @Test
    public void InputTest5() throws IOException {
        File inputFile = new File("src/test/data/test5.txt");
        FileReader fileReader = new FileReader(inputFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        File outputFile = new File("src/test/data/output5.txt");
        FileReader outputFileReader = new FileReader(outputFile);
        BufferedReader outputBufferedReader = new BufferedReader(outputFileReader);
        String reservation;
        String outputReservation;
        while((reservation=bufferedReader.readLine())!=null&&(outputReservation = outputBufferedReader.readLine())!=null){
            Assert.assertEquals(movieTheatreService.allocateSeats(reservation), outputReservation);
        }
    }
}
