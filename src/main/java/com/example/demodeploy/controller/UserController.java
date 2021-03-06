package com.example.demodeploy.controller;


import com.example.demodeploy.model.Category;
import com.example.demodeploy.model.User;
import com.example.demodeploy.service.ICategoryService;
import com.example.demodeploy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    IUserService userService;

    @Autowired
    ICategoryService categoryService;

    @ModelAttribute("categories")
    public Iterable<Category> provinces(){
        return categoryService.findAll();
    }

    @GetMapping
    public String showList(Model model){
        return "redirect:/users/find";
    }

    @GetMapping("/create")
    public ModelAndView showCreate(){
        ModelAndView modelAndView = new ModelAndView("create");
        modelAndView.addObject("user",new User());
        return modelAndView;
    }

    @PostMapping("/create")
    public String crete(User user){
        userService.save(user);
        return "redirect:/users/find";
    }

    @GetMapping("/find")
    public ModelAndView findByName(@RequestParam("keyword") Optional<String> keyword,
                                   @RequestParam("category") Optional<Category> category,
                                   @PageableDefault(value = 5, page = 0)
                                   @SortDefault(sort = "username", direction = Sort.Direction.DESC)
                                           Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("list");
        Page<User> userPage;
        if (category.isPresent()){
            userPage = userService.findAllByUsernameContainingAndCategory(keyword.orElse(""), category.get(), pageable);
        }
        else {
            userPage = userService.findAllByUserContaining(keyword.orElse(""),pageable);
        }
        modelAndView.addObject("users", userPage);
        modelAndView.addObject("keyword",keyword.orElse(""));
        return modelAndView;
    }
    @GetMapping("/{id}/edit")
    public ModelAndView showEdit(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("edit");
        modelAndView.addObject("user",userService.findById(id));
        return modelAndView;
    }

    @PostMapping("/{id}/edit")
    public String edit(User user){
        ModelAndView modelAndView = new ModelAndView("edit");
        userService.save(user);
        return "redirect:/users/find";
    }

    @GetMapping("/{id}/delete")
    public ModelAndView showDelete(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("delete");
        modelAndView.addObject("user",userService.findById(id));
        return modelAndView;
    }

    @PostMapping("/{id}/delete")
    public String delete(User user){
        ModelAndView modelAndView = new ModelAndView("delete");
        userService.remove(user);
        return "redirect:/users/find";
    }

}
