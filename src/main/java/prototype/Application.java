package prototype;

import org.libvirt.Connect;
import org.libvirt.LibvirtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import prototype.configuration.LibvirtConnectionProperties;

import java.util.Properties;

/**
 * Main application configuration file
 */
@SpringBootApplication
@EnableConfigurationProperties(LibvirtConnectionProperties.class)
public class Application extends SpringBootServletInitializer {

    /**
     * Libvirt connection properties
     */
    @Autowired
    private LibvirtConnectionProperties properties;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    /**
     * Method returns Bean that represents connection to hypervisor via libvirt
     * @return Connection bean
     * @throws LibvirtException
     */
    @Bean
    @Scope(scopeName = "prototype")
    public Connect getConnection() throws LibvirtException {
        return new Connect(properties.getType() + "//" + properties.getUri());
    }
}
