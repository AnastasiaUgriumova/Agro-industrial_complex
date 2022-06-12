package GandA.corporation.APK.Controllers;

        import GandA.corporation.APK.model.Land;
        import GandA.corporation.APK.model.Procurement;
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
public class ProcurementController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProcurementService procurementService;

    @RequestMapping("/Procurement")
    public String viewProcurementPage(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        if(userAunt.getCompanyToUser().getCompanyProcurement() != null) {
            List<Procurement> procurementList = userAunt.getCompanyToUser().getCompanyProcurement();
            model.addAttribute("procurementList", procurementList);
        }
        return "Procurement";
    }

    @RequestMapping("/newProcurement")
    public String showNewProcurementForm(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }


        Procurement procurement = new Procurement();

        model.addAttribute("procurement", procurement);

        return "new_Procurement";
    }

    @RequestMapping(value = "/saveProcurement", method = RequestMethod.POST)
    public String saveProcurement(@ModelAttribute("procurement") Procurement procurement) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();
        procurement.setCompanyToProcurement(userAunt.getCompanyToUser());;
        procurementService.save(procurement);

        return "redirect:/Procurement";
    }

    @RequestMapping("/editProcurement/{id}")
    public ModelAndView showEditProcurement(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_Procurement");

        if(companyService.AunHaveCompany()){
            mav = new ModelAndView("error_HasNoCompany");
        }
        if(companyService.AunCompanyIsActive()){
            mav = new ModelAndView("error_HasCompanyNoActive");
        }


        Procurement procurement = procurementService.get(id);
        mav.addObject("procurement", procurement);

        return mav;
    }

    @RequestMapping(value = "/editProcurementSave/{id}", method = RequestMethod.POST)
    public String editProcurementSave(@ModelAttribute("procurement") Procurement procurement,@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        Procurement procurementSave = procurementService.get(id);
        procurementSave.setCompanyToProcurement(userAunt.getCompanyToUser());
        procurementSave.setPurchase_date(procurement.getPurchase_date());
        procurementSave.setName_purchase(procurement.getName_purchase());
        procurementSave.setPrice(procurement.getPrice());
        procurementSave.setProduct_type(procurement.getProduct_type());
        procurementSave.setUnit(procurement.getUnit());
        procurementSave.setAmount(procurement.getAmount());

        procurementService.save(procurementSave);

        return "redirect:/Procurement";
    }

    @RequestMapping("/deleteProcurement/{id}")
    public String deleteProcurement(@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }

        procurementService.delete(id);

        return "redirect:/Procurement";
    }
}