package graphs;

import java.util.TreeSet;

/*************************************************************************
 *  Compilation:  javac SymbolGraph.java
 *  Execution:    java SymbolGraph filename.txt delimiter
 *  Dependencies: ST.java Graph.java In.java StdIn.java StdOut.java
 *  Data files:   http://algs4.cs.princeton.edu/41undirected/routes.txt
 *                http://algs4.cs.princeton.edu/41undirected/movies.txt
 *                http://algs4.cs.princeton.edu/41undirected/moviestiny.txt
 *                http://algs4.cs.princeton.edu/41undirected/moviesG.txt
 *                http://algs4.cs.princeton.edu/41undirected/moviestopGrossing.txt
 *  
 *  %  java SymbolGraph routes.txt " "
 *  JFK
 *     MCO
 *     ATL
 *     ORD
 *  LAX
 *     PHX
 *     LAS
 *
 *  % java SymbolGraph movies.txt "/"
 *  Tin Men (1987)
 *     Hershey, Barbara
 *     Geppi, Cindy
 *     Jones, Kathy (II)
 *     Herr, Marcia
 *     ...
 *     Blumenfeld, Alan
 *     DeBoy, David
 *  Bacon, Kevin
 *     Woodsman, The (2004)
 *     Wild Things (1998)
 *     Where the Truth Lies (2005)
 *     Tremors (1990)
 *     ...
 *     Apollo 13 (1995)
 *     Animal House (1978)
 *
 * 
 *  Assumes that input file is encoded using UTF-8.
 *  % iconv -f ISO-8859-1 -t UTF-8 movies-iso8859.txt > movies.txt
 *
 *************************************************************************/

/**
 *  The <tt>SymbolGraph</tt> class represents an undirected graph, where the
 *  vertex names are arbitrary strings.
 *  By providing mappings between string vertex names and integers,
 *  it serves as a wrapper around the
 *  {@link Graph} data type, which assumes the vertex names are integers
 *  between 0 and <em>V</em> - 1.
 *  It also supports initializing a symbol graph from a file.
 *  <p>
 *  This implementation uses an {@link ST} to map from strings to integers,
 *  an array to map from integers to strings, and a {@link Graph} to store
 *  the underlying graph.
 *  The <em>index</em> and <em>contains</em> operations take time 
 *  proportional to log <em>V</em>, where <em>V</em> is the number of vertices.
 *  The <em>name</em> operation takes constant time.
 *  <p>
 *  For additional documentation, see <a href="http://algs4.cs.princeton.edu/41undirected">Section 4.1</a> of
 *  <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class SymbolGraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Graph G;
    
    /**  
     * Initializes a graph from a file using the specified delimiter.
     * Each line in the file contains
     * the name of a vertex, followed by a list of the names
     * of the vertices adjacent to that vertex, separated by the delimiter.
     * @param filename the name of the file
     * @param delimiter the delimiter between fields
     */
    public SymbolGraph(String filename, String delimiter) {
        st = new ST<String, Integer>();
        
        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);
        // while (in.hasNextLine()) {
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }
        StdOut.println("Done reading " + filename);

        // inverted index to get string keys in an aray
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        G = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                G.addEdge(v, w);
            }
        }
    }

    /**
     * Does the graph contain the vertex named <tt>s</tt>?
     * @param s the name of a vertex
     * @return <tt>true</tt> if <tt>s</tt> is the name of a vertex, and <tt>false</tt> otherwise
     */
    public boolean contains(String s) {
        return st.contains(s);
    }

    /**
     * Returns the integer associated with the vertex named <tt>s</tt>.
     * @param s the name of a vertex
     * @return the integer (between 0 and <em>V</em> - 1) associated with the vertex named <tt>s</tt>
     */
    public int index(String s) {
        return st.get(s);
    }

    /**
     * Returns the name of the vertex associated with the integer <tt>v</tt>.
     * @param v the integer corresponding to a vertex (between 0 and <em>V</em> - 1) 
     * @return the name of the vertex associated with the integer <tt>v</tt>
     */
    public String name(int v) {
        return keys[v];
    }

    public int size() {
        return st.size();
    }
    /**
     * Returns the graph assoicated with the symbol graph. It is the client's responsibility
     * not to mutate the graph.
     * @return the graph associated with the symbol graph
     */
    public Graph G() {
        return G;
    }


    /**
     * Unit tests the <tt>SymbolGraph</tt> data type.
     */
//    public void Show_common_movies(String star1, String star2)
//	{
//		int index_name;
//		if (sg.contains(star1))
//        {
//			TreeSet<Integer> colection= new TreeSet<>();
//        		index_name = sg.index(star1);
//        		Iterable<Integer> containBag = G.adj(index_name);
//        		for (int i:containBag)
//        		{
//        		colection.add(i);
//        		}
//        		if (sg.contains(star2))
//            {
//        			index_name = sg.index(star2);
//        			containBag = G.adj(index_name);
//        			System.out.println(star1 + ", " + star2 + " have played ");
//        			for (int i:containBag)
//        			{
//        				if (colection.contains(i))
//        				{
//        					System.out.println(sg.name(i) + ", ");
//        				}
//        			}
//            }
//            else
//            {
//            		System.out.println("Cannot find the star");
//            		return;
//    			}
//        }
//        else
//        {
//        		System.out.println("Cannot find the star");
//        		return;
//		}
//	}
    
    public static void main(String[] args) {
        //String filename  = args[0];
        //String delimiter = args[1];
        //SymbolGraph sg = new SymbolGraph(filename, delimiter);
    	
    	 	
        
        SymbolGraph sg = new SymbolGraph("movies.txt", "/");
        Graph G = sg.G();
        while (StdIn.hasNextLine()) {
            String source = StdIn.readLine();
            if (source.equals("exit")) {
                StdOut.println("Bye...");
            	break;
            }
            if (sg.contains(source)) {
                int s = sg.index(source);
                for (int v : G.adj(s)) {
                    StdOut.println("   " + sg.name(v));
                }
            }
            else {
                StdOut.println("input does not contain '" + source + "'\n");
            }
            
        }
//        String star2 = new String("Roberts, Julia (I)");
//        String star3 = new String("Grant, Hugh (I)");
//        sg.Show_common_movies(star2, star3);
        //System.out.println(G.V());
 
      
        long start = System.nanoTime();
        CC cc = new CC(G);

        System.out.println("Total time is " + (System.nanoTime() - start));
        // number of connected components
        int M = cc.count();
        StdOut.println(M + " components");

        // compute list of vertices in each connected component
        Queue<String>[] components = (Queue<String>[]) new Queue[M];
        for (int i = 0; i < M; i++) {
            components[i] = new Queue<String>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[cc.id(v)].enqueue(sg.name(v));
        }

        // print results
        for (int i = 0; i < M; i++) {
            for (String v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }
        
    }
}
