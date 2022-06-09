package GandA.corporation.APK.Controllers;

        import GandA.corporation.APK.model.Land;
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
public class LandController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private  UserService userService;

    @Autowired
    private LandService landService;

    @RequestMapping("/Land")
    public String viewLandPage(Model model) {

        if(companyService.AunHaveCompany()){
            System.out.println(companyService.AunHaveCompany());
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            System.out.println(companyService.AunCompanyIsActive());
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        if(userAunt.getCompanyToUser().getCompanyLand() != null) {
            List<Land> landList = userAunt.getCompanyToUser().getCompanyLand();
            model.addAttribute("landList", landList);
        }
        return "Land";
    }

    @RequestMapping("/newLand")
    public String showNewLandForm(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        Land land = new Land();

        model.addAttribute("land", land);

        return "new_Land";
    }

    @RequestMapping(value = "/saveLand", method = RequestMethod.POST)
    public String saveLand(@ModelAttribute("land") Land land) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();
        land.setCompanyToLand(userAunt.getCompanyToUser());;
        landService.save(land);

        return "redirect:/Land";
    }

    @RequestMapping("/editLand/{id}")
    public ModelAndView showEditLand(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_Land");

        if(companyService.AunHaveCompany()){
            mav = new ModelAndView("error_HasNoCompany");
        }
        if(companyService.AunCompanyIsActive()){
            mav = new ModelAndView("error_HasCompanyNoActive");
        }
        User userAunt = userService.getAuntUser();

        Land land = landService.get(id);
        mav.addObject("land", land);

        return mav;
    }

    @RequestMapping(value = "/editLandSave/{id}", method = RequestMethod.POST)
    public String editLandSave(@ModelAttribute("land") Land land,@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            System.out.println(companyService.AunHaveCompany());
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            System.out.println(companyService.AunCompanyIsActive());
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        Land landSave = landService.get(id);
        landSave.setCompanyToLand(userAunt.getCompanyToUser());
        landSave.setLocation(land.getLocation());
        landSave.setType_permitted_use(land.getType_permitted_use());
        landSave.setActual(land.getActual());
        landSave.setConditions(land.getConditions());
        landSave.setCrop_rotation_type(land.getCrop_rotation_type());
        landSave.setSquare(land.getSquare());
        landService.save(landSave);

        return "redirect:/Land";
    }

    @RequestMapping("/deleteLand/{id}")
    public String deleteCompany(@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            System.out.println(companyService.AunHaveCompany());
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            System.out.println(companyService.AunCompanyIsActive());
            return "error_HasCompanyNoActive";
        }

        landService.delete(id);

        return "redirect:/Land";
    }
}
