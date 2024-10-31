package com.ada.holiday_party_planning.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Teste {

    @GetMapping ("/001")
    public String teste01 () {
        return "45156";
    }

}
