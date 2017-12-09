package cipher.service;

import cipher.types.Cipher;
import cipher.types.Morse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DefaultCipherService.class, Morse.class})
public class DefaultCipherServiceTest {

    @Autowired
    @InjectMocks
    private CipherService cipherService;

    @Mock
    private Cipher cipher;

    @Mock
    private Map<String, Cipher> ciphers;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void availableCipherNames() throws Exception {
        given(ciphers.keySet()).willReturn(new HashSet<>(Arrays.asList("testName", "testName2")));

        Set<String> cipherNames = cipherService.availableCipherNames();

        assertFalse(cipherNames.isEmpty());
        assertEquals(2, cipherNames.size());
        assertTrue(cipherNames.contains("testName"));
    }

    @Test
    public void encodeMessage() throws Exception {
        given(ciphers.get(anyString())).willReturn(cipher);
        given(cipher.encode(anyString())).willReturn("encodedMessage");

        String message = cipherService.encodeMessage("message", "cipherName");

        verify(cipher).encode(anyString());
        assertEquals("encodedMessage", message);
    }

    @Test(expected = IllegalArgumentException.class)
    public void encodeMessageWrongCipherName() throws Exception{
        cipherService.encodeMessage("test", "wrong name");
    }

    @Test
    public void decodeMessage() throws Exception {
        given(ciphers.get(anyString())).willReturn(cipher);
        given(cipher.decode(anyString())).willReturn("decodedMessage");

        String message = cipherService.decodeMessage("message", "cipherName");

        verify(cipher).decode(anyString());
        assertEquals("decodedMessage", message);
    }

    @Test(expected = IllegalArgumentException.class)
    public void decodeMessageWrongCipherName() throws Exception {
        cipherService.decodeMessage("test", "wrong name");
    }
}