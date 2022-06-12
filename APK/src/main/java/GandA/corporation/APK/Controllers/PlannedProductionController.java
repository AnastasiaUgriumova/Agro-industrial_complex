package GandA.corporation.APK.Controllers;

        import GandA.corporation.APK.model.Land;
        import GandA.corporation.APK.model.PlannedProduction;
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
public class PlannedProductionController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private PlannedProductionService plannedProductionService;

    @RequestMapping("/PlannedProduction")
    public String viewPlannedProductionPage(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        if(userAunt.getCompanyToUser().getCompanyPlannedProduction() != null) {
            List<PlannedProduction> plannedProductionList = userAunt.getCompanyToUser().getCompanyPlannedProduction();
            model.addAttribute("plannedProductionList", plannedProductionList);
        }
        return "PlannedProduction";
    }

    @RequestMapping("/newPlannedProduction")
    public String showNewPlannedProductionForm(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }


        PlannedProduction plannedProduction = new PlannedProduction();

        model.addAttribute("plannedProduction", plannedProduction);

        return "new_PlannedProduction";
    }

    @RequestMapping(value = "/savePlannedProduction", method = RequestMethod.POST)
    public String savePlannedProduction(@ModelAttribute("plannedProduction") PlannedProduction plannedProduction) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();
        plannedProduction.setCompanyToPlannedProduction(userAunt.getCompanyToUser());;
        plannedProductionService.save(plannedProduction);

        return "redirect:/PlannedProduction";
    }

    @RequestMapping("/editPlannedProduction/{id}")
    public ModelAndView showEditPlannedProduction(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_PlannedProduction");

        if(companyService.AunHaveCompany()){
            mav = new ModelAndView("error_HasNoCompany");
        }
        if(companyService.AunCompanyIsActive()){
            mav = new ModelAndView("error_HasCompanyNoActive");
        }

        PlannedProduction plannedProduction = plannedProductionService.get(id);
        mav.addObject("plannedProduction", plannedProduction);

        return mav;
    }

    @RequestMapping(value = "/editPlannedProductionSave/{id}", method = RequestMethod.POST)
    public String editPlannedProductionSave(@ModelAttribute("plannedProduction") PlannedProduction plannedProduction,@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        PlannedProduction plannedProductionSave = plannedProductionService.get(id);
        plannedProductionSave.setCompanyToPlannedProduction(userAunt.getCompanyToUser());
        plannedProductionSave.setProduct_type(plannedProduction.getProduct_type());
        plannedProductionSave.setProduct_name(plannedProduction.getProduct_name());
        plannedProductionSave.setPlanned_property(plannedProduction.getPlanned_property());
        plannedProductionSave.setPlanned_revenue(plannedProduction.getPlanned_revenue());
        plannedProductionSave.setPlanned_profit(plannedProduction.getPlanned_profit());

        plannedProductionService.save(plannedProductionSave);

        return "redirect:/PlannedProduction";
    }

    @RequestMapping("/deletePlannedProduction/{id}")
    public String deletePlannedProduction(@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }

        plannedProductionService.delete(id);

        return "redirect:/PlannedProduction";
    }
}
