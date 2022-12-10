import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner s= new Scanner(System.in);
        int pageFoundAt=0, atOffset=0;
        int totalPages=0, offset=0, totalFrames=0, framePageSize=0;
        ArrayList<Integer> mainMemEq= new ArrayList<Integer>();

        int pageNumber=0;
        String logicalAddress=""; //offset will be common throughout, so declared in previous line
        HashMap<Integer, Integer> pageTable= new HashMap<Integer, Integer>();

        int frameNumber=0;
        String physicalAddress=""; //offset will remain the same as in logicalAddress
        HashMap<Integer, Integer> mainMemory= new HashMap<Integer, Integer>();

        mainMemory.clear(); mainMemEq.clear(); pageTable.clear();

        System.out.print("Specify the size of each page or the size of each frame: ");
        framePageSize=s.nextInt();

        System.out.print("Specify the number of pages for the process: ");
        totalPages= s.nextInt();

        // System.out.print("Specify the offset for each page: ");
        // offset=s.nextInt();

        System.out.print("Specify the total number of frames in the main memory: ");
        totalFrames=s.nextInt();

        int frameNum=-framePageSize;
        while(frameNum<=(totalFrames-1)*framePageSize){
            frameNum+=framePageSize;
            mainMemEq.add(frameNum);
        }

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

        System.out.println("pageTable contains: "+ pageTable);
        System.out.println("mainMemory contains: "+ mainMemory);

        System.out.print("For the logical address, enter the page number: ");
        pageNumber=s.nextInt();
        System.out.print("enter the offset for the current logical address: ");
        offset=s.nextInt();
        logicalAddress="|| "+ pageNumber +" || "+offset+" ||";
        System.out.println("The logical address to generated is:  "+ logicalAddress);

        s.close();
        
        frameNumber=pageTable.get(pageNumber);
        System.out.println("Frame number as fetched from the page table:  "+ frameNumber);
        physicalAddress="|| "+ frameNumber+" || "+ offset+" ||";
        System.out.println("The physical address generated is:   "+ physicalAddress);

        System.out.println("Fetching content of the page requestd by the logical address at the offset: ");
        pageFoundAt=pageTable.get(pageNumber);
        atOffset=frameNumber+(framePageSize-offset);
        System.out.println("CPU requested access to page number:  "+ pageFoundAt+"\nat an offset of:  "+ atOffset);
        
    }
}
