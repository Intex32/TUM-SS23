package de.tum.in.ase.pse.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ObiWanKenobiController {

    @GetMapping("/train-apprentices")
    public String trainApprentices() {
        return "Apprentices are being trained...";
    }

}
