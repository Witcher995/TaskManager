/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utp.teamwork.controller;

import com.sun.jmx.snmp.tasks.TaskServer;
import com.utp.teamwork.model.Branch;
import com.utp.teamwork.model.CheckListTask;
import com.utp.teamwork.model.Comments;
import com.utp.teamwork.model.Employees;
import com.utp.teamwork.model.Notifications;
import com.utp.teamwork.model.Roles;
import com.utp.teamwork.model.Tasks;
import com.utp.teamwork.repository.BranchRepository;
import com.utp.teamwork.repository.CheckListTaskRepository;
import com.utp.teamwork.repository.CommentsRepository;
import com.utp.teamwork.repository.EmployeesRepository;
import com.utp.teamwork.repository.NotificationsRepository;
import com.utp.teamwork.repository.RolesRepository;
import com.utp.teamwork.repository.TasksRepository;
import com.utp.teamwork.service.BranchService;
import com.utp.teamwork.service.CheckListTaskService;
import com.utp.teamwork.service.CommentsService;
import com.utp.teamwork.service.EmployeeService;
import com.utp.teamwork.service.NotificationsService;
import com.utp.teamwork.service.RoleService;
import com.utp.teamwork.service.SecurityService;
import com.utp.teamwork.service.TasksService;
import java.security.Principal;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author AdamPrzedlacki
 */
