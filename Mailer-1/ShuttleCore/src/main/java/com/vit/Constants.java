package com.vit;

/**
 * Created by kashishsinghal on 13/03/16.
 */
public class Constants {
    /**
     * Assets Version constant
     */
    public static final String ASSETS_VERSION = "assetsVersion";
    //~ Static fields/initializers =============================================
    /**
     * The name of the ResourceBundle used in this application
     */
    public static final String BUNDLE_KEY = "ApplicationResources";
    /**
     * File separator from System properties
     */
    public static final String FILE_SEP = System.getProperty("file.separator");
    /**
     * User home from System properties
     */
    public static final String USER_HOME = System.getProperty("user.home") + FILE_SEP;
    /**
     * The name of the configuration hashmap stored in application scope.
     */
    public static final String CONFIG = "appConfig";
    /**
     * Session scope attribute that holds the locale set by the user. By setting this key
     * to the same one that Struts uses, we get synchronization in Struts w/o having
     * to do extra work or have two session-level variables.
     */
    public static final String PREFERRED_LOCALE_KEY = "org.apache.struts2.action.LOCALE";
    /**
     * The request scope attribute under which an editable user form is stored
     */
    public static final String USER_KEY = "userForm";
    /**
     * The request scope attribute that holds the user list
     */
    public static final String USER_LIST = "userList";
    /**
     * The request scope attribute for indicating a newly-registered user
     */
    public static final String REGISTERED = "registered";
    /**
     * The name of the Administrator role, as specified in web.xml
     */
    public static final String ADMIN_ROLE = "ROLE_ADMIN";
    /**
     * The name of the User role, as specified in web.xml
     */
    public static final String USER_ROLE = "ROLE_USER";
    public static final String TEMPLATE_MAKER_ROLE = "TEMPLATE_MAKER";
    public static final String TEMPLATE_CHECKER_ROLE = "TEMPLATE_CHECKER";
    /**
     * The name of the user's role list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String USER_ROLES = "userRoles";
    /**
     * The name of the available roles list, a request-scoped attribute
     * when adding/editing a user.
     */
    public static final String AVAILABLE_ROLES = "availableRoles";
    /**
     * The name of the CSS Theme setting.
     *
     * @deprecated No longer used to set themes.
     */
    public static final String CSS_THEME = "csstheme";
    /**
     * the maximum time to wait for the client to process the tickle (perform sync) before it will be resend
     */
    public static final long MAX_TICKLE_PROCESSING_WAIT_TIME = 5 * 60 * 1000;
    public static final int MAX_BUCKETS = 1;
    public static final String GCM_API_KEY = "AIzaSyB_8r6o4q8Jpnh938w4Jv93HpToFn0pDJg";
    public static final String SOLR_HOME = System.getProperty("solr.home");
    public static final String DELETE_BKP_ROLE = "DELETE_BKP";

    private Constants() {
        // hide me
    }
}
