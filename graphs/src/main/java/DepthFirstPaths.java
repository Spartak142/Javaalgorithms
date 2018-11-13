
import java.util.Stack;
import se.kth.id1020.Edge;
import se.kth.id1020.Graph;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author MVPIMP
 */
public class DepthFirstPaths {
    private boolean[] marked;    // marked[v] = is there an s-v path?
    private int[] edgeTo;        // edgeTo[v] = last edge on s-v path
    private int s; 
    
    
       public void DepthFirstPaths(Graph G, int s) {
        this.s=s;
        edgeTo = new int[G.numberOfVertices()];
        marked = new boolean[G.numberOfVertices()];
        validateVertex(s);
        dfs(G, s);
    }
       
    private void dfs(Graph G, int v) {
        
        marked[v] = true;
        
        for (Edge w : G.adj(v)) {            
            if (!marked[w.to]) {
                edgeTo[w.to] = v;
                dfs(G, w.to);
            }
        }
    }
    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
         }
    

       public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }
       
         private  void validateVertex(int v) {   
         int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
    // depth first search from v

    
}
