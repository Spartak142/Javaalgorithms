
import java.util.ArrayList;
import java.util.List;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Sentence;
import se.kth.id1020.util.Word;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Hashing <Key, Value> {
    private int n;
    private int m=1024;           // size of linear probing table
    private Word[] keys;      // the keys
    //private Attributes[] vals; 
    private ArrayList <Attributes> [] vals; 
// the values
    public List<Document> listofDoc= new ArrayList <> ();
    
   public  Hashing(int capacity) {
        m = capacity;
        n = 0;
        keys = (Word[])   new Word[m];
        vals = (ArrayList<Attributes> []) new ArrayList  [m];
    }
private int hash(String key) {
        return (key.hashCode() & 0x7fffffff) % m;
    }
    private void resize(int capacity) {
        Hashing <Word, Attributes> temp = new Hashing <Word, Attributes>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                for (Attributes val : vals[i]) {
                temp.put(keys[i],val);               
                }                
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        m    = temp.m;
    }
    public List <Document> search (String k){
        if (!contains(k)){
            return listofDoc;}
        int i= hash(k);
        while (!k.equals(keys[i].word)){
            i=(i+1)%m;
        }
          for (Attributes a: vals[i]){
              boolean check=false;
             for(Document d: listofDoc){              
                 if (d.equals(a.document)){
                    check=true; 
                 }    
             }
             if (check==false)
              listofDoc.add(a.document);
          }  
        return listofDoc;  
        }
 public boolean contains(String key) {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m){
            if (keys[i].word.equals(key))
                return true;}    
        return false;
    }     
        public void put(Sentence set, Attributes att){
            for (Word w : set.getWords()){
                put (w, att);
            }
        }            
        private void put (Word w, Attributes att){
        if (n >= m/2) resize(2*m);
        int i;
        for (i = hash(w.word); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(w)) {
                vals[i].add(att);
                return;
            }
        }
        //System.out.println("Not found making new");
        keys[i] = w;
        vals[i]=new ArrayList<Attributes>();
        vals[i].add(att);
        n++;
    }
}


//Decided to add each word from the sentence separately becuase I couldnt come up with a search function to pull one word from a sentence and find it.