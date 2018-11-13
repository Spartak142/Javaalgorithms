
import java.util.Comparator;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Word;
//v 2.0 added custom comparator implementations and count
public class WordElements{
    public final Word word; 
    public final Attributes attr; 
    public int count=1;
    
    WordElements(Word word, Attributes attr){
        this.word=word;
        this.attr=attr;
    }
    public static class whichPopularity implements Comparator <WordElements>{
        @Override
        public int compare(WordElements o1, WordElements o2) {
          int i=10;
            if (o1.attr.document.popularity<o2.attr.document.popularity)
               i=-10;
            if (o1.attr.document.popularity==o2.attr.document.popularity)
               i=0;
           return i;
        }  
    }
    public static class whichOccurrence implements Comparator <WordElements>{
        @Override
        public int compare(WordElements o1, WordElements o2) {
           int i=10;
            if (o1.attr.occurrence<o2.attr.occurrence)
               i=-10;
            if (o1.attr.occurrence==o2.attr.occurrence)
               i=0;
           return i;
        }   
    }
     public static class whichCount implements Comparator <WordElements>{
        @Override
        public int compare(WordElements o1, WordElements o2) {
           int i=10;
            if (o1.count<o2.count)
               i=-10;
            if (o1.count==o2.count)
               i=0;
           return i;
        }   
    }
    
}

