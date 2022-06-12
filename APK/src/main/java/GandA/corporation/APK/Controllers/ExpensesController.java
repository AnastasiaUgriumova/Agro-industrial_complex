package GandA.corporation.APK.Controllers;

        import GandA.corporation.APK.model.Expenses;
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
public class ExpensesController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private UserService userService;

    @Autowired
    private ExpensesService expensesService;

    @RequestMapping("/Expenses")
    public String viewExpensesPage(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        if(userAunt.getCompanyToUser().getCompanyExpenses() != null) {
            List<Expenses> expensesList = userAunt.getCompanyToUser().getCompanyExpenses();
            model.addAttribute("expensesList", expensesList);
        }
        return "Expenses";
    }

    @RequestMapping("/newExpenses")
    public String showNewExpensesForm(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }


        Expenses expenses = new Expenses();

        model.addAttribute("expenses", expenses);

        return "new_Expenses";
    }

    @RequestMapping(value = "/saveExpenses", method = RequestMethod.POST)
    public String saveExpenses(@ModelAttribute("land") Expenses expenses) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();
        expenses.setCompanyToExpenses(userAunt.getCompanyToUser());;
        expensesService.save(expenses);

        return "redirect:/Expenses";
    }

    @RequestMapping("/editExpenses/{id}")
    public ModelAndView showEditExpenses(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_Expenses");

        if(companyService.AunHaveCompany()){
            mav = new ModelAndView("error_HasNoCompany");
        }
        if(companyService.AunCompanyIsActive()){
            mav = new ModelAndView("error_HasCompanyNoActive");
        }


        Expenses expenses = expensesService.get(id);
        mav.addObject("expenses", expenses);

        return mav;
    }

    @RequestMapping(value = "/editExpensesSave/{id}", method = RequestMethod.POST)
    public String editExpensesSave(@ModelAttribute("expenses") Expenses expenses,@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        Expenses expensesSave = expensesService.get(id);
        expensesSave.setCompanyToExpenses(userAunt.getCompanyToUser());
        expensesSave.setExpenses_date(expenses.getExpenses_date());
        expensesSave.setName_purchase(expenses.getName_purchase());
        expensesSave.setPrice(expenses.getPrice());
        expensesSave.setProduct_type(expenses.getProduct_type());

        expensesService.save(expensesSave);

        return "redirect:/Expenses";
    }

    @RequestMapping("/deleteExpenses/{id}")
    public String deleteExpenses(@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }

        expensesService.delete(id);

        return "redirect:/Expenses";
    }
}
