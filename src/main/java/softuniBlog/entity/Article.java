package softuniBlog.entity;
import javax.persistence.*;

@Entity
@Table(name = "articles")
public class Article {
    private Integer id;

    private String title;

    private String content;

    private User author;

    public Article(String title, String content, User author,Category category) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.category = category;
    }

    public Article() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(columnDefinition = "text", nullable = false)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(columnDefinition = "text" , nullable = false)
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @ManyToOne
    @JoinColumn(nullable = false, name = "authorId")
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Transient
    public String getSummary(){
        if(this.getContent().length() < 25) {
            return this.getContent();
        }
        else {
            return this.getContent().substring(0,25) + "...";
        }

    }

    private  Category category;
    @ManyToOne()
    @JoinColumn(nullable = false, name = "categoryId")
    public Category getCategory(){
        return category;
    }
    public void setCategory(Category category){
      this.category = category;
    }

}
