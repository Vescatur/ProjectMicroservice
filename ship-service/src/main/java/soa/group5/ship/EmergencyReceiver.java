package soa.group5.ship;

import org.jline.terminal.Terminal;
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

    @JmsListener(destination = "${queue.emergency}")
    public void receiveMessage(EmergencyResponse emergencyResponse) {
        terminal.writer().println();
        terminal.writer().write("Emergency received for Ship: " + emergencyResponse.getShipId() + " with emergency " + emergencyResponse.getType());
        terminal.writer().println();
        terminal.writer().flush();

        try {
            Thread.sleep(3 * 1000);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        DispatchTask task = new DispatchTask();
        task.setShipId(emergencyResponse.getShipId());
        task.setType(emergencyResponse.getType());
        task.setDispatched(true);
        jmsTemplate.convertAndSend(dispatchQueue, task);
    }
}
