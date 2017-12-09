package cipher.controller;

import cipher.service.CipherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CipherController {

    @Autowired
    CipherService service;


    @GetMapping
    public String welcome(ModelMap model) {
        model.put("cipherNames", service.availableCipherNames());
        return "cipher";
    }

    @PostMapping(params = "encode")
    public String encode(
            @RequestParam String input, @ModelAttribute("cipher") String cipherName, ModelMap model
    ) {
        model.put("cipherNames", service.availableCipherNames());
        String encodedMessage = service.encodeMessage(input, cipherName);
        model.put("input", input);
        model.put("output", encodedMessage);
        return "cipher";
    }

    @PostMapping(params = "decode")
    public String decode(
            @RequestParam String input, @ModelAttribute("cipher") String cipherName, ModelMap model
    ) {
        model.put("cipherNames", service.availableCipherNames());
        String encodedMessage = service.decodeMessage(input, cipherName);
        model.put("input", input);
        model.put("output", encodedMessage);
        return "cipher";
    }

}
