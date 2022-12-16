# Paging Technique

This repository holds the .java file called `App.java` within the `src` folder. The program mimics the activity of fetching a process into the main memory by a memory management technique called Paging.

"Paging a process" can be traced as follows:
Processes are present within the secondary memory unit of a system. For a process to be executed by the CPU, it must be brought to the main memory or the RAM in order to achieve reasonably fast speeds of execution. There are several ways to fetch a process to the RAM. "Paging" being one of them. This commute of processes is handled by a special unit in the system called as the Memory Management Unit (MMU).

For a process which is brought to the RAM by the means of paging- 
1. Before a process is dumped into the RAM, it is divided into several parts. Each part is of the same width or size. These parts are called as **Pages**.
2.  The pages are now brought to the RAM and are placed within the blocks of the RAM which are free. The pages need not necessarily be placed sequentially or at contiguous address spaces. The CPU accesses a process one page at at time. So the placement of pages is not necessary.
The CPU can request for any bit of the process, now that the process has been loaded within the main memory (RAM). 
*It is crucial for the width of each page to be identical to the width of the main memory. It'll be clear as to why this has to be the case in a little while.*
3. The blocks of the main memory are called as **Frames**.
4. To track which page is present in which frame, each process that is paged and fetched to the main memory has a table associated with it which contains page numbers as its indices and at each index is present, the frame number (Among many other things which are out of scope for the current topic) where the page was placed. This is called as the **Page Table**.

## CPU demanding information from the pages

CPU can request to access any page and any information within a page.
This is how a request of CPU is treated:
1. CPU asks for a certain bit of a certain page. This combination of |Page Number|Bit| is called as the logical address. Formally, the bit is called as **Offset**. Offset can only be a maximum of the width of the page. So, formally, the **|Page Number|Offset|** combination is called as the **Logical Address**.
2. From the logical address, the page number is sent to the page table. The page table returns the frame number where the page was placed. Now, the combination **|Frame Number|Offset|** is generated (Offset is the same value that the CPU requested). This combination is called as **Physical Address**. The physical address is read by the main memory and now points to the respective offset (bit) of the respective page as requested by the CPU.
The method answers the question- why should the width of a page must be the same as that of the main memory or a frame. It is to ease the fetching of bits. If the widths were different, then the page table would be huge as it would have to store the location of every single bit of the process. 
So, if the size of the pages and the size of the frames were to be equal(as is the case), then storing of a process in the main memory and the CPU accessing it is a lot more efficient.

Side Note: 
**PTBR**- The Page Table Base Register. It points to the page numbers of the page table.

## Below is a diagramatic representation of a CPU requesting information of a certain page.
![[PagingDiagram.png]]


# Contents of the Code

The code is very primitive and internally documented. 

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.
