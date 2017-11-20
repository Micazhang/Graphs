# Graphs


1. Consider the graph stored in largeDG.txt (download it from Resources). Run DFS on that graph and show the vertices of the graph in pre-order and post-order. Compute the CPU time and report the worst-case complexity of DFS.
2. Consider the graph stored in largeEWG.txt (download it from Resources).
a. Write a program that finds the shortest path for all pairs of nodes (you choose the algorithm). Calculate the CPU time and report the complexity of the algorithm you chose.
b. Write a program that finds the MST (you choose the algorithm). Calculate the CPU time and compare it with the complexity of the algorithm you chose.
3. Consider the movie database stored in movie.txt, and SymbolGraph.java. Write a program that uses DFS to find all connected components. Use CC.java as a template. Show the CPU time and report the worst-case complexity of DFS.
4. Write a program that finds the movies starred by a particular actor. Show the movies starred by Leonardo DiCaprio. Show the movies starred by Julia Roberts, by Hugh Grant, and by both of them.
5. Consider the one million Chip-seq reads given in the files called “Chip-seq-reads-1M.dat”. Write a program that partitions the list of reads into 4 sublists. Save each sublist in a separate file (called A.dat, B.dat, C.dat, and D.dat). Sort each sublist and store it in a file (AS.dat, BS.dat, CS.dat, DS.dat). Take the 4 sorted sublists from the files and merge them in to a sorted list. Store the sorted list in a file (called “Chip-seq-reads-1M-sorted.dat”).
6. Create a B-tree and insert all the reads from the original list (Chip-seq-reads-1M.dat) as they appear in the file. List the B-tree in in-order traversal and save the output all keys in a file (called B-tree.dat).
7. Record total CPU times for #5 and #6. Comment on the obtained CPU times and compare them with the corresponding complexities as discussed in class.
