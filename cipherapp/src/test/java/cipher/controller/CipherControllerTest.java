package cipher.controller;


import cipher.CipherApplication;
import cipher.service.CipherService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.ModelMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CipherApplication.class})
public class CipherControllerTest {

    @Autowired
    @InjectMocks
    private CipherController controller;

    @Mock
    private CipherService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void welcome() throws Exception {
        ModelMap model = new ModelMap();
        given(service.availableCipherNames()).willReturn(
                new HashSet<>(Arrays.asList("cipherName1", "cipherName2")));

        String page = controller.welcome(model);

        assertEquals("cipher", page);
        assertNotNull(model.get("cipherNames"));
        assertTrue(model.get("cipherNames") instanceof Set);
        assertTrue(((Set)model.get("cipherNames")).contains("cipherName1"));
    }

    @Test
    public void encode() throws Exception {
        ModelMap model = new ModelMap();
        given(service.availableCipherNames()).willReturn(
                new HashSet<>(Arrays.asList("cipherName1", "cipherName2")));
        given(service.encodeMessage(anyString(), anyString())).willReturn("encodedMessage");

        String page = controller.encode("message", "cipherName1", model);

        assertEquals("cipher", page);
        assertNotNull(model.get("cipherNames"));
        assertTrue(model.get("cipherNames") instanceof Set);
        assertTrue(((Set)model.get("cipherNames")).contains("cipherName1"));
        assertEquals("message", model.get("input"));
        assertEquals("encodedMessage", model.get("output"));
    }

    @Test
    public void decode() throws Exception {
        ModelMap model = new ModelMap();
        given(service.availableCipherNames()).willReturn(
                new HashSet<>(Arrays.asList("cipherName1", "cipherName2")));
        given(service.decodeMessage(anyString(), anyString())).willReturn("decodedMessage");

        String page = controller.decode("message", "cipherName1", model);

        assertEquals("cipher", page);
        assertNotNull(model.get("cipherNames"));
        assertTrue(model.get("cipherNames") instanceof Set);
        assertTrue(((Set)model.get("cipherNames")).contains("cipherName1"));
        assertEquals("message", model.get("input"));
        assertEquals("decodedMessage", model.get("output"));
    }
}