package kr.co.yeoeulsim.eatgo.interfaces;

import kr.co.yeoeulsim.eatgo.application.CategoryService;
import kr.co.yeoeulsim.eatgo.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<Category> list() {
        List<Category> categories = categoryService.getCategories();
        return categories;
    }

    @PostMapping("/category")
    public ResponseEntity<?> create(
            @RequestBody Category resource
    ) throws URISyntaxException {
        String name = resource.getName();
        categoryService.addCategory(name);
        String url = "/category/1";
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
