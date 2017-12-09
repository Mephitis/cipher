package cipher.controller;

import cipher.CipherApplication;
import cipher.service.CipherService;
import cipher.types.Cipher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {CipherApplication.class})
public class CipherControllerIntegrationTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext wac;

    @Autowired
    @InjectMocks
    private CipherController controller;

    @Mock
    private CipherService service;

    @Before
    public void setUp() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void welcome() throws Exception {
        given(service.availableCipherNames()).willReturn(
                new HashSet<>(Arrays.asList("cipherName1", "cipherName2")));

        MvcResult result = mvc.perform(get("/")).andDo(print()).andExpect(view().name("cipher"))
                .andReturn();
        assertNotNull(result.getModelAndView().getModelMap().get("cipherNames"));
    }

    @Test
    public void encode() throws Exception {
        given(service.availableCipherNames()).willReturn(
                new HashSet<>(Arrays.asList("cipherName1", "cipherName2")));
        given(service.encodeMessage(anyString(), anyString())).willReturn("encodedMessage");

        MvcResult result = mvc.perform(post("/").param("encode", "encode").param("input",
                "message")
                .requestAttr("cipher", "cipherName")).andDo(print()).andExpect(
                view().name("cipher")).andReturn();
        assertNotNull(result.getModelAndView().getModelMap().get("cipherNames"));
        assertNotNull(result.getModelAndView().getModelMap().get("input"));
        assertNotNull(result.getModelAndView().getModelMap().get("output"));
        verify(service).encodeMessage(anyString(), anyString());
    }

    @Test
    public void decode() throws Exception {
        given(service.availableCipherNames()).willReturn(
                new HashSet<>(Arrays.asList("cipherName1", "cipherName2")));
        given(service.decodeMessage(anyString(), anyString())).willReturn("decodedMessage");

        MvcResult result = mvc.perform(post("/").param("decode", "decode").param("input",
                "message")
                .requestAttr("cipher", "cipherName")).andDo(print()).andExpect(
                view().name("cipher")).andReturn();
        assertNotNull(result.getModelAndView().getModelMap().get("cipherNames"));
        assertNotNull(result.getModelAndView().getModelMap().get("input"));
        assertNotNull(result.getModelAndView().getModelMap().get("output"));
        verify(service).decodeMessage(anyString(), anyString());
    }
}