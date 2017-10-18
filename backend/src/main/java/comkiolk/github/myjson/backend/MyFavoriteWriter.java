package comkiolk.github.myjson.backend;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

@Entity
public class MyFavoriteWriter {

    @Id
    Long id;
    String name;
    String sentences;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSentences() {
        return sentences;
    }

    public void setId(Long pId) {
        id = pId;
    }

    public void setName(String pName) {
        name = pName;
    }

    public void setSentences(String pSentences) {
        sentences = pSentences;
    }
}
