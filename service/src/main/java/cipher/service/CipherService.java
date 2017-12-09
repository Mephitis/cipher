package cipher.service;

import java.util.Set;

public interface CipherService {

    /**
     * Gets names of available ciphers.
     *
     * @return {@code Set} of available cipher names.
     */
    Set<String> availableCipherNames();

    /**
     * Encodes the message with selected cipher.
     *
     * @param message
     *         text to encode
     * @param cipherName
     *         name of the cipher to use for encoding
     * @return encoded message
     * @throws IllegalArgumentException
     *         in case the provided cipher name is not recognized.
     */
    String encodeMessage(String message, String cipherName) throws IllegalArgumentException;

    /**
     * Decodes the message with selected cipher.
     *
     * @param message
     *         text to decode
     * @param cipherName
     *         name of the cipher to use for decoding
     * @return decoded message
     * @throws IllegalArgumentException
     *         in case the provided cipher name is not recognized.
     */
    String decodeMessage(String message, String cipherName) throws IllegalArgumentException;
}
