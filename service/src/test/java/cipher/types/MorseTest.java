package cipher.types;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MorseTest {

    private Cipher morse;

    @Before
    public void setUp() throws Exception {
        morse = new Morse();
    }

    @Test
    public void encodeWord() throws Exception {
        String encoded = morse.encode("test");
        assertEquals("- . ... -", encoded);
    }

    @Test
    public void encodeSentence() throws Exception {
        String encoded = morse.encode("test sentence");
        assertEquals("- . ... - | ... . -. - . -. -.-. .", encoded);
    }

    @Test
    public void encodeMultilineSentence() throws Exception {
        String encoded = morse.encode("test\r\nsentence");
        assertEquals("- . ... - \n... . -. - . -. -.-. .", encoded);
    }

    @Test
    public void encodeEmpty() throws Exception {
        String encoded = morse.encode("");
        assertEquals("", encoded);
    }

    @Test
    public void encodeNull() throws Exception {
        String encoded = morse.encode(null);
        assertEquals("", encoded);
    }

    @Test
    public void encodeErrorChar() throws Exception {
        String encoded = morse.encode("^");
        assertEquals("........", encoded);
    }

    @Test
    public void decodeWord() throws Exception {
        String encoded = morse.decode("- . ... -");
        assertEquals("test", encoded);
    }

    @Test
    public void decodeSentence() throws Exception {
        String encoded = morse.decode("- . ... - | ... . -. - . -. -.-. .");
        assertEquals("test sentence", encoded);
    }

    @Test
    public void decodeMultilineSentence() throws Exception {
        String encoded = morse.decode("- . ... -\r\n... . -. - . -. -.-. .");
        assertEquals("test\nsentence", encoded);
    }

    @Test
    public void decodeEmpty() throws Exception {
        String encoded = morse.decode("");
        assertEquals("", encoded);
    }

    @Test
    public void decodeNull() throws Exception {
        String encoded = morse.decode(null);
        assertEquals("", encoded);
    }

    @Test
    public void decodeErrorChar() throws Exception {
        String encoded = morse.decode("........");
        assertEquals("~", encoded);
    }

    @Test
    public void decodeUnrecognizedMorse() throws Exception {
        String encoded = morse.decode("........--..");
        assertEquals("~", encoded);
    }
}