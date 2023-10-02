package org.Voshod.Voshod.services;

import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class LoginService {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
}
