import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

//Class to handle the paging algorithm
class Paging{

    int pageFoundAt=0, atOffset=0;
    boolean verifyMaxFrames,verifyLogicalAddress; 
    //Rsult of the algortithm
    
    int totalPages=0, offset=0, totalFrames=0, framePageSize=0;
    //framePageSize is nothing but frame size or page size

    ArrayList<Integer> mainMemEq= new ArrayList<Integer>();
    //Just a temporary array to get all the frame numbers in the main memory 

    // Variables for the logical address: (Page number and offset)
    int pageNumber;

    String logicalAddress=""; 
    //The entire logical address( |page number | offset| ) will be produced in the string logicaAddress
    
    HashMap<Integer, Integer> pageTable= new HashMap<Integer, Integer>();
    //The "pageTable" is the Page Table of a process with key value pairs as (Page Number, Frame Number)

    //Variables for the physical address: (Page number and offset)
    int frameNumber=0; //offset will remain common throughout, so declared in one of the previous lines

    String physicalAddress=""; 
    //The entire physical address ( |frame number | offset| ) will be generated in the string physicalAddress
    
    HashMap<Integer, Integer> mainMemory= new HashMap<Integer, Integer>();
    //The HashMap is the mainMemory space with key value pairs as (FrameNumber, PageNumber)

    public void getSizes(){
        verifyMaxFrames=false;
        Scanner askScanner= new Scanner(System.in);

        //clearing out the page table and main memory to create fresh ones each time.
        mainMemory.clear(); mainMemEq.clear(); pageTable.clear();

        //Following 6 lines speak for themselves
        System.out.print("\nSpecify the size of each page or the size of each frame: ");
        framePageSize=askScanner.nextInt();

        System.out.print("\nSpecify the number of pages for the process: ");
        totalPages= askScanner.nextInt();

        //Loop is present to ensure that the algorithm will work until the user 
        //enters number of frames such that they are more than the number of pages
        while(!verifyMaxFrames){
            System.out.print("\nSpecify the total number of frames in the main memory: ");
            totalFrames=askScanner.nextInt();
            if(totalFrames>totalPages)
                verifyMaxFrames=true;
            else{
                System.out.println("\nNumber of frames must be greater than the number of pages."+
                "Please enter number of frames again.");
            }
        }


    }

    public void getTotalMemorySpace(){
        //Measuring the total space of the main memory and storing the frame numbers in the ArrayList, mainMemEq
        int frameNum=-framePageSize; 
        while(frameNum<=(totalFrames-1)*framePageSize){
            frameNum+=framePageSize;
            mainMemEq.add(frameNum);
        }
    }

    public void allocatePagesToFrames(){
        //Assigning pages to frames randomly
        int pageNum=-framePageSize;
        while(pageNum<=(totalPages-1)*framePageSize){
            Collections.shuffle(mainMemEq);
            int frameIndex=mainMemEq.get(0);
            pageNum+=framePageSize;
            mainMemory.put(frameIndex, pageNum);
            pageTable.put(pageNum, frameIndex);
            mainMemEq.remove(0);
        }
    }

    public void contentsOfTables(){
        //Displaying the page table contents and the main memory contents
        System.out.println("\n\npageTable contains: "+ pageTable);
        System.out.println("\n\nmainMemory contains: "+ mainMemory);
    }

    public void getLogicalAddress(){
        
        Scanner s= new Scanner(System.in);
        verifyLogicalAddress=true;
        
        //Asking user for the logical address
        System.out.print("\nFor the logical address, enter the page number: ");
        pageNumber=s.nextInt();

        System.out.print("enter the offset for the current logical address: ");
        offset=s.nextInt();
        
        //Ensuring that the logical address specified is valid
        if(!pageTable.containsKey(pageNumber) || offset>=framePageSize )
            verifyLogicalAddress= false;

        logicalAddress="|| "+ pageNumber +" || "+offset+" ||";
        
        System.out.println("\nThe logical address generated is:  "+ logicalAddress);
        
    }

    public void determiningPhysicalAddress(){
         // Determining the physical address
         //Physical address is only determined if the specified logical address is valid
         if(verifyLogicalAddress){
            frameNumber=pageTable.get(pageNumber);
            System.out.println("\nFrame number as fetched from the page table:  "+ frameNumber);
            physicalAddress="|| "+ frameNumber+" || "+ offset+" ||";
            System.out.println("\nThe physical address generated is:   "+ physicalAddress);
         }
         else{
            System.out.println("\n\n!!!--- The combiantion, ||page number||offset||,"
            +"as requested by CPU is invalid or is out of bounds ---!!!\n\n");
         }
    }

    public void fetchingPageFromMainMemory(){
        //Fetching the page from the main memory and displaying where it is found
        
        //fetching page only if the logical address is valid
        if(verifyLogicalAddress){
            System.out.println("\nFetching content of the ||page number||offset||, as requestd by the logical address: ");
        pageFoundAt=mainMemory.get(frameNumber);
        atOffset=frameNumber+offset;
        System.out.println("\nCPU requested access to page number:  "+ pageFoundAt+"\n"+
        "which is present at the frame number: "+frameNumber+"\nat an offset of:  "+ atOffset);
        }
        else{
            System.out.println("\nPlease provide a valid combination of ||page number||offset||. Valid page numbers include: ");
            System.out.println(pageTable.keySet());
        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {

        Scanner askMe= new Scanner(System.in);
        
        String play= "yes", fetch="yes";
        //Objective of either variables is mentioned when encountered further in the code.

        /*The object, cpu is the object of the class Paging.
        It handles the working of the entir algorithm*/
        Paging cpu= new Paging();

        /*Loop is written so that the algorithm executes for as long 
        as the user is interested in watching it work*/
        while(play.equals("yes") || play.equals("Yes")){
            cpu.getSizes();
            cpu.getTotalMemorySpace();
            cpu.allocatePagesToFrames();
            
            //Loop is written in case implementation is to be tested for multiple page fetches
            while(fetch.equals("yes") || fetch.equals("Yes")){
                cpu.contentsOfTables();
                cpu.getLogicalAddress();
                cpu.determiningPhysicalAddress();
                cpu.fetchingPageFromMainMemory();
                System.out.print("\nDo you want to fetch a page again? (yes or no) ");
                fetch=askMe.nextLine();
            }
            System.out.print("\nDo you want to test the implemntation with a fresh set of sizes? (yes or no) ");
            play=askMe.nextLine();
            fetch="yes";
        }

        askMe.close();
        System.out.println("\nPresented to you by:\n\tShreyas Kumar\n\tSirish Pandit\n\tSneha Sural\n\tSuhas Pai");
                
    }
}
