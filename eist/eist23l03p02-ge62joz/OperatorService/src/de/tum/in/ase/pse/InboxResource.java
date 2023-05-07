package de.tum.in.ase.pse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(value = "/messages/", consumes = "application/json")
public class InboxResource {

    private final DroidFactory factory = new DroidFactory();

    @PostMapping("r2")
    public ResponseEntity<String> droidReadyR2(@RequestBody String droid) {
        return new ResponseEntity<>(droid, HttpStatus.OK);
    }

    @PostMapping("3po")
    public ResponseEntity<String> droidReady3PO(@RequestBody String droid) {
        return new ResponseEntity<>(droid, HttpStatus.OK);
    }
    
}
