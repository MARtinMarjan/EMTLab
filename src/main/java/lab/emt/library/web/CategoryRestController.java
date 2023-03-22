package lab.emt.library.web;

import lab.emt.library.model.enumerations.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryRestController {
    @GetMapping("/all")
    public List<Category> listAllCategories() {
        return Arrays.asList(Category.values());
    }
}
