/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;
import se.kth.id1020.Driver;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Sentence;
        
public class TinySearchEngine implements TinySearchEngineBase {
    public Hashing hashFunc= new Hashing(1024);
    public List<Document> listOfDocuments= new ArrayList <> ();
    public static List <String> searchCriteria= new ArrayList <> ();
    public static List <String> search= new ArrayList <> ();
   
    public static void main(String[] args) throws Exception{
 TinySearchEngineBase searchEngine = new TinySearchEngine();
 
 Driver.run(searchEngine);
 }

    @Override
    public void preInserts() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insert(Sentence sntnc, Attributes atrbts) {
   hashFunc.put(sntnc, atrbts);
    }

    @Override
    public void postInserts() {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Document> search(String string) { 
      listOfDocuments.clear();  
      search.clear();
      searchCriteria.clear();
       
      querySplitter(string);
       
       if (search.size()>1){
          for (int i=0; i<search.size(); i++){     
             if (search.get(i).equals("orderby")){
                 search.remove(i);
                   while (i <search.size()){       
                   searchCriteria.add(search.get(i));
                   search.remove(i);
                 } 
            }
          }}
       if (search.size()>1){
          for (int i=0; i<search.size(); i++){
           listOfDocuments= hashFunc.search(search.get(i));
          }
      }
            else if (search.size()==1){
            listOfDocuments= hashFunc.search(string);
      }
      else
          System.out.println("No search word given");
      System.out.println(search);
      System.out.println(searchCriteria);  
     
        return listOfDocuments;
    }

    @Override
    public String infix(String string) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    String s= "abcd";
    return s;
    }
    
  
 // string parcing routine
    public void querySplitter (String query){       
        String delims = "+-!";
        String[] arrayOfSearchWords=query.split(delims);
        for (int i =0; i<arrayOfSearchWords.length; i++){
            search.add(arrayOfSearchWords[i]);
        } 
    }
    //
}
