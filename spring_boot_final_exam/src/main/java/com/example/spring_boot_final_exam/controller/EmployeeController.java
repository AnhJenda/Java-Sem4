package com.example.spring_boot_final_exam.controller;

import com.example.spring_boot_final_exam.dto.EmployeeDto;
import com.example.spring_boot_final_exam.config.properties.CommonProperties;
import com.example.spring_boot_final_exam.dto.PageDto;
import com.example.spring_boot_final_exam.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpClient;

/*
    @author: Dinh Quang Anh
    Date   : 7/5/2023
    Project: spring_boot_final_exam
*/
@Controller
public class EmployeeController {
    @Autowired
    protected CommonProperties commonProperties;
    @Autowired
    private EmployeeService employeeService;


    // list all
    @GetMapping(value = "/employee-list")
    public ModelAndView getProductList(HttpServletRequest request,
                                       @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                       @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        EmployeeDto criteria = new EmployeeDto();

        // Nếu không truyền paging thì lấy giá trị mặc định từ commonProperties
        if (pageNumber == null || pageNumber < 0) {
            criteria.setPageNumber(commonProperties.getPageNumber());
        } else {
            criteria.setPageNumber(pageNumber);
        }
        if (pageSize == null || pageSize <= 0) {
            criteria.setPageSize(commonProperties.getPageSize());
        } else {
            criteria.setPageSize(pageSize);
        }
        ModelAndView view = new ModelAndView("jsp/employee-list");
        PageDto<EmployeeDto> employeeDtoPageDto = employeeService.getAll(criteria);

        view.addObject("employees", employeeDtoPageDto.getContent());
        view.addObject("pages", employeeDtoPageDto);
        return view;
    }

    // details
    @GetMapping(value = "/employee")
    public ModelAndView getById(@RequestParam long id, HttpServletRequest request){
        EmployeeDto employeeDto = employeeService.getById(id);
        ModelAndView view = new ModelAndView("jsp/details");
        view.addObject("employee", employeeDto);
        return view;
    }

    // create
    @GetMapping(value = "/employee/create")
    public ModelAndView create(HttpServletRequest request){
        ModelAndView view = new ModelAndView("jsp/create");
        return view;
    }
    @PostMapping(value = "/employee/create")
    public ModelAndView create(@ModelAttribute("employeeDto") EmployeeDto employeeDto){
//        ModelAndView view = new ModelAndView("jsp/create.jsp");
        if (employeeDto.getName() == null || employeeDto.getName().isEmpty()) {
            // Các trường không được để trống, xử lý lỗi tại đây
            ModelAndView errorView = new ModelAndView("jsp/create");
            errorView.addObject("error", "Vui lòng điền đầy đủ thông tin tên nhân viên.");
            return errorView;
        }
        employeeService.create(employeeDto);
        return new ModelAndView(new RedirectView("/employee-list", true));
    }

    // update
    @GetMapping(value = "/employee/update")
    public ModelAndView update(@RequestParam Long id){
        ModelAndView view = new ModelAndView("jsp/update");
        EmployeeDto employeeDto = employeeService.getById(id);
        view.addObject("employee", employeeDto);
        return view;
    }
    @PostMapping(value = "/employee/update")
    public ModelAndView update(@RequestParam Long id, @ModelAttribute EmployeeDto employeeDto){
        employeeService.update(id, employeeDto);
        return new ModelAndView(new RedirectView("/employee-list", true));
    }

    @GetMapping(value = "/employee/delete")
    public ModelAndView delete(@RequestParam Long id){
        employeeService.delete(id);
        return new ModelAndView(new RedirectView("/employee-list", true));
    }
}
