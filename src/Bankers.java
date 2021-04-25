/**
 * Author       : Shivani Sanjay Tergaonkar
 * Email ID     : stergaonkar@scu.edu
 * File Name    : Bankers.java
 * Date Created : 02/22/2021
 * Version      : v1.0
 * Description  : Program a simulation of the banker’s algorithm.
 *                Your program should cycle through each of the bank clients asking for a request
 *                and evaluating whether it is safe or unsafe. Output a log of requests and decisions to a file.
 */
import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Bankers {

        int p;
        int r;
        int unsafeProcess;

        int max[][];
        int allocate[][];
        int available[];
        int safe[];
        Scanner scanner=new Scanner(System.in);
        File file ;
        PrintWriter writer;

        public static void main(String[] args)
        {

            Bankers bankers = new Bankers();

            bankers.AcceptUserInput();
            bankers.WriteFile();
            bankers.AcceptRequest();
        }

        /** This method accept user input for total number of processes, total number of resources,
         * Allocation matrix and Available matrix.
         *
         */
        void AcceptUserInput()
        {

            //Total number of processes
            System.out.println("Please enter number of processes: ");
            p=scanner.nextInt();

            //Total number of resources
            System.out.println("Please enter number of resources: ");
            r=scanner.nextInt();

            //Allocation Matrix
            System.out.println("Please enter the Allocation matrix: ");
            allocate=new int[p][r];
            for (int i = 0; i < p; i++) {
                for (int j = 0; j < r; j++) {
                    allocate[i][j]=scanner.nextInt();
                }
                }

            // Available Resources
            System.out.println("Please enter the Available matrix: ");
            available=new int[r];
            for (int i = 0; i < r; i++) {
                available[i]=scanner.nextInt();
            }


            safe= new int[p];

        }

    /** This method checks for the given processes, Allocation matrix and Available matrix whether the system is in safe
     * or un-safe state. If the the system is in safe state it displays the safe sequence of processes.If the system
     * leads to un-safe state , then it displays the process which lead to un-safe state.
     *
     */
    void CheckSafe()
        {
            int count=0;

            //visited array is initialized to 0
            int visited[] = new int[p];
            for (int i = 0;i < p; i++)
            {

                visited[i] = 0;
            }

            //copyAvail array to store the copy of available resources
            int copyAvail[] = new int[r];
            for (int i = 0;i < r; i++)
            {
                copyAvail[i] = available[i];
            }

            while (count<p)
            {

                boolean flag = false;
                for (int i = 0;i < p; i++)
                {

                    if (visited[i] == 0)
                    {

                        int j;
                        for (j = 0;j < r; j++)
                        {

                                if(max[i][j]-allocate[i][j] > copyAvail[j])
                                {
                                unsafeProcess = i;
                                break;
                            }
                        }
                        if (j == r)
                        {
                            safe[count++]=i; // captures the sequence for safe state
                            visited[i]=1; //visited array is updated to mark the process which is completed
                            flag=true;
                            System.out.println("\nProcess "+i+" is running");
                            writer.println("\nProcess "+i+" is running");
                            System.out.println("\nAvailable resources are: ");
                            writer.println("\nAvailable resources are: ");
                            writer.println("");
                            for (j = 0;j < r; j++)
                            {   //adds the resources of allocation matrix to copy of available matrix for completed process
                                copyAvail[j] = copyAvail[j]+allocate[i][j];
                                System.out.print(copyAvail[j] + " ");
                                writer.print(copyAvail[j] + " ");
                            }
                            writer.println("");
                            System.out.println("\nProcess "+i+" has completed");
                            writer.println("\nProcess "+i+" has completed");
                            writer.println("===========================================================================");
                        }
                    }
                }
                if (flag == false)
                {

                    break;
                }
            }
            if (count < p)
            {
                System.out.println("\nThe System is UnSafe for process "+unsafeProcess);
                writer.println("\nThe System is UnSafe for process "+unsafeProcess);
            }
            else
            {

                System.out.println("\nFollowing is the SAFE Sequence");

                writer.println("\nFollowing is the SAFE Sequence");
                for (int i = 0;i < p; i++)
                {
                    System.out.print("P" + safe[i] + " ");
                    writer.println("P" + safe[i]+" ");
                }
            }
        }

    /** This method accepts the user input for max resource matrix and runs in a loop to check if the system
     * leads to safe for unsafe state. It prompts to enter Y/N whether or not to continue to check for different
     * Max resource matrix and check if system leads to safe or un-safe state.
     *
     */
    void AcceptRequest()
        {
            String yesNo="Y";

            while(yesNo.equals("Y"))
            {
                System.out.println("Please enter the Max resource matrix: ");
                writer.println("\n\nMax resource matrix is : ");
                max=new int[p][r];
                for (int i = 0; i < p; i++) {
                    for (int j = 0; j < r; j++) {
                        max[i][j]=scanner.nextInt();
                        writer.print(max[i][j] + " ");
                    }
                    writer.println();
                }
                writer.println("===========================================================================");
                CheckSafe();
                writer.println("===========================================================================");
                System.out.println("\n\nDo you want to continue: ");
                writer.println("\nDo you want to continue:  ");

                yesNo=scanner.next();
                writer.println(yesNo);
                writer.println("===========================================================================");
            }
            writer.close();
            scanner.close();

        }

    /** This method creates a new file "Output.txt" at the mentioned location and writes the Total number of processes,
     * resources , Allocation matrix, Available matrix , Max resource matrix , log of requests and decisions to
     * a file.
     *
     */
    void WriteFile()
        {
            try {
                 //file = new File("/Users/shivani/Output.txt");
                file = new File("Output.txt"); //Creates a new file with name Output.txt
                 file.createNewFile();

               // writer = new PrintWriter("/Users/shivani/Output.txt", "UTF-8");
                writer = new PrintWriter("Output.txt", "UTF-8");//Creates a writer object which writes to Output.txt

                writer.println("Banker algorithm");
                writer.println("===========================================================================");
                writer.println("Total number of processes is: "+p);
                writer.println("===========================================================================");
                writer.println("\nTotal number of resources is: "+r);
                writer.println("===========================================================================");
                writer.println("\nAllocated matrix is : ");
                for (int i = 0; i < p; i++) {
                    for (int j = 0; j < r; j++) {
                        writer.print(allocate[i][j] + " ");
                    }
                    writer.println();
                }
                writer.println("===========================================================================");
                writer.println("\nAvailable matrix is : ");
                for (int i = 0; i < r; i++) {

                        writer.print(available[i] + " ");
                    }
                writer.println("");
                writer.println("===========================================================================");
            }
            catch(Exception e)
            {
                System.out.println();
            }

        }


    }


