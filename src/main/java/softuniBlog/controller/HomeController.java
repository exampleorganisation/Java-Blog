package softuniBlog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import softuniBlog.entity.Article;
import softuniBlog.entity.Category;
import softuniBlog.repository.ArticleRepository;
import softuniBlog.repository.CategoryRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @GetMapping("/")
    public String index(Model model) {

        List<Category> categories = this.categoryRepository.findAll();

        List<Article> lastFourArticles = this.articleRepository.findAll();
        lastFourArticles = lastFourArticles.stream().sorted(Comparator.comparingInt(Article::getId)).collect(Collectors.toList());
        Collections.reverse(lastFourArticles);


        if(lastFourArticles.size() >= 6){
            lastFourArticles = lastFourArticles.subList(0, 6);

        }

        model.addAttribute("view", "home/index");
        model.addAttribute("categories", categories);
        model.addAttribute("articles", lastFourArticles);
        return "base-layout";

    }

    @RequestMapping("/error/403")
    public String accessDenied(Model model){
        model.addAttribute("view", "error/403");

        return "base-layout";
    }

    @GetMapping("/category/{id}")
    public String listArticles(Model model , @PathVariable Integer id){
        model.addAttribute("view", "home/list-articles");
        if(!this.categoryRepository.exists(id)){
            return "redirect:/";
        }

        List<Category> categories = this.categoryRepository.findAll();

        model.addAttribute("view", "home/list-articles");
        model.addAttribute("categories", categories);

        Category category = this.categoryRepository.findOne(id);
        Set<Article> articles = category.getArticles();

        model.addAttribute("articles", articles);
        model.addAttribute("category", category);
        return "base-layout";
    }


}
