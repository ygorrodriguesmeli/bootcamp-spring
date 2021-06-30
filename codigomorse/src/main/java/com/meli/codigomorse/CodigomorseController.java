package com.meli.codigomorse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CodigomorseController {

    private static final List<String> MORSE = Arrays.asList(".-","-...","-.-.","-..",".","..-.","--.","....",
            "..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-",
            "-.--","--..");
    private static final List<String> CHARACTERS = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L",
            "M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");

    @GetMapping(path = "/{codigo}")
    public String conversaoCodigoMorse(@PathVariable String codigo) {
        String[] morseWords = codigo.split("\\s{3}");
        StringBuilder retorno = new StringBuilder();
        for(String morseWord : morseWords) {
            String[] separatedMorseWord = morseWord.trim().split("\\s+");
            StringBuilder word = new StringBuilder();
            for(String morseChar : separatedMorseWord) {
                int morseIndex = MORSE.indexOf(morseChar.trim());
                word.append(CHARACTERS.get(morseIndex));
            }
            retorno.append(word);
            retorno.append(" ");
        }
        return retorno.toString().trim();
    }
}
