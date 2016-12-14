package gd.droplets;

import atg.servlet.DynamoHttpServletRequest;
import atg.servlet.DynamoHttpServletResponse;
import atg.servlet.DynamoServlet;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * @author Alex Aksenov on 12/12/2016.
 */
public class ExampleDroplet extends DynamoServlet {

    @Override
    public void service(DynamoHttpServletRequest request, DynamoHttpServletResponse response) throws ServletException, IOException {

        request.setParameter("test", "test");
        request.serviceParameter("test_service", request, response);
    }
}
