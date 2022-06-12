package GandA.corporation.APK.Controllers;

        import GandA.corporation.APK.model.Land;
        import GandA.corporation.APK.model.PlannedProduction;
        import GandA.corporation.APK.model.Product;
        import GandA.corporation.APK.model.User;
        import GandA.corporation.APK.service.*;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.ModelAttribute;
        import org.springframework.web.bind.annotation.PathVariable;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.servlet.ModelAndView;

        import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private PlannedProductionService plannedProductionService;

    @RequestMapping("/Product")
    public String viewProductPage(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        if(userAunt.getCompanyToUser().getCompanyLand() != null) {
            List<Product> productList = userAunt.getCompanyToUser().getCompanyProduct();
            model.addAttribute("productList", productList);
        }
        return "Product";
    }

    @RequestMapping("/newProduct")
    public String showNewProductForm(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }


        Product product = new Product();

        List<PlannedProduction> listPlannedProduction = plannedProductionService.listAll();

        model.addAttribute("listPlannedProduction", listPlannedProduction);
        model.addAttribute("product", product);

        return "new_Product";
    }

    @RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
    public String saveProduct(@ModelAttribute("product") Product product) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();
        product.setCompanyToProduct(userAunt.getCompanyToUser());
        //product.setPlannedProductionToProduct(plannedProductionService.get(id));
        //product.setId(null);
        productService.save(product);

        return "redirect:/Product";
    }

    @RequestMapping("/editProduct/{id}")
    public ModelAndView showEditProduct(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_Product");

        if(companyService.AunHaveCompany()){
            mav = new ModelAndView("error_HasNoCompany");
        }
        if(companyService.AunCompanyIsActive()){
            mav = new ModelAndView("error_HasCompanyNoActive");
        }


        Product product = productService.get(id);
        mav.addObject("product", product);

        return mav;
    }

    @RequestMapping(value = "/editProductSave/{id}", method = RequestMethod.POST)
    public String editProductSave(@ModelAttribute("product") Product product,@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        Product productSave = productService.get(id);
        productSave.setCompanyToProduct(userAunt.getCompanyToUser());
        productSave.setProduct_type(product.getProduct_type());
        productSave.setDate_manufacture(product.getDate_manufacture());
        productSave.setUnit(product.getProduct_type());
        productSave.setAmount(product.getAmount());
        productSave.setProduction_cost(product.getProduction_cost());
        productSave.setCost_price(product.getCost_price());
        productService.save(productSave);

        return "redirect:/Product";
    }

    @RequestMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }

        productService.delete(id);

        return "redirect:/Product";
    }
}
