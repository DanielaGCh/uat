package com.smartsoft.uat.seguridad;

import com.smartsoft.uat.controller.SesionController;
import java.io.IOException;
import javax.faces.application.ResourceHandler;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class AuthorizationFilter implements Filter {

    private static final String FACES_REDIRECT_XML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + "<partial-response><redirect url=\"%s\"></redirect></partial-response>";
    @Inject
    private SesionController sessionController;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        res.setHeader("X-XSS-Protection", "1; mode=block");
        res.addHeader("X-FRAME-OPTIONS", "DENY");
        res.addHeader("X-Content-Type-Options", "nosniff");
        res.addHeader("Strict-Transport-Security", "max-age=31536000");
        res.addHeader("Pragma", "no-cache");
        res.addHeader("Cache-Control", "no-cache");
        res.addHeader("Cache-Control", "must-revalidate");

        if (req.getRequestURL().toString().endsWith("login.xhtml") || req.getRequestURL().toString().endsWith("recuperarContrasena.xhtml") || req.getRequestURL().toString().endsWith("accesoDenegado.xhtml")) {
            chain.doFilter(request, response);
            //||req.getRequestURL().toString().endsWith("registrarse.xhtml")
            return;
        }
        if (req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)
                || req.getRequestURL().toString().endsWith("recuperarContrasena.xhtml")
                || req.getRequestURL().toString().endsWith("cambiarContrasena.xhtml")
                || req.getRequestURL().toString().endsWith("error.xhtml")
                || req.getRequestURL().toString().endsWith("configuracion.xhtml")
                || req.getRequestURL().toString().endsWith("repartidores.xhtml")
                || req.getRequestURL().toString().endsWith("catClientes.xhtml")
                || req.getRequestURI().startsWith(req.getContextPath() + "/webresources")
                || req.getRequestURI().startsWith(req.getContextPath() + "/recuperarContrasenia")
                || req.getRequestURI().startsWith(req.getContextPath() + "/cambiarContrasena")
                || req.getRequestURI().startsWith(req.getContextPath() + "/errorSolicitudInactiva")
                || req.getRequestURI().startsWith(req.getContextPath() + "/resources/less/layout/atlas-layout.less")
                || req.getRequestURI().startsWith(req.getContextPath() + "/resources/")) {

            chain.doFilter(request, response);
            return;
        }
        if (req.getRequestURI().startsWith(req.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER)) {
            chain.doFilter(request, response); //obtener imagenes
            return;
        }
        if (sessionController.getView() == null) {
            res.sendRedirect(((HttpServletRequest) request).getContextPath() + "/login.xhtml");
            return;
        } else {

            if (sessionController.getView().getUsuario() == null) {
                res.sendRedirect(((HttpServletRequest) request).getContextPath() + "/login.xhtml");
                return;
            }

            if (sessionController.getView().getUsuario().getId() == null ) {
                res.sendRedirect(((HttpServletRequest) request).getContextPath() + "/login.xhtml");
                return;
            } else {
                try {
                    chain.doFilter(request, response);
                } catch (Exception e) {

                    e.printStackTrace();
                    res.sendRedirect(((HttpServletRequest) request).getContextPath() + "/error.xhtml");
                    return;
                }
            }
        }
    }

    @Override
    public void destroy() {
    }

    public Integer obtenerCookies(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        Integer correo = (Integer) httpSession.getAttribute("cU");
        return correo;
    }
}
