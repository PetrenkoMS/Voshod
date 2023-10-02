package org.Voshod.Voshod.services;

import jakarta.transaction.Transactional;
import org.Voshod.Voshod.entity.Region;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Transactional
@Service
public class EventService {
    public void eventForm(Model model) {
        model.addAttribute("event", new Region());
    }
}
