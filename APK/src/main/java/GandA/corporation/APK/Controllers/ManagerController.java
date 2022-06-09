package GandA.corporation.APK.Controllers;

import GandA.corporation.APK.model.Company;
import GandA.corporation.APK.model.User;
import GandA.corporation.APK.service.CompanyService;
import GandA.corporation.APK.service.UserService;
import GandA.corporation.APK.vilidator.CompanyValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ManagerController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private CompanyValidator companyValidator;

    @RequestMapping("/manager")
    public String viewHomePage(Model model) {
        List<Company> listCompany = companyService.listAll();
        model.addAttribute("listCompany", listCompany);

        return "manager";
    }

    @RequestMapping("/newCompany")
    public String showNewCompanyForm(Model model) {
        Company company = new Company();
        List<User> listUsers = userService.getUserWhereCompany_codeIsNull();

        model.addAttribute("company", company);
        model.addAttribute("listUsers", listUsers);

        return "new_company";
    }

    @RequestMapping(value = "/saveCompany", method = RequestMethod.POST)
    public String saveCompany(@ModelAttribute("company") Company company, @ModelAttribute("id") Long id, BindingResult bindingResult) {
        companyValidator.validate(company, bindingResult);

        if (bindingResult.hasErrors()) {
            return "redirect:/newCompany";
        }
        User userSave = userService.get(id);
        company.setId(null);
        companyService.save(company);
        userSave.setCompany_code(company.getCompany_code());
        userSave.setCompanyToUser(company);
        userService.saveNotPassword(userSave);

        return "redirect:/manager";
    }

    @RequestMapping("/editCompany/{id}")
    public ModelAndView showEditCompanyForm(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_company");

        Company company = companyService.get(id);
        mav.addObject("company", company);

        return mav;
    }

    @RequestMapping(value = "/editCompanySave/{id}", method = RequestMethod.POST)
    public String editCompanySave(@ModelAttribute("company") Company company,@PathVariable(name = "id") Long id) {

        Company companySave = companyService.get(id);
        companySave.setCompany_name(company.getCompany_name());
        companySave.setRegion(company.getRegion());
        companySave.setPhone(company.getPhone());
        companySave.setEmail(company.getEmail());
        companySave.setINN(company.getINN());
        companySave.setType_property(company.getType_property());
        companySave.setArea(company.getArea());
        companySave.setMain_product(company.getMain_product());
        companySave.setAmount_workers(company.getAmount_workers());
        companySave.setActive(company.isActive());
        companyService.save(companySave);

        return "redirect:/manager";
    }

    @RequestMapping("/addUserToCompany/{id}")
    public ModelAndView showAddUserToCompany(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("add_UserToCompany");
        List<User> listUsers = userService.getUserWhereCompany_codeIsNull();
        Company company = companyService.get(id);
        mav.addObject("listUsers", listUsers);
        mav.addObject("company", company);

        return mav;
    }

    @RequestMapping(value = "/addUserToCompanySave{idCompany}", method = RequestMethod.POST)
    public String addUserToCompanySave(@PathVariable(name = "idCompany") Long idCompany, @ModelAttribute("id") Long idUser) {
        Company company = companyService.get(idCompany);
        User user = userService.get(idUser);
        user.setCompany_code(company.getCompany_code());
        user.setCompanyToUser(company);
        userService.saveNotPassword(user);

        return "redirect:/manager";
    }

    @RequestMapping("/deleteUserToCompany/{id}")
    public ModelAndView showDeleteUserToCompany(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("delete_UserToCompany");

        Company company = companyService.get(id);
        User user = new User();
        List<User> listUsers = company.getUserCompany();
        mav.addObject("listUsers", listUsers);
        mav.addObject("user", user);

        return mav;
    }

    @RequestMapping(value = "/deleteSelectUserToCompany", method = RequestMethod.POST)
    public String deleteUserToCompany(@ModelAttribute("id") Long id) {
        User user = userService.get(id);
        user.setCompany_code(null);
        user.setCompanyToUser(null);
        userService.saveNotPassword(user);

        return "redirect:/manager";
    }

    @RequestMapping("/deleteCompany/{id}")
    public String deleteCompany(@PathVariable(name = "id") Long id) {
        Company company = companyService.get(id);
        List<User> users = company.getUserCompany();
        for(User user : users){
            user.setCompany_code(null);
            user.setCompanyToUser(null);
            userService.saveNotPassword(user);
        }
        companyService.delete(id);

        return "redirect:/manager";
    }
}
