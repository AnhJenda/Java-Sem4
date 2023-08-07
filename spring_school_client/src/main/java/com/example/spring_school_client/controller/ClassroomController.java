package com.example.spring_school_client.controller;

import com.example.spring_school_client.dto.ClassDto;
import com.example.spring_school_client.dto.PageDto;
import com.example.spring_school_client.properties.CommonProperties;
import com.example.spring_school_client.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

/*
    @author: Dinh Quang Anh
    Date   : 7/31/2023
    Project: Spring_school_client
*/
@Controller
public class ClassroomController {

    @Autowired
    private CommonProperties commonProperties;
    @Autowired
    private ClassroomService service;

    @GetMapping(value = "/classrooms")
    public ModelAndView gets(HttpServletRequest request,
                             @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        ClassDto criteria = new ClassDto();
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
        List<ClassDto> classes = service.getListClassroom(criteria);

        ModelAndView view = new ModelAndView("classroom-list");

        view.addObject("classrooms", classes);

        System.out.println(view.getViewName());

        return view;
    }

    @GetMapping(value = "/classroom")
    public ModelAndView get( @RequestParam(required = false) Long id, HttpServletRequest request){

        ClassDto classroom = service.getClassroom(id);

        ModelAndView view = new ModelAndView("details");
        view.addObject("classroom", classroom);
        return view;
    }

    @GetMapping(value = "/classroom/form")
    public ModelAndView form(@RequestParam(value = "id", required = false) Long id, HttpServletRequest request) {
        ClassDto classDto = new ClassDto();
        if (id != null && id > 0) {
            classDto = service.getClassroom(id);
        }
        ModelAndView view = new ModelAndView("create");
        view.addObject("clazz", classDto);
        return view;
    }

    @PostMapping(value = "classroom/save")
    public ModelAndView save(@ModelAttribute ClassDto classDto, BindingResult bindingResult, HttpServletRequest request) {
        if (bindingResult.hasErrors()) {
            return null;
        }

        // Tiếp tục xử lý khi không có lỗi validation
        ClassDto result = service.save(classDto);

        return new ModelAndView("redirect:/classroom?id=" + result.getId());
    }

    @GetMapping(value = "classroom/delete")
    public ModelAndView delete(@RequestParam(required = false) Long id, HttpServletRequest request){
        if (Objects.isNull(id)){
            return null;
        }
        service.delete(id);
        return new ModelAndView("redirect:/classrooms");
    }

}
