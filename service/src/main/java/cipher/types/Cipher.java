package cipher.types;

public interface Cipher {

    /**
     * Encodes message.
     *
     * @param message
     *         {@code String} to be encoded.
     * @return encoded version of original message.In case the original codedMessage was null
     * empty {@code String} is returned.
     */
    String encode(String message);

    /**
     * Decodes message.
     *
     * @param codedMessage
     *         {@code String} containing coded message.
     * @return decoded message. In case the original codedMessage was null
     * empty {@code String} is returned.
     */
    String decode(String codedMessage);
}
