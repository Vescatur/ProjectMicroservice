package soa.group5.emergency;

import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import soa.group5.emergency.integrations.response.DispatchResponse;

@Component
public class DispatchReceiver {

    @Autowired
    private Terminal terminal;

    @JmsListener(destination = "${queue.dispatch}")
    public void receiveMessage(DispatchResponse dispatchResponse) {
        terminal.writer().println();
        terminal.writer().write("Dispatchment for ship " + dispatchResponse.getShipId() + " with emergency " + dispatchResponse.getType() + ": " + dispatchResponse.isDispatched());
        terminal.writer().println();
        terminal.writer().flush();
    }
}
