package softuniBlog.bindingModel;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.constraints.NotNull;

public class ArticleBindingModel {

    private Integer categoryId;

    public Integer getCategoryId(){
        return categoryId;
    }

    public void setCategoryId(Integer categoryId){
        this.categoryId = categoryId;
    }

    @NotNull
    private String title;

    @NotNull
    private String Content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
