package graphs;

import java.util.*;


public class MovieStars
{
	private SymbolGraph sg;
	private Graph G;
	
	public MovieStars(SymbolGraph sg)
	{
		this.sg = sg;
		G = sg.G();
	}
	
	public void Show_star_movie(String name)
	{
		int index_name;
		if (sg.contains(name))
        {
        	System.out.print(name + " has played ");
        	index_name = sg.index(name);
        	Iterable<Integer> containBag = G.adj(index_name);
        	for (int i:containBag)
        	{
        		System.out.println(sg.name(i) + ", ");
        	}
        }
        else
        {
        	System.out.println("Cannot find the star");
        	return;
		}
	}
	
	public void Show_common_movies(String name1, String name2)
	{
		int index_name;
		if (sg.contains(name1))
        {
			TreeSet<Integer> colection= new TreeSet<>();
        	index_name = sg.index(name1);
        	Iterable<Integer> containBag = G.adj(index_name);
        	for (int i:containBag)
        	{
        		colection.add(i);
        	}
        	if (sg.contains(name2))
            {
            	index_name = sg.index(name2);
            	containBag = G.adj(index_name);
            	System.out.println(name1 + ", " + name2 + " have played ");
            	for (int i:containBag)
            	{
            		if (colection.contains(i))
            		{
            			System.out.println(sg.name(i) + ", ");
            		}
            	}
            }
            else
            {
            	System.out.println("Cannot find the star");
            	return;
    		}
        }
        else
        {
        	System.out.println("Cannot find the star");
        	return;
		}
	}
	
	public static void main(String[] args)
	{
		MovieStars stars = new MovieStars(new SymbolGraph("movies.txt", "/"));

        String name1 = new String("DiCaprio, Leonardo");
        String name2 = new String("Roberts, Julia (I)");
        String name3 = new String("Grant, Hugh (I)");
        
        stars.Show_star_movie(name1);
        System.out.println();
        stars.Show_star_movie(name2);
        System.out.println();
        stars.Show_star_movie(name3);
        System.out.println();
        stars.Show_common_movies(name2, name3);
        
	}
}
