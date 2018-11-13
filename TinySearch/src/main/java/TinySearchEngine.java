
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import se.kth.id1020.Driver;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;
import se.kth.id1020.TinySearchEngineBase;
public class TinySearchEngine implements TinySearchEngineBase{
public static void main(String[] args) throws Exception{
 TinySearchEngineBase searchEngine = new TinySearchEngine();
 Driver.run(searchEngine);
}
  TinySearchEngine() {
         //To change body of generated methods, choose Tools | Templates.
    }
  public List <Document> array= new ArrayList <> ();
  public static List <WordElements> array1= new ArrayList <> ();
  // V2.0 search changed from array of Strings to an array list
  public static List <String> search= new ArrayList <> ();
  public static List <String> searchCriteria= new ArrayList <> ();
  @Override
    public void insert(Word word, Attributes attr) { 
    WordElements addon = new WordElements(word, attr);      
    if ( array1.size()>= 2){ 
    binaryInsert (0, array1.size()-1, addon);}
    else if ( array1.size()== 1){
        if(array1.get(0).word.word.compareTo(addon.word.word)>0)
            array1.add(0,addon);
         else{
        array1.add(addon);
    }}         
    else{
        array1.add(addon);
    }
    }
    // routine to dinamically sort and place the words
    // Sort is done in the following order: alphabetical order of words, parts of speech, document and then occurence.
    // This means that the same word from the same document can come up at different place in the output due to POS
    //V2.0 count added on the same document
    public void binaryInsert ( int low, int high, WordElements addon){  
   int mid =low+(high-low)/2; 
   if (high<low){
      array1.add(mid, addon);
      return;
   }
   if(high>=low){
    if (array1.get(mid).word.word.equals(addon.word.word)){
                   array1.add(mid, addon);
                    return;}
    else if (array1.get(mid).word.word.compareTo(addon.word.word)<0){
       binaryInsert (mid+1,high, addon); 
   }
    else {
        binaryInsert (low,mid-1, addon);
    } 
  }
    }
//String query can be any number of words separated by signs '()=* .,?!+/-_:; or with a white space
    @Override
    public List<Document> search(String query) {
      // clearing the list from the previous search, comment out if you want to keep the results
      array.clear();  
      search.clear();
      searchCriteria.clear();
      querySplitter(query);
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
      //call to binary search for required words
      if (search.size()>1){
          for (int i=0; i<search.size(); i++){
          binarySearch (0,array1.size()-1,search.get(i));
          }
      }
      else if (search.size()==1){
           binarySearch (0,array1.size()-1, search.get(0));
      }
      else
          System.out.println("No search word given");
      System.out.println(search);
      System.out.println(searchCriteria);
      return array;
    }
 // string parcing routine
    public void querySplitter (String query){       
        String delims = "['()=* .,?!+/-_:;]";
        String[] arrayOfSearchWords=query.split(delims);
        for (int i =0; i<arrayOfSearchWords.length; i++){
            search.add(arrayOfSearchWords[i]);
        } 
    }
    //Binary search for search
    public void binarySearch( int lo, int hi,String s) {
        if (hi< lo){
        System.out.println("Word not found");
        return;}
        // V 2.0 previous formula for some reason resulted in missing soome of the results: mid=(lo+hi)/2
       int mid= lo+(hi-lo)/2;
          if (array1.get(mid).word.word.compareTo(s)<0){
              binarySearch(mid+1, hi, s);
          }
          else if (array1.get(mid).word.word.compareTo(s)>0){
               binarySearch(lo, mid-1, s);
          }
          else{
             System.out.println("Word successfully found");
              //array.add(array1.get(mid).attr.document);
             int finish= addingAllWordsRoutineAbove(mid,s);
             int start= addingAllWordsRoutineBelow(mid,s);
            if (searchCriteria.isEmpty()){
             for (int i=start; i<finish; i++)
                        array.add(array1.get(i).attr.document);   
            }    
            else{   
                orderBy (start,finish);  
                if (searchCriteria.get(1)== null){
                    searchCriteria.add( "asc");}
            switch (searchCriteria.get(1)) {
                case "asc": 
                    for (int i=start; i<finish; i++)
                        array.add(array1.get(i).attr.document);
                    break;
                case "desc":
                    for (int i=start; i<finish; i++)
                        array.add(0,array1.get(i).attr.document);
                    break;
                default:
                    System.out.println (" Please use asc or desc as command, default order is ascending");
                    for (int i=start; i<finish; i++)
                        array.add(array1.get(i).attr.document);
                    break;
            }}
              // V 2.0 for somne reason previous document-cancelling routine did not work
              array = new ArrayList<Document>(new LinkedHashSet<Document>(array));
              return;
          }
    }
    //Routine to add all documents from the found words since words are sorted by pos and attributes as well
    // and binary search finds only one of them.
    // V 2.0 if implemented with a for loop crreates stack overflow on common words
    public int addingAllWordsRoutineAbove(int start, String word){  
        int start1=start+1;
       // if (start1> array1.size()-1)
       //     return; 
        //To make sure documents are not repeated
        while (array1.get(start1).word.word.equals(word)){
           // array.add(array1.get(start1).attr.document);
            start1++;
        }     
            return start1;
    }
    // Second routine to add words around the found one 
   //V2.0 if implemented with a for loop crreates stack overflow on common words
    public int addingAllWordsRoutineBelow(int start, String word) {
        int start2=start-1;
        // if (start2<0)
          //  return;
         while (array1.get(start2).word.word.equals(word)){  
           // array.add(0,array1.get(start2).attr.document);
           start2--;
        }
         return start2+1;
           /* just a little print out to see which words exist 
            System.out.println(array1.get(50).word.word);
            System.out.println(array1.get(50000).word.word);
            System.out.println(array1.get(5000).word.word);
            System.out.println(array1.get(500).word.word);  
            System.out.println(array1.get(500000).word.word);
            System.out.println(array1.get(600000).word.word);
            System.out.println(array1.get(700000).word.word);
            System.out.println(array1.get(800000).word.word);
*/                 
    }  
   // V 2.0
    
