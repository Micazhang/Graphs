package graphs;


import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class B_tree {
	
	public static void Treeinsert(String[] s) throws IOException {
	    BinarySearchTree<String> t = new BinarySearchTree<>( );
	    
	    for( int i = 0; i < s.length; i++)
        {
            t.insert( s[i] );
        }

	    t.printTree();
	}
	
	public static void read_file(String[] l, String location) throws IOException {
        
		InputStream is = new FileInputStream(location);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        
        String lines = reader.readLine();  //read by lines
        
        int i = 0;
        
        while (lines != null) { 
        		l[i] = lines;
            i += 1;
            lines = reader.readLine(); 
        }
        
        reader.close();
        is.close();
    }
	
	public static void main(String[] args) throws IOException {

	    final int NUMS = 1000000;  
		args = new String[NUMS];
        long start = System.nanoTime();
        
		read_file(args,"src/graphs/RNA-seq-reads-1M.txt");
		Treeinsert(args);  
		
		StdOut.println("Time is "+ (System.nanoTime()-start));
	}

}
