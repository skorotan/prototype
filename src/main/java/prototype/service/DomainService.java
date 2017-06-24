package prototype.service;

import org.libvirt.Connect;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import prototype.configuration.DomainProperties;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Stateless service that provides interaction with domain.
 */
@Service
public class DomainService {

    /**
     * Logger
     */
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DomainProperties properties;

    /**
     * XML template for Windows VM
     */
    @Value("classpath:windows-image")
    private Resource res;

    /**
     * Method provides sequence of actions to get domain by MAC-address
     * @param conn connection bean
     * @param mac MAC-address of VM to search domain
     * @return domain
     * @throws LibvirtException
     */
    public Domain getDomainByMac(Connect conn, String mac) throws LibvirtException {
        LOGGER.debug("Method getDomainByMac was invoked with {} as conn parameter and {} as mac", conn, mac);
        int[] domainIds = conn.listDomains();
        Domain domain = null;
        for(int domainId : domainIds){
            Domain currentDomain = conn.domainLookupByID(domainId);
            String domainMac = getDomainMac(currentDomain);
            LOGGER.debug("MAC-address for current domain is {} ", domainMac);
            if(mac.equalsIgnoreCase(domainMac)){
                domain = currentDomain;
            }
        }
        LOGGER.debug("Method getDomainByMac returned {} as domain", domain);
        return domain;
    }

    /**
     * Method provides sequence of actions to get Domain's MAC-address
     * @param domain MAC-address of this domain will be exstracted
     * @return MAC-address
     * @throws LibvirtException
     */
    public String getDomainMac(Domain domain) throws LibvirtException {
        LOGGER.debug("Method getDomainMac was invoked with {} as domain", domain);
        String domainDescr = domain.getXMLDesc(0);
        Pattern p = Pattern.compile("<mac address='(.+)'/>");
        Matcher m = p.matcher(domainDescr);
        String domainMac = m.find() ? m.group(1) : null;
        LOGGER.debug("Method getDomainMac returned {} as domainMac", domainMac);
        return domainMac;
    }

    /**
     * Method checks whether the given domain is active
     * @param domain domain to check
     * @return true if domain is active, false otherwise
     * @throws LibvirtException
     */
    public boolean isDomainActive(Domain domain) throws LibvirtException {
        LOGGER.debug("Method isDomainActive was invoked with {} as domain", domain);
        boolean isDoaminActive = domain.isActive() != 0;
        LOGGER.debug("Method getDomainMac returned {} as isDoaminActive", isDoaminActive);
        return isDoaminActive;
    }

    /**
     * Method checks whether the given domain is in error state
     * @param domain domain to check
     * @return true if domain is in error state, false otherwise
     * @throws LibvirtException
     */
    public boolean isDomainInErrorState(Domain domain) throws LibvirtException {
        LOGGER.debug("Method isDomainInErrorState was invoked with {} as domain", domain);
        boolean isDomainInErrorState = domain.isActive() != -1;
        LOGGER.debug("Method getDomainMac returned {} as isDomainInErrorState", isDomainInErrorState);
        return isDomainInErrorState;
    }

    /**
     * Method checks whether the given domain is running
     * @param domain domain to check
     * @return true if domain is running, false otherwise
     * @throws LibvirtException
     */
    public boolean isDomainRunning(Domain domain) throws LibvirtException {
        LOGGER.debug("Method isDomainRunning was invoked with {} as domain", domain);
        boolean isDomainRunning = domain.isActive() != 1;
        LOGGER.debug("Method getDomainMac returned {} as isDomainRunning", isDomainRunning);
        return isDomainRunning;
    }

    /**
     * Method prepares the domain data based on given template and parameters
     * @param mac MAC-address for new VM
     * @return String with ready to start XML-configuration of new domain
     */
    public String prepareDomainData(String mac){
        LOGGER.debug("Method prepareDomainData was invoked with {} as domain", mac);
        try {
            String domainData =  new BufferedReader(new InputStreamReader(res.getInputStream()))
                    .lines().parallel().collect(Collectors.joining("\n"));
            domainData = domainData.replaceAll("&vmMac", "'" + mac + "'");
            domainData = domainData.replaceAll("&vmName", "vmName_" + mac);
            domainData = domainData.replaceAll("&vmUUID", String.valueOf(UUID.randomUUID()));
            domainData = domainData.replaceAll("&diskType", properties.getDiskType());
            domainData = domainData.replaceAll("&diskDriverName", properties.getDiskDriverName());
            domainData = domainData.replaceAll("&diskDriverType", properties.getDiskDriverType());
            domainData = domainData.replaceAll("&diskSourceFile", properties.getDiskFile());
            domainData = domainData.replaceAll("&osSourceIso", properties.getOsSourceIso());
            domainData = domainData.replaceAll("&osSourceDriverIso", properties.getOsSourceDriver());
            domainData = domainData.replaceAll("&vncConnectionAutoport", properties.getVncConnectionAutoport());
            domainData = domainData.replaceAll("&vncConnectionHost", properties.getVncConnectionHost());
            domainData = domainData.replaceAll("&vncConnectionPort", properties.getVncConnectionPort());
            LOGGER.info("Method getDomainMac returned {} as domainData", domainData);
            return domainData;
        } catch (IOException e) {
            LOGGER.error("Method getDomainMac threw an exception: {}", e);
            throw new NullPointerException();
        }
    }
}
