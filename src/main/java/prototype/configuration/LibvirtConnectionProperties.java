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

    private String diskType;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
