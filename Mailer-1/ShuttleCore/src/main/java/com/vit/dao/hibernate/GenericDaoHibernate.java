package com.vit.dao.hibernate;

import com.vit.dao.GenericDao;
import com.vit.dao.SearchException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.*;
import org.hibernate.jdbc.Work;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import javax.annotation.Resource;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by kashishsinghal on 13/03/16.
 */
public abstract class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    protected Class<T> persistentClass;

    @Resource
    private SessionFactory sessionFactory;

    protected org.apache.lucene.analysis.Analyzer defaultAnalyzer;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
        //defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
    }


    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
        //defaultAnalyzer = new StandardAnalyzer(Version.LUCENE_35);
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess;

        if(sessionFactory == null){
            System.out.println("sessionFactory is null");
        }
        sess = sessionFactory.getCurrentSession();

        if (sess == null) {

            sess = sessionFactory.openSession();

        }
        return sess;
    }
    /*public Session getReadSession() throws HibernateException {
        Session sess = getSessionFactory1().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory1().openSession();
        }
        return sess;
    }*/

    @Autowired
    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session sess = getSession();
        return sess.createCriteria(persistentClass).list();
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> search(String searchTerm) throws SearchException{
        Session sess = getSession();
        FullTextSession txtSession = Search.getFullTextSession(sess);

        /*org.apache.lucene.search.Query qry;
        try {
            qry = HibernateSearchTools.generateQuery(searchTerm, this.persistentClass, sess, defaultAnalyzer);
        } catch (ParseException ex) {
            throw new SearchException(ex);
        }
        org.hibernate.search.FullTextQuery hibQuery = txtSession.createFullTextQuery(qry,
                this.persistentClass);
        return hibQuery.list();*/
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);

        if (entity == null) {
            //log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            //throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);

        // for some reason hibernate is throwing an exception org.hibernate.ObjectNotFoundException
        // its not supposed to but it is
        // lets handle it here
        T entity = null;
        try {
            entity = (T) byId.load(id);
        } catch (ObjectNotFoundException ex) {
            // ignore it
            // it gets already logged in the hibernate code so don't log it here
        }
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        Session sess = getSession();
        return (T) sess.merge(object);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public void updateObject(T object) {
        Session sess = getSession();
        sess.update(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        Session sess = getSession();
        sess.delete(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        sess.delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Session sess = getSession();
        Query namedQuery = sess.getNamedQuery(queryName);

        for (String s : queryParams.keySet()) {
            namedQuery.setParameter(s, queryParams.get(s));
        }

        return namedQuery.list();
    }

    /**
     * {@inheritDoc}
     */
    /*public void reindex() {
        HibernateSearchTools.reindex(persistentClass, getSessionFactory().getCurrentSession(), true);
    }


    *//**
     * {@inheritDoc}
     *//*
    public void reindexAll(boolean async) {
        HibernateSearchTools.reindexAll(async, getSessionFactory().getCurrentSession());
    }
*/

    public Map getRawSqlResults(String sql) {
        final Map<String, Object> resultMap = new HashMap<String, Object>();
        final String finalSql = sql.trim().toLowerCase();
        if (finalSql.startsWith("select")) {
            Session session = getSession();
            session.doWork(new Work() {
                public void execute(Connection connection) throws SQLException {
                    connection.createStatement().executeQuery(finalSql);
                    ResultSet rs = connection.createStatement().executeQuery(finalSql);
                    ResultSetMetaData rsmd = rs.getMetaData();

                    // add the metadata to the map as well
                    resultMap.put("metadata", rsmd);

                    List<List<Object>> data = new ArrayList<List<Object>>();
                    resultMap.put("data", data);

                    int cols = rsmd.getColumnCount();
                    while (rs.next()) {
                        // Get the data from the row using the column index
                        List<Object> row = new ArrayList<Object>();
                        for (int i = 1; i <= cols; i++) {
                            row.add(rs.getObject(i));
                        }
                        data.add(row);
                    }
                }
            });
        }
        return resultMap;
    }

    public String executeCreate(final String sql){
        final List<String>l=new ArrayList<>();
        Session session = getSession();
        session.doWork(new Work() {
            public void execute(Connection connection) throws SQLException {
                int result;
                try{
                    result=connection.createStatement().executeUpdate(sql);
                    //System.out.println(connection.createStatement().executeUpdate(sql));
                    l.add(""+result);
                }
                catch(Exception e){
                    System.out.println("Inside Catch"+e.toString());
                    l.add(e.toString());
                }

            }


        });
        return l.get(0);
    }
}
