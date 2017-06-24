package prototype.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Domain properties
 */
@ConfigurationProperties(prefix = "domain.settings")
public class DomainProperties {

    private Disk disk;

    private OsSource osSource;

    private VncConnection vncConnection;

    public static class Disk {
        /**
         * VM Disk type
         */
        private String type;

        /**
         * VM Disk source file
         */
        private String file;

        /**
         * VM Disk driver type
         */
        private String driverType;

        /**
         * VM Disk driver name
         */
        private String driverName;

        /**
         * Getter for disk type
         * @return vm disk type
         */
        public String getType() {
            return type;
        }

        /**
         * Getter for disk source file
         * @return disk source file location
         */
        public String getFile() {
            return file;
        }

        /**
         * Getter for disk driver type
         * @return disk driver type
         */
        public String getDriverType() {
            return driverType;
        }

        /**
         * Getter for disk driver name
         * @return disk driver name
         */
        public String getDriverName() {
            return driverName;
        }

        /**
         * Setter for domain disk type
         * @param type domain disk type to set
         */
        public void setType(String type) {
            this.type = type;
        }

        /**
         * Setter for domain disk file
         * @param file domain disk file to set
         */
        public void setFile(String file) {
            this.file = file;
        }

        /**
         * Setter for domain disk driver type
         * @param driverType domain disk driver type to set
         */
        public void setDriverType(String driverType) {
            this.driverType = driverType;
        }

        /**
         * Setter for domain disk driver name
         * @param driverName domain disk driver name to set
         */
        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }
    }

    public static class OsSource{
        /**
         * Guest OS source iso image
         */
        private String iso;

        /**
         * Guest OS driver iso image
         */
        private String driver;

        /**
         * Getter for guest OS source iso file location
         * @return path to os source iso image location
         */
        public String getIso() {
            return iso;
        }

        /**
         * Getter for guest os source driver
         * @return path to os driver iso image location
         */
        public String getDriver() {
            return driver;
        }

        /**
         * Setter for domain os source iso
         * @param iso domain os source iso to set
         */
        public void setIso(String iso) {
            this.iso = iso;
        }

        /**
         * Setter for domain os source driver
         * @param driver domain os source driver to set
         */
        public void setDriver(String driver) {
            this.driver = driver;
        }
    }

    public static class VncConnection {
        /**
         * VNC connection autoport
         */
        private String autoport;

        /**
         * VNC connection host
         */
        private String host;

        /**
         * VNC connection port
         */
        private String port;

        /**
         * Getter for vnc connection autoport property
         * @return vnc connection autoport property
         */
        public String getAutoport() {
            return autoport;
        }

        /**
         * Getter for vnc connection host
         * @return vnc connection host
         */
        public String getHost() {
            return host;
        }

        /**
         * Getter for vnc connection port
         * @return vnc connection port
         */
        public String getPort() {
            return port;
        }

        /**
         * Setter for domain vnc connection autoport
         * @param autoport domain vnc connection autoport to set
         */
        public void setAutoport(String autoport) {
            this.autoport = autoport;
        }

        /**
         * Setter for domain vnc connection host
         * @param host domain vnc connection host to set
         */
        public void setHost(String host) {
            this.host = host;
        }

        /**
         * Setter for domain vnc connection port
         * @param port domain vnc connection port to set
         */
        public void setPort(String port) {
            this.port = port;
        }
    }

    /**
     * Getter for disk related properties
     * @return disk properties class
     */
    public Disk getDisk() {
        return disk;
    }

    /**
     * Setter for disk related properties
     * @param disk disk properties class
     */
    public void setDisk(Disk disk) {
        this.disk = disk;
    }

    /**
     * Getter for os source related properties
     * @return os source properties class
     */
    public OsSource getOsSource() {
        return osSource;
    }

    /**
     * Setter for os source related properties
     * @param osSource os source properties class
     */
    public void setOsSource(OsSource osSource) {
        this.osSource = osSource;
    }

    /**
     * Getter for vnc connection related properties
     * @return vnc connection properties class
     */
    public VncConnection getVncConnection() {
        return vncConnection;
    }

    /**
     * Setter for vnc connection related properties
     * @param vncConnection vnc connection properties class
     */
    public void setVncConnection(VncConnection vncConnection) {
        this.vncConnection = vncConnection;
    }
}
