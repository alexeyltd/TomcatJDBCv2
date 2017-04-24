package dao.impl;

import dao.UserDAO;
import model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by alexeypavlenko on 23/04/2017.
 */
public class UserDaoHib implements UserDAO {

    private Session session;
    private SessionFactory sessionFactory;

    public UserDaoHib(Configuration configuration) {
        this.sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }



    public User get(long id) {
        User user = (User) session.get(User.class, id);
        return user;
    }


    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(User.class);
        User user = ((User) criteria.add(Restrictions.eq("name", name)).uniqueResult());
        return user.getId();
    }



    public void deleteUserById(long id) throws HibernateException {
        User user;
        if ((user = (User) session.get(User.class, id)) != null) {
            session.delete(user);
        }
    }

    /*
//    auto_increment !!!


    public void createTable() throws HibernateException {

        session.createSQLQuery("create table if not exists data (id bigint  , name varchar(256), " +
                "login varchar(256), password varchar(256), primary key (id))").executeUpdate();
    }


    public void dropTable() throws HibernateException {
        session.createSQLQuery("DROP TABLE data").executeUpdate();
    }
    */

    @Override
    public void addUser(String name, String login, String password) throws UserDaoJDBCException, UserDaoHibException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, login, password));
            transaction.commit();
            session.close();
        }
        catch (HibernateException e) {
            throw new UserDaoHibException(e);
        }
    }

    @Override
    public long updateUser(long id, String name, String login, String password) throws UserDaoJDBCException, UserDaoHibException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(new User(id, name, login, password));
            transaction.commit();
            session.close();
            return id;
        }
        catch (HibernateException e) {
            throw new UserDaoHibException(e);
        }
    }

    @Override
    public void deleteUser(long id) throws UserDaoJDBCException, UserDaoHibException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            User user;
            if ((user = (User) session.get(User.class, id)) != null) {
                session.delete(user);
            }

            transaction.commit();
            session.close();
        }
        catch (HibernateException e) {
            throw new UserDaoHibException(e);
        }
    }
}
