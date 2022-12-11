import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

class Paging{
    int pageFoundAt=0, atOffset=0; //Rsult of the algortithm
    int totalPages=0, offset=0, totalFrames=0, framePageSize=0; //framePageSize is nothing but frame size or page size
    ArrayList<Integer> mainMemEq= new ArrayList<Integer>(); //Just a temporary array to get all the frame numbers in the main memory

    // Variables for the logical address: (Page number and offset)
    int pageNumber;
    String logicalAddress=""; //offset will be common throughout, so declared in one of the previous lines
    HashMap<Integer, Integer> pageTable= new HashMap<Integer, Integer>();
    //The HashMap is the pageTable of a process with key value pairs as (Page Number, Frame Number)

    //Variables for the physical address: (Page number and offset)
    int frameNumber=0;
    String physicalAddress=""; //offset will remain common throughout, so declared in one of the previous lines
    HashMap<Integer, Integer> mainMemory= new HashMap<Integer, Integer>();
    //The HashMap is the mainMemory space with key value pairs as (FrameNumber, PageNumber)

    public void getSizes(){
        Scanner s= new Scanner(System.in);
        mainMemory.clear(); mainMemEq.clear(); pageTable.clear();

        //Following 6 lines speak for themselves
        System.out.print("Specify the size of each page or the size of each frame: ");
        framePageSize=s.nextInt();

        System.out.print("Specify the number of pages for the process: ");
        totalPages= s.nextInt();

        System.out.print("Specify the total number of frames in the main memory: ");
        totalFrames=s.nextInt();

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
            pageTable.put(pageNum, frameIndex); //to be changed
            mainMemEq.remove(0);
        }
    }

    public void contentsOfTables(){
        //Displaying the page table contents and the main memory contents
        System.out.println("pageTable contains: "+ pageTable);
        System.out.println("mainMemory contains: "+ mainMemory);
    }

    public void getLogicalAddress(){
        
        Scanner s= new Scanner(System.in);
        //Asking user for the logical address
        System.out.print("For the logical address, enter the page number: ");
        pageNumber=s.nextInt();
        System.out.print("enter the offset for the current logical address: ");
        offset=s.nextInt();
        logicalAddress="|| "+ pageNumber +" || "+offset+" ||";
        System.out.println("The logical address to generated is:  "+ logicalAddress);
        s.close();
    }

    public void determiningPhysicalAddress(){
         // Determining the physical address
         frameNumber=pageTable.get(pageNumber);
         System.out.println("Frame number as fetched from the page table:  "+ frameNumber);
         physicalAddress="|| "+ frameNumber+" || "+ offset+" ||";
         System.out.println("The physical address generated is:   "+ physicalAddress);
    }

    public void fetchingPageFromMainMemory(){
        //Fetching the page from the main memory and dispyaing where it is found
        System.out.println("Fetching content of the page requestd by the logical address at the offset: ");
        pageFoundAt=pageTable.get(pageNumber);
        atOffset=frameNumber+offset;
        System.out.println("CPU requested access to page number:  "+ pageFoundAt+"\nat an offset of:  "+ atOffset);
    }
}

public class App {
    public static void main(String[] args) throws Exception {

        Paging cpu= new Paging();

        cpu.getSizes();
        cpu.getTotalMemorySpace();
        cpu.allocatePagesToFrames();
        cpu.contentsOfTables();
        cpu.getLogicalAddress();
        cpu.determiningPhysicalAddress();
        cpu.fetchingPageFromMainMemory();
        
        // Scanner s= new Scanner(System.in); //Scanner object to read user inputs
        
        // int pageFoundAt=0, atOffset=0; //Rsult of the algortithm

        // int totalPages=0, offset=0, totalFrames=0, framePageSize=0; //framePageSize is nothing but frame size or page size
        // ArrayList<Integer> mainMemEq= new ArrayList<Integer>(); //Just a temporary array to get all the frame numbers in the main memory

        // // Variables for the logical address: (Page number and offset)
        // int pageNumber=0;
        // String logicalAddress=""; //offset will be common throughout, so declared in one of the previous lines
        // HashMap<Integer, Integer> pageTable= new HashMap<Integer, Integer>();
        // //The HashMap is the pageTable of a process with key value pairs as (Page Number, Frame Number)

        // //Variables for the physical address: (Page number and offset)
        // int frameNumber=0;
        // String physicalAddress=""; //offset will remain common throughout, so declared in one of the previous lines
        // HashMap<Integer, Integer> mainMemory= new HashMap<Integer, Integer>();
        // //The HashMap is the mainMemory space with key value pairs as (FrameNumber, PageNumber)

        // //Will add a loop later, so I have written these lines. Ignore them for now.
        // mainMemory.clear(); mainMemEq.clear(); pageTable.clear();

        // //Following 6 lines speak for themselves
        // System.out.print("Specify the size of each page or the size of each frame: ");
        // framePageSize=s.nextInt();

        // System.out.print("Specify the number of pages for the process: ");
        // totalPages= s.nextInt();

        // System.out.print("Specify the total number of frames in the main memory: ");
        // totalFrames=s.nextInt();

        // //Measuring the total space of the main memory and storing the frame numbers in the ArrayList, mainMemEq
        // int frameNum=-framePageSize; 
        // while(frameNum<=(totalFrames-1)*framePageSize){
        //     frameNum+=framePageSize;
        //     mainMemEq.add(frameNum);
        // }

        // //Assigning pages to frames randomly
        // int pageNum=-framePageSize;
        // while(pageNum<=(totalPages-1)*framePageSize){
        //     Collections.shuffle(mainMemEq);
        //     int frameIndex=mainMemEq.get(0);
        //     pageNum+=framePageSize;
        //     mainMemory.put(frameIndex, pageNum);
        //     pageTable.put(pageNum, frameIndex); //to be changed
        //     mainMemEq.remove(0);
        // }

        // //Displaying the page table contents and the main memory contents
        // System.out.println("pageTable contains: "+ pageTable);
        // System.out.println("mainMemory contains: "+ mainMemory);

        // //Asking user for the logical address
        // System.out.print("For the logical address, enter the page number: ");
        // pageNumber=s.nextInt();
        // System.out.print("enter the offset for the current logical address: ");
        // offset=s.nextInt();
        // logicalAddress="|| "+ pageNumber +" || "+offset+" ||";
        // System.out.println("The logical address to generated is:  "+ logicalAddress);

        // //Closing scanner object. Ignore statement.
        // s.close();
        
        // // Determining the physical address
        // frameNumber=pageTable.get(pageNumber);
        // System.out.println("Frame number as fetched from the page table:  "+ frameNumber);
        // physicalAddress="|| "+ frameNumber+" || "+ offset+" ||";
        // System.out.println("The physical address generated is:   "+ physicalAddress);

        // //Fetching the page from the main memory and dispyaing where it is found
        // System.out.println("Fetching content of the page requestd by the logical address at the offset: ");
        // pageFoundAt=pageTable.get(pageNumber);
        // atOffset=frameNumber+offset;
        // System.out.println("CPU requested access to page number:  "+ pageFoundAt+"\nat an offset of:  "+ atOffset);
        
    }
}
