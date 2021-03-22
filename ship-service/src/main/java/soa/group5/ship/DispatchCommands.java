package soa.group5.ship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import soa.group5.ship.integrations.task.DispatchTask;

@ShellComponent
public class DispatchCommands {

    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${queue.dispatch}")
    private String dispatchQueue;

    @ShellMethod("Post an dispatch")
    public String emergency(
            long shipId,
            String type,
            boolean dispatched
    ) {
        DispatchTask task = new DispatchTask();
        task.setShipId(shipId);
        task.setType(type);
        task.setDispatched(true);
        jmsTemplate.convertAndSend(dispatchQueue, task);

        return "Posted emergency";
    }

}
