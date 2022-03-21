package main.java.service;

import java.util.*;

/**
 * This class allocates seats according to customer requirement
 *
 */
public class MovieTheatreService {
    private static int ROWS = 10;
    private static int COLUMNS = 20;
    private static int COLUMN_BUFFER = 3;
    private static int INIT_ROW = ROWS/2;
    private static String[][] theatreLayout;
    private Integer[] availableRowSeats;
    private Map<Integer, Set<Integer>> availableSeats;
    private StringBuilder allotedSeats;

    /**
     * Constructor for creating and initializing movie theatre service object
     */
    public MovieTheatreService(){
        theatreLayout = new String[ROWS][COLUMNS];
        availableRowSeats = new Integer[ROWS];
        availableSeats = new HashMap<>();
        for(int i=0;i<ROWS;i++){
            availableSeats.put(i, new HashSet<>());
            for(int j=0;j<COLUMNS;j++){
                availableSeats.get(i).add(j);
            }
        }
        for(int i=0;i<ROWS;i++){
            availableRowSeats[i]=i;
        }

    }

    /**
     * Allocate seats function to allocate seats for each reservation ID
     * @param reservationRequest
     * @return the allocated seats for each reservation
     */
    public String allocateSeats(String reservationRequest){
        String reservationId = reservationRequest.split(" ")[0];
        int requestedSeats = Integer.valueOf(reservationRequest.split(" ")[1]);
        allotedSeats = new StringBuilder();

        //checks if the input is valid
        if(requestedSeats<=0){
            System.out.println("please enter valid number of seats");
            allotedSeats.append("please enter valid number of seats..........");
            return allotedSeats.toString();
        }

        allotedSeats.append(reservationId+" ");

        //finds available seats and allocates best possible seats to customers
        findSeats(requestedSeats, reservationId);
        return allotedSeats.substring(0, allotedSeats.length()-2);
    }

    /**
     * finds available seats and allocates best possible seats to customers for best customer satisfaction
     * @param requestedSeats
     * @param reservationId
     */
    public void findSeats(int requestedSeats, String reservationId){
        int numberOfSeats =0;
        List<int[]> alloteRows = new ArrayList<>();

        //checks if continuous seats are available
        //for best customer satisfaction it starts from middle row to the top and finds continuous seats
        for(int row=INIT_ROW;row<ROWS;row++){
            int[] max = getMaxConsecutive(row);
            if(max[0]>=requestedSeats){
                //allocates the continuous seats found to the customer
                allocate(row, max[1], requestedSeats, reservationId);
                return;
            }
        }
        //checks from middle to bottom row and finds continuous seats
        for(int row=INIT_ROW-1;row>=0;row--){
            int[] max = getMaxConsecutive(row);
            if(max[0]>=requestedSeats){
                //allocates the continuous seats found to the customer
                allocate(row, max[1], requestedSeats, reservationId);
                return;
            }
        }
        boolean unableToAllocate = false;
        Map<Integer, Set<Integer>> tempAvailableSeats = new HashMap<>(availableSeats);
        //if continuous seats are not available it splits the allocation to group the customers as closely as possible
        while(!unableToAllocate){
            final int p=numberOfSeats;

            //sorts the rows based on number of continuous seats available
            Arrays.sort(availableRowSeats, new Comparator<Integer>(){
                @Override
                public int compare(Integer o1, Integer o2) {
                    int maxConsecutiveSeats1 = getMaxConsecutive(o1)[0];
                    int maxConsecutiveSeats2 = getMaxConsecutive(o2)[0];
                    if(maxConsecutiveSeats1==maxConsecutiveSeats2){
                        return o2-o1;
                    }
                    if(maxConsecutiveSeats2>=requestedSeats-p && maxConsecutiveSeats1>=requestedSeats-p){
                        return o2-o1;
                    }
                    return maxConsecutiveSeats2-maxConsecutiveSeats1;
                }
            });
            int[] curr = getMaxConsecutive(availableRowSeats[0]);
            if(curr[0]==0){
                unableToAllocate = true;
            }
            for(int j=curr[1];j<Math.min(curr[0], requestedSeats-numberOfSeats)+curr[1];j++) {
                //removes the allocated seats from available seats
                tempAvailableSeats.get(availableRowSeats[0]).remove(j);
            }
            alloteRows.add(new int[]{availableRowSeats[0], Math.min(curr[0], requestedSeats-numberOfSeats), curr[1]});
            numberOfSeats = numberOfSeats+curr[0];
            if(numberOfSeats>=requestedSeats){
                //allocates the seats found in multiple rows to the customer
                availableSeats = tempAvailableSeats;
                allocateSeperately(alloteRows, requestedSeats, reservationId);
                return;
            }
        }
        allotedSeats.append("Sorry Not able to allocate as the request exceed availability....");
    }

