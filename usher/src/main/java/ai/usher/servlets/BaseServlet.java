package ai.usher.servlets;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ai.usher.exceptions.NotImplementedException;
import ai.usher.exceptions.Xception;

public class BaseServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            this.handleGet(request, response);
        } catch (Xception ex) {
            response.setStatus(ex.getCode());
            response.setContentType("text/plain");
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            this.handlePost(request, response);
        } catch (Xception ex) {
            response.setStatus(ex.getCode());
            response.setContentType("text/plain");
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            this.handlePut(request, response);
        } catch (Xception ex) {
            response.setStatus(ex.getCode());
            response.setContentType("text/plain");
            response.getWriter().write(ex.getMessage());
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            this.handleDelete(request, response);
        } catch (Xception ex) {
            response.setStatus(ex.getCode());
            response.setContentType("text/plain");
            response.getWriter().write(ex.getMessage());
        }
    }

    protected void handleGet(HttpServletRequest request, HttpServletResponse response) throws Xception, IOException {
        throw new NotImplementedException();
    }

    protected void handlePost(HttpServletRequest request, HttpServletResponse response) throws Xception, IOException {
        throw new NotImplementedException();
    }

    protected void handlePut(HttpServletRequest request, HttpServletResponse response) throws Xception, IOException {
        throw new NotImplementedException();
    }

    protected void handleDelete(HttpServletRequest request, HttpServletResponse response) throws Xception, IOException {
        throw new NotImplementedException();
    }

}
