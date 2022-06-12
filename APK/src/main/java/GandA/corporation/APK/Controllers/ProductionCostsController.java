package GandA.corporation.APK.Controllers;

        import GandA.corporation.APK.model.Land;
        import GandA.corporation.APK.model.ProductionCosts;
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
public class ProductionCostsController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private  ProductionCostsService  productionCostsService;

    @RequestMapping("/ProductionCosts")
    public String viewProductionCostsPage(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        if(userAunt.getCompanyToUser().getCompanyProductionCosts() != null) {
            List<ProductionCosts> productionCostsList = userAunt.getCompanyToUser().getCompanyProductionCosts();
            model.addAttribute("productionCostsList", productionCostsList);
        }
        return "ProductionCosts";
    }

    @RequestMapping("/newProductionCosts")
    public String showNewProductionCostsForm(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }

        ProductionCosts productionCosts = new ProductionCosts();

        model.addAttribute("productionCosts", productionCosts);

        return "new_ProductionCosts";
    }

    @RequestMapping(value = "/saveProductionCosts", method = RequestMethod.POST)
    public String saveProductionCosts(@ModelAttribute("productionCosts") ProductionCosts productionCosts) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();
        productionCosts.setCompanyToProductionCosts(userAunt.getCompanyToUser());;
        productionCostsService.save(productionCosts);

        return "redirect:/ProductionCosts";
    }

    @RequestMapping("/editProductionCosts/{id}")
    public ModelAndView showEditProductionCosts(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_ProductionCosts");

        if(companyService.AunHaveCompany()){
            mav = new ModelAndView("error_HasNoCompany");
        }
        if(companyService.AunCompanyIsActive()){
            mav = new ModelAndView("error_HasCompanyNoActive");
        }


        ProductionCosts productionCosts = productionCostsService.get(id);
        mav.addObject("productionCosts", productionCosts);

        return mav;
    }

    @RequestMapping(value = "/editProductionCostsSave/{id}", method = RequestMethod.POST)
    public String editProductionCostsSave(@ModelAttribute("productionCosts") ProductionCosts productionCosts,@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        ProductionCosts productionCostsSave = productionCostsService.get(id);
        productionCostsSave.setCompanyToProductionCosts(userAunt.getCompanyToUser());
        productionCostsSave.setProduct_type(productionCosts.getProduct_type());
        productionCostsSave.setSalary(productionCosts.getSalary());
        productionCostsSave.setMaterial_costs(productionCosts.getMaterial_costs());
        productionCostsSave.setDepreciation_costs(productionCosts.getDepreciation_costs());
        productionCostsSave.setOther_costs(productionCosts.getOther_costs());
        productionCostsService.save(productionCostsSave);

        return "redirect:/ProductionCosts";
    }

    @RequestMapping("/deleteProductionCosts/{id}")
    public String deleteProductionCosts(@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }

        productionCostsService.delete(id);

        return "redirect:/ProductionCosts";
    }
}
