# Movie Theatre Seating Challenge
### Overview
Created a service which assigns seats to the customer based on their reservation request. It takes the input file as command line argument which contains one line of input for each reservation request. 
Based on the requests in the file seats are allocated making sure seats from one reservation are allocated as closely as possible. And the output of this allocations are written to an output file.
#### Language used : JAVA
#### Assumptions 
1. If the input is invalid such as number of seats requested is negative or zero we ignore the request and print the error to the output file.
2. To maximize customer satisfaction we start allocation from the middle row as most of the customers prefer middle of the theatre for best view. Initially we start from middle row and go up until there are no available spaces matching the customer requirements. Then we start going down from the middle row.  
3. For the same reason we make sure the customers of one reservation are allocated seats as closely as possible. If allocation to same row is not possible we allocate them in separate rows such that maximum people are grouped together.
4. Considering public safety we have included a column buffer of 3 seats on either side after the seats are allocated to the group. We also consider a row buffer of one row such that customers of one group are not seated exactly to the front or back of customers of different group.
5. If the reservation request exceeds the availability we ignore the request and print the error to the output file.

#### Instruction to run the program
1. Clone the repository.
2. Open the command line and navigate to the src directory.
3. To compile the program run `javac main/java/Theatre.java`
4. To execute the program run `java main.java.Theatre ../input.txt`