@Controller
public class AuthenticationController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    BranchService branchService;

    @Autowired
    RolesRepository rolesRepository;
    
    @Autowired
    EmployeesRepository employeeRepository;

    @Autowired
    SecurityService securityService;

    @Autowired
    RoleService roleService;

    @Autowired
    TasksService tasksService;

    @Autowired
    NotificationsService notificationsService;

    @Autowired
    CheckListTaskRepository checkListTaskRepository;

    @Autowired
    CheckListTaskService checkListTaskService;

    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    CommentsService commentsService;

    @Autowired
    NotificationsRepository notificationRepository;

    @Autowired
    TasksRepository tasksRepository;

    @Autowired
    BCryptPasswordEncoder be;

    @Autowired
    BranchRepository branchRepository;

    @RequestMapping(value = "/main")
    public String main(Model model, Principal principal) {
        model.addAttribute("tasks", tasksService.findByUserName(principal.getName()));
        return "main";
    }

    @RequestMapping("/login")
    public String loginPage1(Model model, String error, String logout, HttpServletRequest request) {
        return "login";
    }

    @RequestMapping(value = "/loginError")
    public String loginError(Model model, String username) {
        model.addAttribute("error", "Your username and password is invalid.");
        model.addAttribute("username", username);
        return "login";
    }

    @RequestMapping("/admin")
    public String admin() {
        return "/admin/admin";
    }

    @RequestMapping("/user")
    public String user() {
        return "/user/user";
    }

    @RequestMapping("/403")
    public String access() {
        return "/access";
    }

    @GetMapping(value = "/admin/employees")
    public String employees(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "admin/employees";
    }

    @GetMapping(value = "/admin/registration")
    public String registrationUser(Model model) {
        model.addAttribute("employeeForm", new Employees());
        model.addAttribute("branches", branchService.findAll());
        model.addAttribute("roles", roleService.findAll());
        return "admin/registration";
    }

    @PostMapping(value = "/admin/registration")
    public String registrationUser(@ModelAttribute("employeeForm") Employees employeeForm,
            BindingResult bindingResult,
            Long roleId, Long branchId) {
        if (bindingResult.hasErrors()) {
            return "form";
        } else {
            employeeService.saveUser(employeeForm, roleId, branchId);
        }
        return "redirect:/admin";
    }

    @GetMapping(value = "admin/delete/{id}")
    public String removeEmployee(@PathVariable("id") Long employeeId, Model model) {
        employeeService.deleteUserById(employeeId);

        return "redirect:/admin/employees";
    }

    @GetMapping(value = "admin/edit/{id}")
    public String editEmployee(@PathVariable("id") Long employeeId, Model model) {
        model.addAttribute("employee", employeeService.findUserById(employeeId));
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("branches", branchService.findAll());
        return "/admin/editEmployee";
    }

    @PatchMapping(value = "admin/edit/{id}")
    public String editEmployee(@ModelAttribute("employee") Employees editEmployeeForm, 
            @PathVariable("id") Long id,
            Long branchId,
            String roleId, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        } else {
            editEmployeeForm.setPassword(employeeRepository.findOne(id).getPassword());
            employeeRepository.saveAndFlush(editEmployeeForm);
            //employeeService.updateUser(editEmployeeForm, roleId, branchId);
        }

        return "redirect:/admin/employees";
    }

    @GetMapping(value = "/admin/addBranch")
    public String addBranch(Model model) {
        model.addAttribute("branchForm", new Branch());
        return "admin/addBranch";
    }

    @PostMapping(value = "/admin/addBranch")
    public String addBranch(@ModelAttribute("branchForm") Branch branchForm,
            BindingResult bindingResult) {
        branchService.saveBranch(branchForm);
        return "redirect:/admin";

    }

    @GetMapping(value = "/tasks")
    public String getTasks(Model model) {
        model.addAttribute("tasks", tasksService.findAll());
        return "user/tasks";
    }

    @GetMapping(value = "/tasks/addTask")
    public String addTask(Model model) {
        model.addAttribute("taskForm", new Tasks());
        model.addAttribute("employees", employeeService.findAll());
        return "user/addTask";
    }

    @PostMapping(value = "/tasks/addTask")
    public String addTask(@ModelAttribute("taskForm") Tasks taskForm, Long employeeId,
            BindingResult bindingResult) {
        taskForm.setStatus("todo");
        tasksService.saveTask(taskForm, employeeId);
        notificationsService.saveNotification(employeeService.findUserById(employeeId),
                "Przydzielono Ci nowe zadanie: ",
                "tasks/details/" + taskForm.getId(),
                false);
        return "redirect:/tasks";
    }

    @PatchMapping(value = "/tasks/details/{id}/editTask")
    public String editTask(@ModelAttribute("task") Tasks task,
            @PathVariable("id") Long taskId,
            Long[] employeesId,
            Model model) {

        tasksService.updateTask(task);

        return "redirect:/tasks/details/{id}";
    }

    @GetMapping(value = "/tasks/details/{id}")
    public String detailsTask(@PathVariable("id") Long taskId, Model model) {
        model.addAttribute("task", tasksService.findUserById(taskId));
        model.addAttribute("comments", commentsService.findCommentByTaskId(taskId));
        model.addAttribute("checkList", checkListTaskService.findCheckListByTaskId(taskId));
        model.addAttribute("emp", employeeService.findAll());
        return "user/taskDetails";
    }

    @GetMapping(value = "/tasks/details/{id}/{es}")
    public String removeUserFromTask(@PathVariable("id") Long taskId, @PathVariable("es") Long employeeId) {
        tasksService.removeUser(taskId, employeeId);
        return "redirect:/tasks/details/{id}";
    }

    @PostMapping(value = "/tasks/details/{id}/addComment")
    public String addCommentToTask(@PathVariable("id") Long taskId,
            @ModelAttribute("commentForm") Comments comments,
            Principal principal) {
        comments.setDateOfComment(Date.from(Instant.now()));
        comments.setEmployee(employeeService.findByUsername(principal.getName()));
        comments.setTasks(tasksService.findUserById(taskId));
        commentsRepository.saveAndFlush(comments);
        return "redirect:/tasks/details/{id}";
    }

    @PostMapping(value = "/tasks/details/{id}/addCheckList")
    public String addCheckListToTask(@PathVariable("id") Long taskId,
            @ModelAttribute("checkListForm") CheckListTask checkListTask) {

        checkListTask.setTask(tasksService.findUserById(taskId));
        checkListTaskRepository.saveAndFlush(checkListTask);
        return "redirect:/tasks/details/{id}";
    }

    @PostMapping(value = "/tasks/details/{id}/editCheckList")
    public String updateCheckListStatus(@PathVariable("id") Long taskId,
            @ModelAttribute("checkListId") Long checkListId) {

        CheckListTask checkListTask = checkListTaskRepository.findOne(checkListId);
        if (!checkListTask.isIsChecked()) {
            checkListTask.setChecked(true);
            System.out.println("1");
        } else {
            checkListTask.setChecked(false);
        }

        checkListTaskRepository.saveAndFlush(checkListTask);

        return "redirect:/tasks/details/{id}";
    }

    @GetMapping(value = "notifications")
    public String notifications(Model model, Principal principal) {
        model.addAttribute("notifications",
                notificationsService.findNotificationsByUsername(principal.getName()));
        return "user/notifications";
    }

    @GetMapping(value = "/admin/toAccept")
    public String tasksToAccept(Model model) {
        model.addAttribute("acc", tasksRepository.findByStatus("done"));
        return "admin/toAccept";
    }

    @GetMapping(value = "/admin/toAccept/{id}")
    public String tasksToDelete(Model model, @PathVariable("id") Long taskId) {
        tasksService.deleteById(taskId);
        //tasksRepository.delete(tasksRepository.findOne(taskId));
        return "redirect:/admin/toAccept";
    }

    @GetMapping(value = "notofications/delete/{id}")
    public String notificationDelete(@PathVariable("id") Long notification) {
        notificationRepository.delete(notification);
        return "redirect:/notifications";
    }

    @GetMapping(value = "myProfile")
    public String myProfile(Model model, Principal principal) {
        Employees employeer = employeeService.findByUsername(principal.getName());
        if (!employeer.equals(null)) {
            model.addAttribute("profile", employeer);
        } else {
            model.addAttribute("profile", new Employees());
        }
        return "user/myProfile";

    }

    @PatchMapping(value = "myProfile/{id}")
    public String editTask(@ModelAttribute("profile") Employees employee,
            @PathVariable("id") Long taskId,
            Model model) {

        employeeService.updateUser(employeeService.findUserById(taskId));

        return "redirect:/myProfile";
    }

    @GetMapping(value = "profiles")
    public String profiles(Model model) {

        model.addAttribute("employees", employeeService.findAll());

        return "user/profiles";
    }

    @GetMapping(value = "admin/branches")
    public String branches(Model model) {

        model.addAttribute("branches", branchService.findAll());

        return "admin/branches";
    }

    @GetMapping(value = "admin/branches/delete/{id}")
    public String delBranch(Model model, @PathVariable("id") Long branchId) {
        branchRepository.delete(branchId);
        return "redirect:/admin/branches";
    }

}
