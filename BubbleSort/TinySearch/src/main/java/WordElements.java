
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Word;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
   if(high>=low){
    if (array1.get(mid).word.word.equals(addon.word.word)){
        if (array1.get(mid).word.pos.equals(addon.word.pos)){
           if (array1.get(mid).attr.document.equals(addon.attr.document)){
               if (array1.get(mid).attr.occurrence== addon.attr.occurrence)
                   return;
               else{
                   array1.add(mid+1, addon);
                   }}
           else if(array1.get(mid).attr.document.compareTo(addon.attr.document)<0)
                   array1.add(mid+1, addon);
           else if (array1.get(mid).attr.document.compareTo(addon.attr.document)>0)        
                   array1.add(mid, addon);}
        else if (array1.get(mid).word.pos.compareTo(addon.word.pos)<0)
                   array1.add(mid+1, addon);
        else if (array1.get(mid).word.pos.compareTo(addon.word.pos)>0)        
                   array1.add(mid, addon);}
    else if (array1.get(mid).word.word.compareTo(addon.word.word)<0){
        binarySearchInsert (mid+1, high,addon);
    }
    else if (array1.get(mid).word.word.compareTo(addon.word.word)>0){
         binarySearchInsert (low, mid-1,addon);
    }
  }
   if (low>high && array1.get(mid).word.word.compareTo(addon.word.word)!=0 )
     array1.add(mid, addon); */
public class WordElements{
    public Word word; 
    public Attributes attr;  
    
    WordElements(Word word, Attributes attr){
        this.word=word;
        this.attr=attr;
    }
}
