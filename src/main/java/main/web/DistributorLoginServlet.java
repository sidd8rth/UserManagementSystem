package main.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.model.Article;
import main.model.CompanyDistributor;
import main.model.CompanyLogin;
import main.model.DistributorLogin;
import main.dao.ArticleDAO;
import main.dao.CompanyDistributorDao;
import main.dao.CompanyLoginDao;
import main.dao.DistributorLoginDao;


@WebServlet("/distributorLogin")
public class DistributorLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String distributorUserName = null;
    private DistributorLoginDao distributorLoginDao = new DistributorLoginDao();

    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	response.getWriter().append("Served at :").append(request.getContextPath());
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("login-distributor.jsp");
    	dispatcher.forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        DistributorLogin distLogin = new DistributorLogin();
        distLogin.setUsername(username);
        distLogin.setPassword(password);
        setDistUserName(username);
        try {
        	String companyUserName = distributorLoginDao.validate(distLogin);
        	if (companyUserName != null) {
        		CompanyLoginServlet.setCompanyUserName(companyUserName);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("article-list.jsp");
				try {
					List<Article> listArt = ArticleDAO.selectAllArticles(companyUserName, username);
					request.setAttribute("listArticle", listArt);
					dispatcher.forward(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                //HttpSession session = request.getSession();
                //session.setAttribute("user", username);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("login-distributor.jsp");
            	dispatcher.forward(request,response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

	public static String getDistUserName() {
		return distributorUserName;
	}

	public static void setDistUserName(String distUserName) {
		DistributorLoginServlet.distributorUserName = distUserName;
	}
}

