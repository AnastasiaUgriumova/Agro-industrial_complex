package GandA.corporation.APK.Controllers;

import GandA.corporation.APK.model.Employee;
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
public class EmployeeController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private  UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/Employee")
    public String viewEmployeePage(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        if(userAunt.getCompanyToUser().getCompanyEmployee() != null) {
            List<Employee> employeeList = userAunt.getCompanyToUser().getCompanyEmployee();
            model.addAttribute("employeeList", employeeList);
        }
        return "Employee";
    }

    @RequestMapping("/newEmployee")
    public String showNewEmployeeForm(Model model) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }

        Employee employee = new Employee();

        model.addAttribute("employee", employee);

        return "new_Employee";
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    public String saveLand(@ModelAttribute("employee") Employee employee) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();
        employee.setCompanyToEmployee(userAunt.getCompanyToUser());;
        employeeService.save(employee);

        return "redirect:/Employee";
    }

    @RequestMapping("/editEmployee/{id}")
    public ModelAndView showEditLand(@PathVariable(name = "id") Long id) {
        ModelAndView mav = new ModelAndView("edit_Employee");

        if(companyService.AunHaveCompany()){
            mav = new ModelAndView("error_HasNoCompany");
        }
        if(companyService.AunCompanyIsActive()){
            mav = new ModelAndView("error_HasCompanyNoActive");
        }

        Employee employee = employeeService.get(id);
        mav.addObject("employee", employee);

        return mav;
    }

    @RequestMapping(value = "/editEmployeeSave/{id}", method = RequestMethod.POST)
    public String editLandSave(@ModelAttribute("employee") Employee employee,@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }
        User userAunt = userService.getAuntUser();

        Employee employeeSave = employeeService.get(id);
        employeeSave.setCompanyToEmployee(userAunt.getCompanyToUser());
        employeeSave.setSurname(employee.getSurname());
        employeeSave.setUsername(employee.getUsername());
        employeeSave.setPatronymic(employee.getPatronymic());
        employeeSave.setGender(employee.getGender());
        employeeSave.setAge(employee.getAge());
        employeeSave.setSpeciality(employee.getSpeciality());
        employeeSave.setTotal_work_experience(employee.getTotal_work_experience());
        employeeSave.setCompany_work_experience(employee.getCompany_work_experience());
        employeeSave.setJob_title(employee.getJob_title());
        employeeSave.setPhone(employee.getPhone());
        employeeSave.setEmail(employee.getEmail());
        employeeSave.setSalary(employee.getSalary());

        employeeService.save(employeeSave);

        return "redirect:/Employee";
    }

    @RequestMapping("/deleteEmployee/{id}")
    public String deleteCompany(@PathVariable(name = "id") Long id) {

        if(companyService.AunHaveCompany()){
            return "error_HasNoCompany";
        }
        if(companyService.AunCompanyIsActive()){
            return "error_HasCompanyNoActive";
        }

        employeeService.delete(id);

        return "redirect:/Employee";
    }
}


