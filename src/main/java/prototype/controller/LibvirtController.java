package prototype.controller;

import org.libvirt.LibvirtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import prototype.api.Response;
import prototype.api.ResponseOk;
import prototype.service.LibvirtService;

import javax.validation.constraints.Pattern;

/**
 * Libvirt controller
 */
@RestController
@Validated
public class LibvirtController {

    /**
     * Logger
     */
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    /**
     * Regular expression to validate MAC-address
     */
    private final static String MAC_REGEX = "^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$";

    /**
     * Message that should be shown in case of wrong MAC-address format
     */
    private final static String ERROR_MESSAGE = "Given mac address has wrong wrong format";

    @Autowired
    private LibvirtService connection;

    @GetMapping(path = "/api/vm/start/{macAddress}")
    @ResponseBody
    public Response start(@PathVariable
                          @Pattern(regexp = MAC_REGEX,
                                   message = ERROR_MESSAGE) String macAddress) throws LibvirtException {
        LOGGER.info("Method start was invoked with {} as macAddress parameter", macAddress);
        String status = connection.startVm(macAddress);
        Response response = new ResponseOk(status);
        LOGGER.info("Method start returned {} as response", response);
        return response;
    }

    @GetMapping(path = "/api/vm/status/{macAddress}")
    @ResponseBody
    public Response status(@PathVariable
                             @Pattern(regexp = MAC_REGEX,
                                      message = ERROR_MESSAGE) String macAddress) throws LibvirtException {
        LOGGER.info("Method status was invoked with {} as macAddress parameter", macAddress);
        String status =  connection.status(macAddress);
        Response response =  new ResponseOk(status);
        LOGGER.info("Method status returned {} as response", response);
        return response;
    }

    @GetMapping(path = "/api/vm/stop/{macAddress}")
    @ResponseBody
    public Response stop(@PathVariable
                         @Pattern(regexp = MAC_REGEX,
                                  message = ERROR_MESSAGE) String macAddress) throws LibvirtException {
        LOGGER.info("Method stop was invoked with {} as macAddress parameter", macAddress);
        String status =  connection.stopVm(macAddress);
        Response response =  new ResponseOk(status);
        LOGGER.info("Method stop returned {} as response", response);
        return response;
    }
}
