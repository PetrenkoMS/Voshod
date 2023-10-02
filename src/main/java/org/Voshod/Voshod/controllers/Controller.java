package org.Voshod.Voshod.controllers;


import org.Voshod.Voshod.entity.Region;
import org.Voshod.Voshod.services.CurrentRegionInfoService;
import org.Voshod.Voshod.services.EventService;
import org.Voshod.Voshod.services.RegistrationService;
import org.Voshod.Voshod.services.UserService;
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

    public Controller(RegistrationService registrationService, UserService userService, CurrentRegionInfoService currentRegionInfoService, EventService eventService) {
        this.registrationService = registrationService;
        this.userService = userService;
        this.currentRegionInfoService = currentRegionInfoService;
        this.eventService = eventService;
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


//    @PostMapping(value = "/regionInfo")
//    public ModelAndView currentRegionInfo(Model model, Region currentRegion) {
//        return currentRegionInfoService.currentRegionInfo(currentRegion, model);
//    }
}
