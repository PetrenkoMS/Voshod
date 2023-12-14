package org.Voshod.Voshod.services;

import jakarta.transaction.Transactional;
import org.Voshod.Voshod.entity.Region;
import org.Voshod.Voshod.entity.User;
import org.Voshod.Voshod.repository.RegionRepository;
import org.Voshod.Voshod.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class RegistrationService {
    private static final Logger logger = Logger.getLogger(RegistrationService.class.getName());

    @Autowired
    private RegionRepository regionRepository;

    @Autowired
    private UserRepository userRepository;

    public void regionForm(Model model) {
        model.addAttribute("region", new Region());
    }

    public ModelAndView regionRegistration(Region region, Model model) {
        int strength = 12;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(strength, new SecureRandom());

        String returnPage="registration";
        Optional<Region> employLogin = this.regionRepository.findByLogin(region.getLogin());
        List<Region> employHouseNumber = this.regionRepository.findByHouseNumber(region.getHouseNumber());
        Optional<Region> employSeriesAndNumber = this.regionRepository.findBySeriesPassportAndNumberPassport(region.getSeriesPassport(), region.getNumberPassport());

        if (employLogin.isEmpty()) {
            if (employHouseNumber.isEmpty()) {
                if (employSeriesAndNumber.isEmpty()) {
                    User userNew = new User();
                    userNew.setUserName(region.getLogin());
                    userNew.setPassword(bCryptPasswordEncoder.encode(region.getPassword()));
                    userNew.setRoles("ROLE_USER");
                    userNew.setActive(true);
                    logger.info("block 'if' in registration complete");

                    userRepository.save(userNew);
                    region.setEr("Регистрация прошла успешно");
                    region.setPassword(null);
                    regionRepository.save(region);
                    returnPage = "main";
                    logger.info("registration complete");
                }
                else {
                    region.setEr("Серия и номер паспорта уже используется");
                    logger.info("block 'if (employSeriesAndNumber.isPresent())' not complete. Error series and number passport)");
                }
            }
            else {
                region.setEr("Данный участок уже зарегистрирован");
                logger.info("block 'if (employHouseNumber.isPresent())' not complete. Error number of house");
            }
        }
        else {
            region.setEr("Данный логин уже занят");
            logger.info("block 'if (employLogin.isPresent())' not complete. Error login");
        }

        model.addAttribute("region", region);
        return new ModelAndView(returnPage);
    }
}
