package com.myapp.controllers;

import com.myapp.dto.EmployeeDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.myapp.service.EmployeeService;

import java.util.List;

@Controller
public class EmployeeController {

    private static final Logger logger = LogManager.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService employeeService;


    /**
     * This method handles GET requests for both "/" and "/login", serves as default url.
     * <p>
     * ✅ When a user visits either:
     * • Http://localhost:8080/springMVCday1/
     * • Http://localhost:8080/springMVCday1/login
     * <p>
     * This method returns the same JSP view: "loginPage.jsp"
     * <p>
     * 💡 Note:
     * - Visiting "/" will render the login page, but the browser URL will remain:
     * → Http://localhost:8080/springMVCday1/
     * <p>
     * - It does NOT redirect to:
     * → Http://localhost:8080/springMVCday1/login
     * It simply renders loginPage.jsp for both paths without changing the visible URL.
     * <p>
     * So: either @GetMapping({"/", "/login"}) or @GetMapping({"/login", "/"}) => (order doesn't matter)
     * maps BOTH paths to the same method,
     * but it does NOT trigger a browser redirect or alter the URL.
     * <p>
     * 👉 If you want to **redirect to /login** and have the browser URL show it explicitly:
     * 1. You need to use:
     * return "redirect:/login";
     * 2. Or change:
     * '@GetMapping({"/", "/login"})
     * ➝ to ➝
     * '@GetMapping("/login")
     * <p>
     * Also, if you don't provide and write like this =>   @GetMapping(), it would also direct to Http://localhost:444/
     */
    @GetMapping({"/login", "/"}) // To fetch data or display a page or any read-only information
    public String callLoginPage() {
        logger.info(" Login page requested.....@@@@@.......");
        return "login/loginPage";
    }

    @PostMapping("/loginValidate") // To submit data to the server (e.g., saving a form)
    public String authenticate(@RequestParam String emailId, @RequestParam String password, Model model) {
        //forwarding request to service layer
        EmployeeDTO employeeDTO = employeeService.authenticateUser(emailId, password);

        // After getting back employee records w/valid credentials
        // Model interface is used to rendering the data in JSP as view.
        if (employeeDTO != null) {
            // for valid credentials
            model.addAttribute("employeeDTO", employeeDTO);
            return "login/congratulation";
        } else {
            // for in-valid credentials
            model.addAttribute("message", "Wrong credential-- Please try again with valid username & password !!!");
            return "login/loginPage";
        }
    }

    /**
     * Handles GET requests to "/register".
     * When a user visits this url Http//localhost:444/register, this method is invoked.
     * It returns the logical view name "employeeRegistration", which is resolved to:
     * /WEB-INF/pages/employeeRegistration.jsp
     * <p>
     * Then, JSP displays the registration form.
     * When the form is submitted, it POSTs to "/registration", which processes the data
     * and then renders the "registrationConfirmation.jsp" page.
     */
    @GetMapping("/register")
    public String registrationPage() {
        logger.info("Employee registration page requested........@@@@@@@@........");
        return "register/employeeRegistration";
    }

    //This method  registerEmployee should be triggered when a user submits a form to /registerValidation
    // using the POST method as it mention as action's value from employeeRegistration.jsp.
    @PostMapping("/registration")
    public String registerEmployee(@ModelAttribute EmployeeDTO employeeDTO, Model model) {
        employeeService.registerEmp(employeeDTO);

        // for the time being it's the data on the way to DB via employeeDTO, not the one coming back from DB
        model.addAttribute("employeeDTO", employeeDTO);
        model.addAttribute("message", "Record inserted successfully");
        return "register/registrationConfirmation";
    }

    //fetches all employees records
    @GetMapping("/showEmployee")
    public String showAllEmployees(Model model) {
        //first forwards the request to service layer to get all employee records
        //and return back all the record in the form of list(employeeDtoList)
        List<EmployeeDTO> employeeDtoList = employeeService.findAllEmployees();
        model.addAttribute("employeeDtoList", employeeDtoList);
        return "showAll/showAll";
    }

    //delete a record ("/deleteEmployee" simply just a href name)
    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int employeeId, Model model) {
        employeeService.deleteEmployeeById(employeeId);

        model.addAttribute("message", "Employee record deleted successfully!!!");
        return "delete/deleteConfirmation";
        //return "redirect:/showEmployee";
    }

    //update operation ------ tier 1
    @GetMapping("/updateEmployee")
    public String displayEditForm(@RequestParam int employeeId, Model model) {
        EmployeeDTO employeeDTO = employeeService.findEmployeeById(employeeId);
        model.addAttribute("employeeDTO", employeeDTO);
        return "update/updateEmployeeRecord";
    }

    //update operation ------ tier 2
    @PostMapping("/employeeUpdate")
    public String updateEmployeeInDb(@ModelAttribute EmployeeDTO employeeDTO, Model model){
        employeeService.registerEmpAfterUpdate(employeeDTO);
        model.addAttribute("message", "Record updated successfully");
        return "update/updateConfirmation";
        //return "redirect:/showEmployee";
    }
}

//    @PostMapping("/deleteEmployee")
//    public String deleteEmployee(@RequestParam("employeeId") Integer employeeId, RedirectAttributes redirectAttributes) {
//        boolean isEmployeeDeleted = employeeService.deleteEmployeeRecord(employeeId);
//
//        if (isEmployeeDeleted) {
//            redirectAttributes.addFlashAttribute("message", "Employee record deleted successfully.");
//        } else {
//            redirectAttributes.addFlashAttribute("message", "Employee not found.");
//        }
//        return "redirect:/showAll";
//    }

//    @PostMapping("/deleteEmployee")
//        public String deleteEmployeeWithModel(@RequestParam("employeeId") Integer employeeId, Model model) {
//
//        boolean isEmployeeDeleted = employeeService.deleteEmployeeRecord(employeeId);
//
//        if (isEmployeeDeleted) {
//            model.addAttribute("message", "Employee with Employee ID : " + employeeId + " is deleted successfully.");
//        } else {
//            model.addAttribute("message", "Employee not found.");
//        }
//        return "delete/deleteStatus";
//    }
