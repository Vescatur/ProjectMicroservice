package soa.group5.emergency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import soa.group5.emergency.integrations.task.EmergencyTask;

@ShellComponent
public class EmergencyCommands {

    @Autowired private JmsTemplate jmsTemplate;
    @Value("${queue.emergency}") private String emergencyQueue;

    @ShellMethod("Post an emergency")
    public String emergency(
            long shipId,
      EmergencyType type
    ) {
        EmergencyTask task = new EmergencyTask();
        task.setShipId(shipId);
        task.setType(type);
        jmsTemplate.convertAndSend(emergencyQueue, task);

        return "Posted emergency";
    }

}
