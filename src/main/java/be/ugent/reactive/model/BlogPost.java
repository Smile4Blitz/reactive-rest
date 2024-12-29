package be.ugent.reactive.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class BlogPost {
    @Id
    ObjectId id;
    String title;
    String content;

    public BlogPost() {
    }

    public BlogPost(ObjectId id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public ObjectId getId() {
        return this.id;
    }

    void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
