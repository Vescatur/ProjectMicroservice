package soa.group5.ship;

import org.jline.terminal.Terminal;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import soa.group5.ship.integrations.response.EmergencyResponse;
import soa.group5.ship.integrations.task.DispatchTask;

@Component
public class EmergencyReceiver {

    @Autowired
    private Terminal terminal;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${queue.dispatch}")
    private String dispatchQueue;
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(JmsConfiguration.class);

    @JmsListener(destination = "${queue.emergency}")
    public void receiveMessage(EmergencyResponse emergencyResponse) {
        // terminal.writer().println();
        // terminal.writer().write("Emergency received for Ship: " + emergencyResponse.getShipId() + " with emergency " + emergencyResponse.getType());
        // terminal.writer().println();
        // terminal.writer().flush();
        log.info("Emergency received for Ship: " + emergencyResponse.getShipId() + " with emergency " + emergencyResponse.getType());

        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        log.info("SENT OUT DISPATCHMENT");
        DispatchTask task = new DispatchTask();
        task.setShipId(emergencyResponse.getShipId());
        task.setType(emergencyResponse.getType());
        task.setDispatched(true);
        jmsTemplate.convertAndSend(dispatchQueue, task);
    }
}
