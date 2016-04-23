package ua.dudka.store.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.dudka.beans.User;

import java.util.Collection;
import java.util.List;

/**
 * Created by RASTA on 15.03.2016.
 */
@Repository
class UserDAOImpl implements UserDAO {
    private final HibernateTemplate template;

    @Autowired
    public UserDAOImpl(final HibernateTemplate hibernateTemplate) {
        this.template = hibernateTemplate;
    }

    @Transactional
    public void addUser(User user) {
        template.save(user);
    }

    public Collection<User> getAllUsers() {
        return template.loadAll(User.class);
    }

    public User getUser(int id) {
        return template.load(User.class, id);
    }


    public User getUser(String login) {
        List<?> objects = template.find("from User as u where u.login=?", login);
        return objects.isEmpty() ? null : (User) objects.get(0);
    }

    @Override
    public User findByAuth(String login, String pass) {
        List<?> objects = template.find("from User  as u where  u.login=? and  u.password =?", login, pass);
        return objects.isEmpty() ? null : (User) objects.get(0);
    }

    @Transactional
    public void updateUser(User user) {
        template.update(user);
    }

    public boolean contains(String login) {
        return getUser(login) != null;
    }

}
