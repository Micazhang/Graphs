package graphs;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

public class Q5_Sorts {

	private static int length;	//get the length of each line
	
	// read the file
	public static void read_file(String[] l, String location) throws IOException {
        
		InputStream is = new FileInputStream(location);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        
        String lines = reader.readLine();  //read by lines
        length = lines.length();
        int i = 0;
        
        while (lines != null) { 
        		l[i] = lines;
            i += 1;
            lines = reader.readLine(); 
        }
        reader.close();
        is.close();
    }
	//write the file
	public static void write_file(String[] l) throws IOException {
		//create a new file to store sorted file
		File file = new File("src/graphs/RNA-seq-reads-1M-sorted.txt");
		file.createNewFile();
		
		BufferedWriter writer = new BufferedWriter(new FileWriter(file));
		
		for(int j = 0; j < l.length; j++) {
			writer.append(l[j]);
			writer.append("\n");
		}
		writer.flush();
		writer.close();
	}
	
	public static void main(String[] args) throws IOException  {
	    
		final int NUMS = 1000000;  
		String[] list = new String[NUMS];
        long start = System.nanoTime();
        
		read_file(list,"src/graphs/RNA-seq-reads-1M.txt");
		RadixSort.radixSortA(list, length);
		write_file(list);
       
        StdOut.println("Time is "+ (System.nanoTime()-start));
	}

}
