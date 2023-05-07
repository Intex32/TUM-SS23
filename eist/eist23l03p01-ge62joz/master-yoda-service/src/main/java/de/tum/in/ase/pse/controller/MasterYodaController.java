package de.tum.in.ase.pse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MasterYodaController {

    @GetMapping("/find-apprentices")
    public String findNewApprentices() {
        return "Looking for new apprentices...";
    }

}

