package prototype.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.libvirt.Connect;
import org.libvirt.Domain;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Libvirt service testsuite
 */
@RunWith(SpringRunner.class)
public class LibvirtServiceTest {

    private LibvirtService libvirtService;

    @Mock
    private DomainService domainService;

    @Mock
    private Connect connect;

    @Mock
    private Domain domain;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        libvirtService = new LibvirtService(domainService) {
            @Override
            protected Connect getConnection() {
                return connect;
            }
        };
    }

    @Test
    public void startVm_withStoppedDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(true);

        String response = libvirtService.startVm("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Started")));
    }

    @Test
    public void startVm_withNotFoundDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(null);

        String response = libvirtService.startVm("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Booting")));
    }

    @Test
    public void startVm_withRunningDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(false);
        when(domainService.isDomainRunning(domain)).thenReturn(true);

        String response = libvirtService.startVm("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Running")));
    }

    @Test
    public void startVm_withInErrorStateDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(false);
        when(domainService.isDomainRunning(domain)).thenReturn(false);
        when(domainService.isDomainInErrorState(domain)).thenReturn(true);

        String response = libvirtService.startVm("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("In error state")));
    }

    @Test
    public void startVm_withNotSupportedDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(false);
        when(domainService.isDomainRunning(domain)).thenReturn(false);
        when(domainService.isDomainInErrorState(domain)).thenReturn(false);

        String response = libvirtService.startVm("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Not supported")));
    }

    @Test
    public void status_withStoppedDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(true);

        String response = libvirtService.status("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Stopped")));
    }

    @Test
    public void status_withNotFoundDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(null);

        String response = libvirtService.status("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Not exist")));
    }

    @Test
    public void status_withRunningDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(false);
        when(domainService.isDomainRunning(domain)).thenReturn(true);

        String response = libvirtService.status("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Running")));
    }

    @Test
    public void status_withInErrorStateDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(false);
        when(domainService.isDomainRunning(domain)).thenReturn(false);
        when(domainService.isDomainInErrorState(domain)).thenReturn(true);

        String response = libvirtService.status("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("In error state")));
    }

    @Test
    public void status_withNotSupportedDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(false);
        when(domainService.isDomainRunning(domain)).thenReturn(false);
        when(domainService.isDomainInErrorState(domain)).thenReturn(false);

        String response = libvirtService.status("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Not supported")));
    }

    @Test
    public void stopVm_withStoppedDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(true);

        String response = libvirtService.stopVm("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Already stopped")));
    }

    @Test
    public void stopVm_withNotFoundDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(null);

        String response = libvirtService.stopVm("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Not exist")));
    }

    @Test
    public void stopVm_withRunningDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(false);
        when(domainService.isDomainRunning(domain)).thenReturn(true);

        String response = libvirtService.stopVm("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Command to stop domain was sent")));
    }

    @Test
    public void stopVm_withInErrorStateDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(false);
        when(domainService.isDomainRunning(domain)).thenReturn(false);
        when(domainService.isDomainInErrorState(domain)).thenReturn(true);

        String response = libvirtService.stopVm("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("In error state")));
    }

    @Test
    public void stopVm_withNotSupportedDomain() throws Exception {
        when(domainService.getDomainByMac(connect, "50-fa-34-a2-4a-48")).thenReturn(domain);
        when(domainService.isDomainActive(domain)).thenReturn(false);
        when(domainService.isDomainRunning(domain)).thenReturn(false);
        when(domainService.isDomainInErrorState(domain)).thenReturn(false);

        String response = libvirtService.stopVm("50-fa-34-a2-4a-48");

        assertThat(response, is(equalTo("Not supported")));
    }

}