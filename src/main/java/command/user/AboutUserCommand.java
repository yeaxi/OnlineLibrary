package command.user;

import command.ActionCommand;
import managers.ConfigurationManager;
import models.User;
import store.cache.UserCache;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by RASTA on 16.03.2016.
 */
public class AboutUserCommand implements ActionCommand {
    private static final UserCache CACHE = UserCache.getInstance();

    public String execute(HttpServletRequest req) {
        String page;
        String login = req.getParameter("login");

        User user = CACHE.getUser(login);
        req.setAttribute("books", user.getBooks());
        page = ConfigurationManager.getProperty("path.page.aboutuser");
        return page;
    }

}
