package main.web;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import main.model.CompanyDistributor;
import main.model.CompanyLogin;
import main.dao.CompanyDistributorDao;
import main.dao.CompanyLoginDao;


@WebServlet("/login")
public class CompanyLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String companyUserName = null;
    private CompanyLoginDao companyLoginDao = new CompanyLoginDao();

    public  CompanyLoginServlet() {
    	super();
        //loginDao = new LoginDao();
    }
    
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	response.getWriter().append("Served at :").append(request.getContextPath());
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("login-company.jsp");
    	dispatcher.forward(request,response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        CompanyLogin companyLogin = new CompanyLogin();
        companyLogin.setUsername(username);
        companyLogin.setPassword(password);
        setCompanyUserName(username);
        try {
            if (companyLoginDao.validate(companyLogin)) {
                //HttpSession session = request.getSession();
                // session.setAttribute("username",username);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("distributor-list.jsp");
				try {
					List<CompanyDistributor> listDis = CompanyDistributorDao.selectAllDistributors(username);
					request.setAttribute("listArticle", listDis);
					dispatcher.forward(request,response);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                //HttpSession session = request.getSession();
                //session.setAttribute("user", username);
            	RequestDispatcher dispatcher = request.getRequestDispatcher("login-company.jsp");
            	dispatcher.forward(request,response);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

	public static String getCompanyUserName() {
		return companyUserName;
	}

	public static void setCompanyUserName(String companyUserName) {
		CompanyLoginServlet.companyUserName = companyUserName;
	}
}
