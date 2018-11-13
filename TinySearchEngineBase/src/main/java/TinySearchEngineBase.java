/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.List;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;
import se.kth.id1020.util.Sentence;

public interface TinySearchEngineBase {
    //Prepare to build the index
 public void preInserts();
//Build the index
 public void insert(Sentence sentence, Attributes attr);
 //Finish up building the index
 public void postInserts();
 //Searching
 public List<Document> search(String query);
 //Convert Prefix into Infix
 public String infix(String query);
}
