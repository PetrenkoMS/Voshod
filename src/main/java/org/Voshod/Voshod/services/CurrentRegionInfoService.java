package org.Voshod.Voshod.services;

import jakarta.transaction.Transactional;
import org.Voshod.Voshod.entity.Region;
import org.Voshod.Voshod.repository.RegionRepository;
import org.Voshod.Voshod.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Optional;

@Transactional
@Service
public class CurrentRegionInfoService {

    private static final Logger logger = Logger.getLogger(CurrentRegionInfoService.class.getName());

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private UserRepository userRepository;

    public ModelAndView currentRegionInfoGet(Region userInfo, Model model) {
        Optional<Region> currentRegionLogin = this.regionRepository.findByLogin(UserService.currentUserLogin);
        logger.info("currentUserLogin is " + UserService.currentUserLogin);
        logger.info("currentRegionLogin isPresent " + currentRegionLogin.isPresent());
        Region region = currentRegionLogin.get();
        logger.info("currentRegionLogin 'region' " + region);
        userInfo.setLogin(UserService.currentUserLogin);

        String name = region.getName();
        userInfo.setName(name);

        String secondName = region.getSecondName();
        userInfo.setSecondName(secondName);

        String houseNumber = region.getHouseNumber();
        userInfo.setHouseNumber(houseNumber);

        Date dateOfBirth = region.getDateOfBirth();
        userInfo.setDateOfBirth(dateOfBirth);

        String telephone = region.getTelephone();
        userInfo.setTelephone(telephone);

        String mail = region.getMail();
        userInfo.setMail(mail);
        logger.info("currentRegionLogin 'name' " + name);
        logger.info("currentRegionLogin 'date' " + dateOfBirth);

        model.addAttribute("userInfo", userInfo);

//        model.addAttribute("currentRegion", new Region());
//        Region currentRegion = new Region();
//        currentRegionLogin.map(currentRegionOp -> currentRegionOp.getLogin())
//                .ifPresent(
//                        login -> currentRegion.setLogin(login)
//                );
//        logger.info("ny : " + currentRegion.getLogin());

//    }

//    public ModelAndView currentRegionInfo(Region currentRegion, Model model) {
////        String currentLogin = currentRegion.getLogin();
////        String currentName = currentRegion.getName();
////        String currentSecondName = currentRegion.getSecondName();
////        Date currentDateOfBirth = currentRegion.getDateOfBirth();
////        String currentSeries = currentRegion.getSeriesPassport();
////        String currentNumber = currentRegion.getNumberPassport();
////        String currentEmail = currentRegion.getMail();
////        String currentPhone = currentRegion.getTelephone();
//
//
//
        return new ModelAndView("regionInfo");
    }
}
