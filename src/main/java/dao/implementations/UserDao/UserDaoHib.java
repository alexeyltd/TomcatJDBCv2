package dao.implementations.UserDao;

import dao.abstracts.UserDao.UserDAO;
import dao.implementations.Exceptions.UserDaoHibException;
import dao.implementations.Exceptions.UserDaoJDBCException;
import model.User;
import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import java.util.List;


public class UserDaoHib implements UserDAO {

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



    public User getOneUser(String login, String password) {

        Session session = sessionFactory.openSession();
        User user = (User) session.get(User.class, getUserId(login, password));
        session.close();

        return user;
    }


    private long getUserId(String login, String password) throws HibernateException {
        Session session = sessionFactory.openSession();
        try {
            Criteria criteria = session.createCriteria(User.class);
            User userOne = ((User) criteria.add(Restrictions.eq("login", login)).uniqueResult());
            User userTwo = ((User) criteria.add(Restrictions.eq("password", password)).uniqueResult());

            if (userOne.equals(userTwo)) {
                return userOne.getId();
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
        finally {
            session.close();
        }

        return -1;
    }


    /*
    public void deleteUserById(long id) throws HibernateException {
        User user;
        if ((user = (User) session.get(User.class, id)) != null) {
            session.delete(user);
        }
    }
    */


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


    public List<User> getUsers() throws UserDaoHibException {

        Session session = sessionFactory.openSession();

        try {
            Transaction transaction = session.beginTransaction();
            List<User> list = (List<User>) session.createQuery("FROM User").list();
            transaction.commit();


            return list;
        }
        catch (HibernateException e) {
            throw new UserDaoHibException(e);
        }
        finally {
            session.close();
        }

    }


    @Override
    public void addUser(String name, String login, String password, String role) throws UserDaoJDBCException, UserDaoHibException {

        Session session = sessionFactory.openSession();

        try {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, login, password, role));
            transaction.commit();
        }
        catch (HibernateException e) {
            throw new UserDaoHibException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void updateUser(long id, String name, String login, String password, String role) throws UserDaoJDBCException, UserDaoHibException {

        Session session = null;

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.update(new User(id, name, login, password, role));
            transaction.commit();
        }
        catch (HibernateException e) {
            throw new UserDaoHibException(e);
        }
        finally {
            session.close();
        }
    }

    @Override
    public void deleteUser(long id) throws UserDaoJDBCException, UserDaoHibException {

        Session session = sessionFactory.openSession();

        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();

            User user;
            if ((user = (User) session.get(User.class, id)) != null) {
                session.delete(user);
            }

            transaction.commit();
        }
        catch (HibernateException e) {
            throw new UserDaoHibException(e);
        }
        finally {
            session.close();
        }
    }
}
