package cipher.service;

import cipher.types.Cipher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Service
public class DefaultCipherService implements CipherService {

    @Autowired
    private Map<String, Cipher> ciphers = new HashMap<>();

    @Override
    public Set<String> availableCipherNames() {
        return ciphers.keySet();
    }

    @Override
    public String encodeMessage(String message, String cipherName) throws IllegalArgumentException {
        Cipher cipher = ciphers.get(cipherName);
        String encodedMessage;
        if (cipher != null) {
            encodedMessage = cipher.encode(message);
        } else {
            throw new IllegalArgumentException(cipherName + " is not a valid cipher.");
        }

        return encodedMessage;
    }

    @Override
    public String decodeMessage(String message, String cipherName) throws IllegalArgumentException {
        Cipher cipher = ciphers.get(cipherName);
        String decodedMessage;
        if (cipher != null) {
            decodedMessage = cipher.decode(message);
        } else {
            throw new IllegalArgumentException(cipherName + " is not a valid cipher.");
        }

        return decodedMessage;
    }


}
