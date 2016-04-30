package com.vit.webapp.action;

import com.opensymphony.xwork2.ActionSupport;
import com.vit.Constants;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Implementation of <strong>ActionSupport</strong> that contains
 * convenience methods for subclasses.  For example, getting the current
 * user and saving messages/errors. This class is intended to
 * be a base class for all Action classes.
 *
 * @author <a href="mailto:matt@raibledesigns.com">Matt Raible</a>
 *         <<<<<<< HEAD
 *         Created by kashishsinghal on 29/10/15.
 */
public class BaseAction extends ActionSupport {

    /**
     * Constant for cancel result String
     */
    public static final String CANCEL = "cancel";
    private static final long serialVersionUID = 3525445612504421307L;
    /**
     * Transient log to prevent session synchronization issues - children can use instance for logging.
     */
    protected final transient Log log = LogFactory.getLog(getClass());
    /**
     * Indicator if the user clicked cancel
     */
    protected String cancel;

    /**
     * Indicator for the page the user came from.
     */
    protected String from;

    /**
     * Set to "delete" when a "delete" request parameter is passed in
     */
    protected String delete;

    /**
     * Set to "save" when a "save" request parameter is passed in
     */
    protected String save;

    /**
     * Velocity template to use for e-mailing
     */
    protected String templateName;

    /**
     * Simple method that returns "cancel" result
     *
     * @return "cancel"
     */
    public String cancel() {
        return CANCEL;
    }

    /**
     * Save the message in the session, appending if messages already exist
     *
     * @param msg the message to put in the session
     */
    @SuppressWarnings("unchecked")
    protected void saveMessage(String msg) {
        List messages = (List) getRequest().getSession().getAttribute("messages");
        if (messages == null) {
            messages = new ArrayList();
        }
        messages.add(msg);
        getRequest().getSession().setAttribute("messages", messages);
    }

    /**
     * Convenience method to get the Configuration HashMap
     * from the servlet context.
     *
     * @return the user's populated form from the session
     */
    protected Map getConfiguration() {
        Map config = (HashMap) getSession().getServletContext().getAttribute(Constants.CONFIG);
        // so unit tests don't puke when nothing's been set
        if (config == null) {
            return new HashMap();
        }
        return config;
    }

    /**
     * Convenience method to get the request
     *
     * @return current request
     */
    protected HttpServletRequest getRequest() {
        return ServletActionContext.getRequest();
    }

    /**
     * Convenience method to get the response
     *
     * @return current response
     */
    protected HttpServletResponse getResponse() {
        return ServletActionContext.getResponse();
    }

    /**
     * Convenience method to get the session. This will create a session if one doesn't exist.
     *
     * @return the session from the request (request.getSession()).
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }


    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    /**
     * Convenience method for setting a "from" parameter to indicate the previous page.
     *
     * @param from indicator for the originating page
     */
    public void setFrom(String from) {
        this.from = from;
    }

    public void setDelete(String delete) {
        this.delete = delete;
    }

    public void setSave(String save) {
        this.save = save;
    }

    /**
     * Save the key value pair in linked messages array. They provide facility to serve links to users in form of notifications
     *
     * @param key   Message displayed
     * @param value Url
     */
    @SuppressWarnings("unchecked")
    protected void saveLinkedMessage(String key, String value) {
        Map<String, String> messages = (Map<String, String>) getRequest().getSession().getAttribute("link_messages");
        if (messages == null) {
            messages = new HashMap<>();
        }
        messages.put(key, value);
        getRequest().getSession().setAttribute("link_messages", messages);
    }

    /**
     * Save the user error message in the session, appending if messages already exist
     *
     * @param msg the message to put in the session
     */
    @SuppressWarnings("unchecked")
    protected void saveUserErrorMessage(String msg) {
        List messages = (List) getRequest().getSession().getAttribute("user_error_messages");
        if (messages == null) {
            messages = new ArrayList();
        }
        messages.add(msg);
        getRequest().getSession().setAttribute("user_error_messages", messages);
    }

    /**
     * Returns the real path of the file name
     *
     * @param fileName
     * @return
     */
    String getRealPath(String fileName) {
        ServletContext sc = getSession().getServletContext();
        return sc.getRealPath(fileName);
    }

}
