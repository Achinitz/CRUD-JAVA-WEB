package servlets;
import beans.ConfigBean;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

@WebServlet(name = "Startup", loadOnStartup = 1)
public class StartupServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        ConfigBean config = new ConfigBean();
        config.setEmailAdmin("gustavo.achinitz@ufpr.br");
        ServletContext ctx = getServletContext();
        ctx.setAttribute("configuracao", config);
    }
}