package GandA.corporation.APK.Controllers;

        import GandA.corporation.APK.model.Land;
        import GandA.corporation.APK.model.Sale;
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
public class SaleController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private SaleService saleService;

    @RequestMapping("/Sale")
    public String viewSalePage(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        if(userAunt.getCompanyToUser().getCompanySale() != null) {
            List<Sale> saleList = userAunt.getCompanyToUser().getCompanySale();
            model.addAttribute("saleList", saleList);
        }
        return "Sale";
    }

    @RequestMapping("/newSale")
    public String showNewSaleForm(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }


        Sale sale = new Sale();

        model.addAttribute("sale", sale);

        return "new_Sale";
    }

    @RequestMapping(value = "/saveSale", method = RequestMethod.POST)
    public String saveSale(@ModelAttribute("sale") Sale sale) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();
        sale.setCompanyToSale(userAunt.getCompanyToUser());;
        saleService.save(sale);

        return "redirect:/Sale";
    }

    @RequestMapping("/editSale/{id}")
    public ModelAndView showEditLand(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_Sale");

        if(companyService.AunHaveCompany()){
            mav = new ModelAndView("error_HasNoCompany");
        }
        if(companyService.AunCompanyIsActive()){
            mav = new ModelAndView("error_HasCompanyNoActive");
        }


        Sale sale = saleService.get(id);
        mav.addObject("sale", sale);

        return mav;
    }

    @RequestMapping(value = "/editSaleSave/{id}", method = RequestMethod.POST)
    public String editLandSave(@ModelAttribute("sale") Sale sale,@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        Sale saleSave = saleService.get(id);
        saleSave.setCompanyToSale(userAunt.getCompanyToUser());
        saleSave.setProductType(sale.getProductType());
        saleSave.setDateSale(sale.getDateSale());
        saleSave.setUnit(sale.getUnit());
        saleSave.setAmount(sale.getAmount());
        saleSave.setRevenue(sale.getRevenue());
        saleSave.setProfit(sale.getProfit());

        saleService.save(saleSave);

        return "redirect:/Sale";
    }

    @RequestMapping("/deleteSale/{id}")
    public String deleteSale(@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }

        saleService.delete(id);

        return "redirect:/Sale";
    }
}
