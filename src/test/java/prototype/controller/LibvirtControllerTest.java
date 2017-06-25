package prototype.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import prototype.service.LibvirtService;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by skorotan on 25.06.2017.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = LibvirtController.class)
public class LibvirtControllerTest {

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibvirtService libvirtService;

    @Before
    public void setup() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void start_correctMac1() throws Exception {
        Mockito.when(libvirtService.startVm("00:50:56:C0:00:08")).thenReturn("Booting");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/vm/start/00:50:56:C0:00:08").accept(
                MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("{\"status\":\"status ok\",\"message\":\"Booting\"}"));
    }

    @Test
    public void start_wrongMac1() throws Exception {
        Mockito.when(libvirtService.startVm("005056C00008")).thenReturn("Booting");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/vm/start/005056C00008").accept(
                MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("{\"status\":\"error\",\"message\":[\"Given mac address has wrong wrong format\"]}"));
    }

    @Test
    public void start_wrongMac2() throws Exception {
        Mockito.when(libvirtService.startVm("00-50-56-C0-00-08")).thenReturn("Booting");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/vm/start/005056C00008").accept(
                MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("{\"status\":\"error\",\"message\":[\"Given mac address has wrong wrong format\"]}"));
    }

    @Test
    public void start_wrongMac3() throws Exception {
        mockMvc.perform(get("/api/vm/start/00:50:56:C0:KK:08"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("{\"status\":\"error\",\"message\":[\"Given mac address has wrong wrong format\"]}"));
    }

    @Test
    public void status_correctMac() throws Exception {
        Mockito.when(libvirtService.status("00:50:56:C0:00:08")).thenReturn("Running");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/vm/status/00:50:56:C0:00:08").accept(
                MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("{\"status\":\"status ok\",\"message\":\"Running\"}"));
    }

    @Test
    public void status_wrongMac() throws Exception {
        mockMvc.perform(get("/api/vm/status/00:50:56:C0$66:08"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("{\"status\":\"error\",\"message\":[\"Given mac address has wrong wrong format\"]}"));
    }

    @Test
    public void stop_correctMac() throws Exception {
        Mockito.when(libvirtService.stopVm("00:50:56:C0:00:08")).thenReturn("Already stopped");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/api/vm/stop/00:50:56:C0:00:08").accept(
                MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("{\"status\":\"status ok\",\"message\":\"Already stopped\"}"));
    }

    @Test
    public void stop_wrongMac() throws Exception {
        mockMvc.perform(get("/api/vm/stop/00:50:56:C0@66:08"))
                .andExpect(status().is4xxClientError())
                .andExpect(content().contentType(contentType))
                .andExpect(content().string("{\"status\":\"error\",\"message\":[\"Given mac address has wrong wrong format\"]}"));
    }

}