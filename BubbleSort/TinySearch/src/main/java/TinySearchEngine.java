
import java.util.ArrayList;
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
 System.out.println(array1.get(50));
 System.out.println(array1.get(50));
 
}
  TinySearchEngine() {
         //To change body of generated methods, choose Tools | Templates.
    }
  
  public List <Document> array= new ArrayList <> ();
  public static List <WordElements> array1= new ArrayList <> ();

  @Override
    public void insert(Word word, Attributes attr) {
    int size;
    int low=0; 
    WordElements addon = new WordElements(word, attr);
  
       
    if ( array1.size()>= 2){ 
         size= array1.size()-1;
    binaryInsert (low, size, addon);}
    else if ( array1.size()== 1){
        if(array1.get(0).word.word.compareTo(addon.word.word)>0)
            array1.add(0,addon);
         else{
        array1.add(addon);
    }
    }         
    else{
        array1.add(addon);
    }
    }

    // Sort is done in the following order: alphabetical order of words, parts of speech, document and then occurence.
    // This means that the same word from the same document can come up at different place in the output due to POS
    public void binaryInsert ( int low, int high, WordElements addon){  
   int mid =(low+high)/2; 
   if (high<low){
      array1.add(mid, addon);
      return;
   }
   if(high>=low){
    if (array1.get(mid).word.word.equals(addon.word.word)){      
        if (array1.get(mid).word.pos.equals(addon.word.pos)){
           if (array1.get(mid).attr.document.equals(addon.attr.document)){
               if (array1.get(mid).attr.occurrence== addon.attr.occurrence)
                   return; //same word
               else if (array1.get(mid).attr.occurrence < addon.attr.occurrence){
                   array1.add(mid+1, addon);
                   }
                else {
                   array1.add(mid, addon);
                   }}
           else if(array1.get(mid).attr.document.compareTo(addon.attr.document)<0)
                   array1.add(mid+1, addon);
           else         
                   array1.add(mid, addon);}
        else if (array1.get(mid).word.pos.compareTo(addon.word.pos)<0)
                   array1.add(mid+1, addon);
        else        
                   array1.add(mid, addon);}
    else if (array1.get(mid).word.word.compareTo(addon.word.word)<0){
       binaryInsert (mid+1,high, addon);
   }
    else {
        binaryInsert (low,mid-1, addon);
    } 
  }
    }

    @Override
    public List<Document> search(String query) {

      String [] search= querySplitter(query);
      if (search.length>1){
                int n= 0;
      int k =array1.size()-1;
          for (int i=0; i<=search.length-1;i++){
          binarySearch (n,k,search[i]);
          }
      }
      else{
                int n= 0;
      int k =array1.size()-1;
      binarySearch (n, k, query);
      }
      return array;
    }
 
    public String[] querySplitter (String query){       
        String delims = "['()=* .,?!+/-_:;]";
        String[] arrayOfSearchWords=query.split(delims);
        return arrayOfSearchWords;
    }
    
    private void binarySearch( int lo, int hi,String s) {
        if (hi<= lo){
        System.out.println("Word not found");
        return;}
       int mid= (lo+hi)/2;
          if (array1.get(mid).word.word.compareTo(s)<0){
              binarySearch(mid+1, hi, s);
          }
          else if (array1.get(mid).word.word.compareTo(s)>0){
               binarySearch(lo, mid-1, s);
          }
          else{
              array.add(array1.get(mid).attr.document);
              addingAllWordsRoutineAbove(mid,s);
              addingAllWordsRoutineBelow(mid,s);
              return;
          }
    }
    public void addingAllWordsRoutineAbove(int start, String word){
        int start1=start+1;
        if (start1> array1.size()-1)
            return; 
        if (array1.get(start1).word.word.equals(word)){
            array.add(array1.get(start1).attr.document);
            addingAllWordsRoutineAbove(start1,word);
        }
        else 
            return;
    }

    private void addingAllWordsRoutineBelow(int start, String word) {
        int start2=start-1;
         if (start2<0)
            return;
         if (array1.get(start2).word.word.equals(word)){            
            array.add(0,array1.get(start2).attr.document);
            addingAllWordsRoutineBelow(start2,word);
        }
         else{
            System.out.println(array1.get(50).word.word);
            System.out.println(array1.get(50000).word.word);
            System.out.println(array1.get(5000).word.word);
            System.out.println(array1.get(500).word.word);  
            System.out.println(array1.get(500000).word.word);
            System.out.println(array1.get(600000).word.word);
            System.out.println(array1.get(700000).word.word);
            System.out.println(array1.get(800000).word.word);

              return;
         }
             
            
    }  
}

    
    /*public void insertionSort (int i, WordElements element){
        
         if ( array1.size()== 1){
        if(array1.get(0).word.word.compareTo(addon.word.word)>0)
            array1.add(0,addon);
        else{
        array1.add(addon);
    } 
     }
       if( array1.size()== 0){
           array1.add(addon);
       }
    int i = array1.size()-2;
    insertionSort(i,addon);
    ???????????????????????????????????????????????????????????????????????????????????
        if ( i >= 0) {
                if (array1.get(i).word.word.compareTo(element.word.word)>0&& i>0){
                  i--;  
                  insertionSort(i,element);
                }
                else if (array1.get(i).word.word.compareTo(element.word.word)>0&& i==0){
                    array1.add(0, element);
                  return;}
                else if (array1.get(i).word.word.compareTo(element.word.word)<0&& i==0){
                    array1.add(1, element);
                  return;}
                  else if (array1.get(i).word.word.compareTo(element.word.word)<0&& i>0){
                  array1.add(i+1, element);
                  return;
                          }
    }
        
    }*/