    public void orderBy (int start, int finish){
        if (searchCriteria.isEmpty()){
            System.out.println("No searching criteria given");
        return;
        }       
    switch (searchCriteria.get(0)) {
        case "occurrence":
            System.out.println("doing occurence sort");
            documentSort(start,finish, new WordElements.whichOccurrence()); 
            break;
        case "popularity":
            System.out.println("doing popularity sort");
            documentSort(start,finish, new WordElements.whichPopularity());           
            break;
        case "count":
            System.out.println("doing count sort");
            documentSort(start,finish, new WordElements.whichCount());
            break;
        default:
            System.out.println("bad search criteria");
            break;
    }
        
    }  
    public void documentSort(int start,int finish, Comparator comp){   
         int R = finish - start;
         for (int i=start;i<finish-1;i++){
             for(int j=i+1; j<finish; j++)
             if (array1.get(i).attr.document.name.equals(array1.get(j).attr.document.name)){
                 array1.get(i).count++;
                 array1.get(j).count++;
             }
         }
         boolean swapped= true;   
         while (R > 0 && swapped == true ){
         swapped= false;
         for (int i = start ; i<finish-1; i++){
            if ( less(comp,array1.get(i),array1.get(i+1))){
               swapped= true;
               swap (i, i+1);
            }
        }
        R--;
    }
         System.out.println("Sorting accomplished");
}
    private static boolean less (Comparator comp, Object a, Object b){
        return comp.compare (a,b)>0;
    }
        public void  swap ( int a, int b){
        WordElements temp =array1.get(a);
        array1.set(a,array1.get(b));
        array1.set(b,temp); 
        System.out.println("Sorting and swap accomplished");  
    }
}

