/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import edu.princeton.cs.algs4.DepthFirstOrder;
import java.util.Arrays;
import se.kth.id1020.Graph;
import se.kth.id1020.DataSource;
import se.kth.id1020.Vertex;
import java.util.Iterator;
import java.util.Stack;
import se.kth.id1020.Edge;


public class Paths {
    private static boolean[] marked;
     public static void main(String[] args) {
 Graph g = DataSource.load();
 // work on g
int count=0;
System.out.println(g.vertex(1));
   System.out.println (recursiveMarking (g, 0, count));
    

  
 }   
      public static int recursiveMarking (Graph g, int i, int count){
          marked=new boolean[g.numberOfVertices()];
          for (int j=0; j<g.numberOfVertices(); j++){
              if (marked[j]==false)
              checkC(g,j);
              count++;
          }
           
          return count;
      }
              
               
 public static void checkC (Graph g, int i){
     marked[i]=true;
// starting point
    for (Iterator <Edge> b=g.adj(i).iterator();b.hasNext();){
      if (marked[g.adj(i).iterator().next().to]==true)
          return;
      else{
        marked[g.adj(i).iterator().next().to]=true;
        i=g.adj(i).iterator().next().to; } 
    }
    // g.adj(i).iterator().next().to;
     
 }     
     

}
               
               
               
      /*  DepthFirstPaths dfs = new DepthFirstPaths();    
      int s=0;
      dfs.DepthFirstPaths(g, s);
        for (int v = 0; v < g.numberOfVertices(); v++) {
            if (dfs.hasPathTo(v)) {
                
                for (int x : dfs.pathTo(v)) {
                    if (x == s)
                        System.out.println(x);
                    
                    else        System.out.println("-" + x);
                }
                System.out.println();
               
            }

            else {
               System.out.println("%d to %d:  not connected\n"+ s+ v);
               count++;
            }

        }


  System.out.println(g);System.out.println(count);
*/













      
      
      
      
      
      
  /*    
   private static void dfs(Graph G, int v) {
        marked[v] = true;
       if (G.adj(v).iterator().hasNext()==true) {
            if(marked[G.adj(v).iterator().next().from]==false){
                v=G.adj(v).iterator().next().from;
                System.out.println("hi");
                dfs(G, v);
            }
            else{
                System.out.println("hi1");
            return;
            }
        }
    }
 

    // depth first search from v

    */


/*for (int i=0; i<g.numberOfVertices(); i++){
   if (marked[i]== false  ){
       depthFirstPaths(g,i);
   count++;
   System.out.println(count);}
}*/