    /**
     * allocates the seats to customer in multiple rows
     * @param alloteRows
     * @param requestedSeats
     * @param reservationId
     */
    private void allocateSeperately(List<int[]> alloteRows, int requestedSeats, String reservationId) {
        for(int j=0;j<alloteRows.size();j++){
            int startSeat = alloteRows.get(j)[2];
            int end = alloteRows.get(j)[1]+startSeat;
            int row = alloteRows.get(j)[0];
            for(int i=startSeat;i<end;i++){
                //allocates seats
                theatreLayout[row][i]=reservationId;
                char columnSeat = (char)('A'+row);
                allotedSeats.append(columnSeat);
                allotedSeats.append(i+", ");
                addRowBuffer(row, i);
            }
            addColumnBuffer(row, startSeat, end);
        }

    }

    /**
     * allocates the continuous seats to the customer
     * @param row
     * @param startSeat
     * @param requestedSeats
     * @param reservationId
     */
    private void allocate(int row, int startSeat, int requestedSeats, String reservationId) {
        for(int i=startSeat;i<startSeat+requestedSeats;i++){
            //allocates seats
            theatreLayout[row][i]=reservationId;
            char columnSeat = (char)('A'+row);
            allotedSeats.append(columnSeat);
            allotedSeats.append(i+", ");
            //removes the allocated seats from available seats
            availableSeats.get(row).remove(i);
            addRowBuffer(row, i);
        }
        addColumnBuffer(row, startSeat, startSeat+requestedSeats);
    }

    /**
     * finds the maximum number of consecutive seats available in a row
     * @param row
     * @return
     */
    public int[] getMaxConsecutive(int row){
        int maxConsecutiveSeats=0;
        int startSeat = -1;
        for(int seat : availableSeats.get(row)){
            if(!availableSeats.get(row).contains(seat-1)){
                int currSeat=seat;
                int count=1;
                while(availableSeats.get(row).contains(currSeat+1)){
                    currSeat++;
                    count++;
                }
                if(maxConsecutiveSeats<count){
                    maxConsecutiveSeats=count;
                    startSeat=seat;
                }
            }
        }
        return new int[]{maxConsecutiveSeats, startSeat};
    }

    /**
     * adds column buffer for customer safety
     * @param row
     * @param startSeat
     * @param end
     */
    public void addColumnBuffer(int row, int startSeat, int end){
        for(int i=1;i<=COLUMN_BUFFER;i++){
            if(availableSeats.get(row).contains(startSeat-i))
                availableSeats.get(row).remove(startSeat-i);
            if(availableSeats.get(row).contains(end+i-1))
                availableSeats.get(row).remove(end+i-1);
        }
    }

    /**
     * adds row buffer of one row for customer safety
     * @param row
     * @param seat
     */
    public void addRowBuffer(int row, int seat){
        if(row!=0&&availableSeats.get(row-1).contains(seat)){
            availableSeats.get(row-1).remove(seat);
        }
        if(row!=ROWS-1 &&availableSeats.get(row+1).contains(seat)){
            availableSeats.get(row+1).remove(seat);
        }
    }
}
