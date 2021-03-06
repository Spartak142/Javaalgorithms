
import java.util.List;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

public interface TinySearchEngineBase {
    //Build the index
public void insert(Word word, Attributes attr);

//Searching
public List<Document> search(String query);

}
