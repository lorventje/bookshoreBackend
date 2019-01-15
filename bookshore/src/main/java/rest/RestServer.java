package rest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RestServer {
    public static final int SERVER_PORT = 8090;

    public static void main(String[] args) {

        Server server = configuredServer(SERVER_PORT);

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            Logger.getLogger(RestServer.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            server.destroy();
        }
    }

    public static Server configuredServer(int port) {
        ResourceConfig config = createResourceConfig();

        ServletHolder servlet = new ServletHolder(new ServletContainer(config));

        Server server = new Server(port);

        ServletContextHandler context = new ServletContextHandler(server, "/*");
        context.addServlet(servlet, "/*");
        return server;
    }

    protected static ResourceConfig createResourceConfig() {
        final ResourceConfig rc = new ResourceConfig();
        rc.packages("rest");
        rc.register(new RestServerCorsFilter());
        return rc;
    }
}