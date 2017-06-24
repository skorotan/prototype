package prototype.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Domain properties
 */
@ConfigurationProperties(prefix = "domain.settings")
public class DomainProperties {

    /**
     * VM Disk type
     */
    private String diskType;

    /**
     * VM Disk source file
     */
    private String diskFile;

    /**
     * VM Disk driver type
     */
    private String diskDriverType;

    /**
     * VM Disk driver name
     */
    private String diskDriverName;

    /**
     * Guest OS source iso image
     */
    private String osSourceIso;

    /**
     * Guest OS driver iso image
     */
    private String osSourceDriver;

    /**
     * VNC connection autoport
     */
    private String vncConnectionAutoport;

    /**
     * VNC connection host
     */
    private String vncConnectionHost;

    /**
     * VNC connection port
     */
    private String vncConnectionPort;

    /**
     * Getter for disk type
     * @return vm disk type
     */
    public String getDiskType() {
        return diskType;
    }

    /**
     * Getter for disk source file
     * @return disk source file location
     */
    public String getDiskFile() {
        return diskFile;
    }

    /**
     * Getter for disk driver type
     * @return disk driver type
     */
    public String getDiskDriverType() {
        return diskDriverType;
    }

    /**
     * Getter for disk driver name
     * @return disk driver name
     */
    public String getDiskDriverName() {
        return diskDriverName;
    }

    /**
     * Getter for guest OS source iso file location
     * @return path to os source iso image location
     */
    public String getOsSourceIso() {
        return osSourceIso;
    }

    /**
     * Getter for guest os source driver
     * @return path to os driver iso image location
     */
    public String getOsSourceDriver() {
        return osSourceDriver;
    }

    /**
     * Getter for vnc connection autoport property
     * @return vnc connection autoport property
     */
    public String getVncConnectionAutoport() {
        return vncConnectionAutoport;
    }

    /**
     * Getter for vnc connection host
     * @return vnc connection host
     */
    public String getVncConnectionHost() {
        return vncConnectionHost;
    }

    /**
     * Getter for vnc connection port
     * @return vnc connection port
     */
    public String getVncConnectionPort() {
        return vncConnectionPort;
    }

    /**
     * Setter for domain disk type
     * @param diskType domain disk type to set
     */
    public void setDiskType(String diskType) {
        this.diskType = diskType;
    }

    /**
     * Setter for domain disk file
     * @param diskFile domain disk file to set
     */
    public void setDiskFile(String diskFile) {
        this.diskFile = diskFile;
    }

    /**
     * Setter for domain disk driver type
     * @param diskDriverType domain disk driver type to set
     */
    public void setDiskDriverType(String diskDriverType) {
        this.diskDriverType = diskDriverType;
    }

    /**
     * Setter for domain disk driver name
     * @param diskDriverName domain disk driver name to set
     */
    public void setDiskDriverName(String diskDriverName) {
        this.diskDriverName = diskDriverName;
    }

    /**
     * Setter for domain os source iso
     * @param osSourceIso domain os source iso to set
     */
    public void setOsSourceIso(String osSourceIso) {
        this.osSourceIso = osSourceIso;
    }

    /**
     * Setter for domain os source driver
     * @param osSourceDriver domain os source driver to set
     */
    public void setOsSourceDriver(String osSourceDriver) {
        this.osSourceDriver = osSourceDriver;
    }

    /**
     * Setter for domain vnc connection autoport
     * @param vncConnectionAutoport domain vnc connection autoport to set
     */
    public void setVncConnectionAutoport(String vncConnectionAutoport) {
        this.vncConnectionAutoport = vncConnectionAutoport;
    }

    /**
     * Setter for domain vnc connection host
     * @param vncConnectionHost domain vnc connection host to set
     */
    public void setVncConnectionHost(String vncConnectionHost) {
        this.vncConnectionHost = vncConnectionHost;
    }

    /**
     * Setter for domain vnc connection port
     * @param vncConnectionPort domain vnc connection port to set
     */
    public void setVncConnectionPort(String vncConnectionPort) {
        this.vncConnectionPort = vncConnectionPort;
    }
}
