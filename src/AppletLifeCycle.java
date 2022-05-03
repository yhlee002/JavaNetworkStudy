import java.applet.Applet;
import java.awt.*;

public class AppletLifeCycle extends Applet {

    public String state = "";

    public void init() {
        state += "init, ";
    }

    public void start() {
        state += "start, ";
    }

    public void stop() {
        state += "stop, ";
    }

    public void destroy() {
        state += "destroy, ";
    }

    public void paint(Graphics graphics) {
        state += "paint, ";
        graphics.drawString(state, 20, 50);
    }
}
