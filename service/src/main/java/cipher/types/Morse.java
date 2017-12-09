package cipher.types;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component(value = "Morse code")
public class Morse implements Cipher {

    private static final Character ERROR_CHAR = '~';

    private Map<Character, String> encodeAlphabet;
    private Map<String, Character> decodeAlphabet;

    public Morse() {
        encodeAlphabet = new HashMap<>();
        encodeAlphabet.put('a', ".-");
        encodeAlphabet.put('b', "-...");
        encodeAlphabet.put('c', "-.-.");
        encodeAlphabet.put('d', "-..");
        encodeAlphabet.put('e', ".");
        encodeAlphabet.put('f', "..-.");
        encodeAlphabet.put('g', "--.");
        encodeAlphabet.put('h', "....");
        encodeAlphabet.put('i', "..");
        encodeAlphabet.put('j', ".---");
        encodeAlphabet.put('k', "-.-");
        encodeAlphabet.put('l', ".-..");
        encodeAlphabet.put('m', "--");
        encodeAlphabet.put('n', "-.");
        encodeAlphabet.put('o', "---");
        encodeAlphabet.put('p', ".--.");
        encodeAlphabet.put('q', "--.-");
        encodeAlphabet.put('r', ".-.");
        encodeAlphabet.put('s', "...");
        encodeAlphabet.put('t', "-");
        encodeAlphabet.put('u', "..-");
        encodeAlphabet.put('v', "...-");
        encodeAlphabet.put('w', ".--");
        encodeAlphabet.put('x', "-..-");
        encodeAlphabet.put('y', "-.--");
        encodeAlphabet.put('z', "--..");
        encodeAlphabet.put('0', "-----");
        encodeAlphabet.put('1', ".----");
        encodeAlphabet.put('2', "..---");
        encodeAlphabet.put('3', "...--");
        encodeAlphabet.put('4', "....-");
        encodeAlphabet.put('5', ".....");
        encodeAlphabet.put('6', "-....");
        encodeAlphabet.put('7', "--...");
        encodeAlphabet.put('8', "---..");
        encodeAlphabet.put('9', "----.");
        encodeAlphabet.put('.', ".-.-.-");
        encodeAlphabet.put(',', "--..--");
        encodeAlphabet.put('?', "..--..");
        encodeAlphabet.put('\'', ".----.");
        encodeAlphabet.put('!', "-.-.--");
        encodeAlphabet.put('/', "-..-.");
        encodeAlphabet.put('(', "-.--.");
        encodeAlphabet.put(')', "-.--.-");
        encodeAlphabet.put('&', ".-...");
        encodeAlphabet.put(':', "---...");
        encodeAlphabet.put(';', "-.-.-.");
        encodeAlphabet.put('=', "-...-");
        encodeAlphabet.put('+', ".-.-.");
        encodeAlphabet.put('-', "-....-");
        encodeAlphabet.put('_', "..--.-");
        encodeAlphabet.put('\"', ".-..-.");
        encodeAlphabet.put('$', "...-..-");
        encodeAlphabet.put('@', ".--.-.");
        encodeAlphabet.put(' ', "|");
        encodeAlphabet.put('\r', "");
        encodeAlphabet.put('\n', "\n");
        encodeAlphabet.put(ERROR_CHAR, "........");

        decodeAlphabet = encodeAlphabet.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
        decodeAlphabet.put("\r\n", '\n');
    }

    @Override
    public String encode(String message) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(message)) {
            for (char character : message.trim().toLowerCase().toCharArray()) {
                String code = encodeAlphabet.get(character);
                if (code != null) {
                    sb.append(code);
                } else {
                    sb.append(encodeAlphabet.get(ERROR_CHAR));
                }
                if (!('\r' == character) && !('\n' == character)) {
                    sb.append(' ');
                }
            }
        }

        return sb.toString().trim();
    }

    @Override
    public String decode(String codedMessage) {
        StringBuilder sb = new StringBuilder();
        if (StringUtils.isNotBlank(codedMessage)) {
            String[] codes = codedMessage.trim().split("(?<=\r\n)|(?=\r\n)|[ ]+");

            for (String code : codes) {
                Character character = decodeAlphabet.get(code);
                if (character != null) {
                    sb.append(character);
                } else {
                    sb.append(ERROR_CHAR);
                }
            }
        }

        return sb.toString().trim();
    }
}
