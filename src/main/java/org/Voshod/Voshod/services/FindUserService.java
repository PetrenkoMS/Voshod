package org.Voshod.Voshod.services;

import org.Voshod.Voshod.entity.ParamToFind;
import org.Voshod.Voshod.entity.Region;
import org.Voshod.Voshod.repository.RegionRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class FindUserService {

    private static final Logger logger = Logger.getLogger(CurrentRegionInfoService.class.getName());

    @Autowired
    private RegionRepository regionRepository;

    public void findUserServiceGet(Model model) {
        model.addAttribute("findUser", new Region());
        model.addAttribute("paramToFind", new ParamToFind());
        logger.info("Get!");
    }

    public ModelAndView findUserServicePost(Region findUser, ParamToFind paramToFind, Model model) {
        String param = paramToFind.getSelectParam();
        logger.info("paramToFind param is: " + param);

        Optional<Region> forSwitch = Optional.empty();
        List<Region> listForSwitch = null;
        Region currentUser = new Region();
        Region currentNameAndSName = new Region();
        String cUser = "";
        String pattern = "";
        String titlePattern = "";

        switch(param) {
            case "nameAndSecondName":
                String fSecondName = paramToFind.getFUser().split(" ")[0];
                String fName = paramToFind.getFUser().split(" ")[1];
                logger.info("paramToFind fName is: " + fName);
                logger.info("paramToFind fSecondName is: " + fSecondName);

                listForSwitch = regionRepository.findByNameAndSecondName(fName, fSecondName);
                logger.info("currentUser.nameAndSecondName.get() = " + listForSwitch);
                logger.info("size: " + listForSwitch.size());
                if (listForSwitch.size() >= 1) {
                    findUser.setPattern("^[A-Za-zА-Яа-яЁё\s]{2,}\s^[A-Za-zА-Яа-яЁё\s]{2,}");
                    findUser.setTitlePattern("Минимум два символа без цифр и спец. символов. См. также *");
                    findUser.setFoundError("ok");
//                    model.addAttribute("thisUser", thisUser);
                    logger.info("block 'if' in nameAndSecondName is ok");
                }
                else {
                    findUser.setFoundError("User was not found!");
                    logger.info("nameAndSecondName elseError: User was not found!");
                }
//                currentNameAndSName = nameAndSecondName.get();
//                String cName = currentUser.getName();
//                String cSecondName = currentUser.getSecondName();

                break;

            case "houseNumber":
                listForSwitch = regionRepository.findByHouseNumber(paramToFind.getFUser());
                logger.info("HouseNumber is: " + listForSwitch);
                logger.info("size: " + listForSwitch.size());
                if (listForSwitch.size() >= 1) {
                    findUser.setPattern("[0-9]{1,3}");
                    findUser.setTitlePattern("Введите номер от 1 до  106");
                    findUser.setFoundError("ok");
                    logger.info("currentUser.getHouseNumber is: " + cUser);
                    logger.info("block 'if' in houseNumber is ok");
                }
                else {
                    findUser.setFoundError("User was not found!");
                    logger.info("houseNumber elseError: User was not found!");
                }
                break;

            case "telephone":
                listForSwitch = regionRepository.findByTelephone(paramToFind.getFUser());
                logger.info("Telephone is: " + listForSwitch);
                logger.info("size: " + listForSwitch.size());
                if (listForSwitch.size() >= 1) {
//                    currentUser = forSwitch.get();
//                    cUser = currentUser.getTelephone();
                    findUser.setPattern("[0-9]{11})");
                    findUser.setTitlePattern("x-xxx-xxx-xx-xx");
                    findUser.setFoundError("ok");
                    logger.info("Block 'if' in telephone is ok");
                }
                else {
                    findUser.setFoundError("User was not found!");
                    logger.info("telephone elseError: User was not found!");
                }
                break;

            case "mail":
                listForSwitch = regionRepository.findByMail(paramToFind.getFUser());
                logger.info("Mail is: " + listForSwitch);
                logger.info("size: " + listForSwitch.size());
                if (listForSwitch.size() >= 1) {
//                    currentUser.getMail();
//                    cUser = currentUser.getMail();
                    findUser.setPattern("{'[a-z0-9]+@[a-z]+\\.[a-z]{2,6}'}");
                    findUser.setTitlePattern("Введите корректно почту (формат: login@email.domen)");
                    findUser.setFoundError("ok");
                    logger.info("block 'if' in mail is ok");
                }
                else {
                    findUser.setFoundError("User was not found!");
                    logger.info("mail elseError: User was not found!");
                }
                break;
        }

        logger.info("ParamToFind: " + paramToFind.getName());
        logger.info("----------------");
        model.addAttribute("findUser", findUser);
        model.addAttribute("listForSwitch", listForSwitch);
        return new ModelAndView("findUser");
    }
}
