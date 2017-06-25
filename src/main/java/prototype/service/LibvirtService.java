package prototype.service;

import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

/**
 * Service that provides connection to Hypervisor via libvirt lib.
 */
@Service
public abstract class LibvirtService {

    /**
     * Logger
     */
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Domain service
     */
    @Autowired
    private DomainService domainService;

    /**
     * Method provides sequence of actions to start windows vm with given MAC-address
     * @param macAddress MAC address for VM
     * @return status of VM
     * @throws LibvirtException
     */
    public String startVm(String macAddress) throws LibvirtException {
        LOGGER.debug("Method startVm was invoked with {} macAddress", macAddress);
        String status = null;
        Connect conn = getConnection();
        try {
            Domain domain = domainService.getDomainByMac(conn, macAddress);
            if(domain == null){
                conn.domainCreateXML(domainService.prepareDomainData(macAddress), 0);
                status = "Booting";
            } else {
                if (domainService.isDomainStopped(domain)) {
                    domain.create();
                    status = "Started";
                } else if (domainService.isDomainRunning(domain)) {
                    status = "Running";
                } else if (domainService.isDomainInErrorState(domain)) {
                    status = "In error state";
                } else {
                    status = "Not supported";
                }
            }
        } finally {
            conn.close();
        }
        LOGGER.debug("Method startVm returned {} as status", status);
        return status;
    }

    /**
     * Method provides sequence of actions to check status of VM.
     * @param macAddress MAC address of VM to check
     * @return VM status
     * @throws LibvirtException
     */
    public String status(String macAddress) throws LibvirtException {
        LOGGER.debug("Method status was invoked with {} macAddress", macAddress);
        String status = null;
        Connect conn = getConnection();
        try {
            Domain domain = domainService.getDomainByMac(conn, macAddress);
            if(domain == null){
                status = "Not exist";
            } else {
                if (domainService.isDomainStopped(domain)) {
                    status = "Stopped";
                } else if (domainService.isDomainRunning(domain)) {
                    status = "Running";
                } else if (domainService.isDomainInErrorState(domain)) {
                    status = "In error state";
                } else {
                    status = "Not supported";
                }
            }
        } finally {
            conn.close();
        }
        LOGGER.debug("Method startVm returned {} as status", status);
        return status;
    }

    /**
     * Method provides sequence of actions to stop VM with given MAC-address
     * @param macAddress MAC address of VM to stop
     * @return status of VM
     * @throws LibvirtException
     */
    public String stopVm(String macAddress) throws LibvirtException {
        LOGGER.debug("Method stopVm was invoked with {} macAddress", macAddress);
        Connect conn = getConnection();
        String status = null;
        try {
            conn = new Connect("qemu+tcp://192.168.237.128/system");
            Domain domain = domainService.getDomainByMac(conn, macAddress);
            if(domain == null){
                status = "Not exist";
            } else {
                if (domainService.isDomainStopped(domain)) {
                    status = "Already stopped";
                } else if (domainService.isDomainRunning(domain)) {
                    domain.destroy();
                    status = "Command to stop domain was sent";
                } else if (domainService.isDomainInErrorState(domain)) {
                    status = "In error state";
                } else {
                    status = "Not supported";
                }
            }
        } finally {
            conn.close();
        }
        LOGGER.debug("Method startVm returned {} as status", status);
        return status;
    }

    /**
     * Method that provides appropriate injection of Connection prototype-scoped bean
     * @return Connection bean
     */
    @Lookup
    protected abstract Connect getConnection();

}
