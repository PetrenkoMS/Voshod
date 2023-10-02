package org.Voshod.Voshod.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.Voshod.Voshod.entity.User;
import org.Voshod.Voshod.models.MyUserDetails;
import org.Voshod.Voshod.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


/**
 * Авторизация и аутентификация пользователя
 */
@Transactional
@Service
public class UserService implements UserDetailsService {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    public static String currentUserLogin;

    @Autowired
    UserRepository userRepository;

    /**
     * Проверка пользователя
     * @param userName - логин пользователя
     * @return либо пользователя, либо ошибку
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUserName(userName);
        currentUserLogin = userName;

        user.orElseThrow(() -> new UsernameNotFoundException("Пользователь " + userName + " не найден"));


        return user.map(MyUserDetails::new).get();
    }
}
