package prototype.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Libvirt connection properties.
 */
@ConfigurationProperties(prefix = "libvirt.connection")
public class LibvirtConnectionProperties {

    /**
     * Type
     */
    private String type;

    /**
     * Uri
     */
    private String uri;

    /**
     * Getter for libvirt connection type
     * @return connection type
     */
    public String getType() {
        return type;
    }

    /**
     * Getter for libvirt connection URI
     * @return URI
     */
    public String getUri() {
        return uri;
    }

    /**
     * Setter for type
     * @param type type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Setter for uri
     * @param uri uri to set
     */
    public void setUri(String uri) {
        this.uri = uri;
    }
}
