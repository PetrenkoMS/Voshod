package org.Voshod.Voshod.controllers;


import org.Voshod.Voshod.entity.ParamToFind;
import org.Voshod.Voshod.entity.Region;
import org.Voshod.Voshod.services.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Controller {

    private final RegistrationService registrationService;
    private final UserService userService;
    private final CurrentRegionInfoService currentRegionInfoService;
    private final EventService eventService;
    private final FindUserService findUserService;

    public Controller(RegistrationService registrationService, UserService userService, CurrentRegionInfoService currentRegionInfoService, EventService eventService, FindUserService findUserService) {
        this.registrationService = registrationService;
        this.userService = userService;
        this.currentRegionInfoService = currentRegionInfoService;
        this.eventService = eventService;
        this.findUserService = findUserService;
    }

    @GetMapping(value = "/")
    public ModelAndView main(Model model) {
        return new ModelAndView("main");
    }

    @GetMapping(value = "/registration")
    public ModelAndView registration(Model model) {
        registrationService.regionForm(model);
        return new ModelAndView("registration");
    }

    @PostMapping(value = "/registration")
    public ModelAndView registration(Region region, Model model) {
        return registrationService.regionRegistration(region, model);
    }

    @GetMapping(value = "/login")
    public ModelAndView login(Model model) {
        return new ModelAndView("login");
    }

    @GetMapping(value = "/event")
    public ModelAndView event(Model model) {
        eventService.eventForm(model);
        return new ModelAndView("event");
    }

    @GetMapping(value = "/regionInfo")
    public ModelAndView currentRegionInfoGet(Region userInfo, Model model) {
        currentRegionInfoService.currentRegionInfoGet(userInfo, model);
        return new ModelAndView("regionInfo");
    }

    @GetMapping(value = "/findUser")
    public ModelAndView findUser(Model model) {
        findUserService.findUserServiceGet(model);
        return new ModelAndView("findUser");

    }

    @PostMapping(value = "/findUser")
    public ModelAndView findUser(Region findUser, ParamToFind paramToFind, Model model) {
        findUserService.findUserServicePost(findUser, paramToFind, model);
        return new ModelAndView("findUser");
    }


//    @PostMapping(value = "/regionInfo")
//    public ModelAndView currentRegionInfo(Model model, Region currentRegion) {
//        return currentRegionInfoService.currentRegionInfo(currentRegion, model);
//    }
}
