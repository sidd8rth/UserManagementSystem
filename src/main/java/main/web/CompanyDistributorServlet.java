package main.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import main.dao.ArticleDAO;
import main.dao.CompanyDistributorDao;
import main.model.Article;
import main.model.CompanyDistributor;
import main.model.Distributor;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/distributor/*")
public class CompanyDistributorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CompanyDistributorDao companyDistributorDao;

    public void init() {
    	companyDistributorDao = new CompanyDistributorDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String action = request.getPathInfo();
        try {
            switch (action) {
                case "/new":
                    showNewForm(request, response);
                    break;
                case "/insert":
                    insertDistributor(request, response);
                    break;
                case "/delete":
                    deleteDistributor(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateDistributor(request, response);
                    break;
                case "/show":
                	listDist(request,response);
                	break;
                case "/list":
                	listDistributor(request, response);
                	break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listDistributor(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < CompanyDistributor > listDistributor = CompanyDistributorDao.selectAllDistributors(CompanyLoginServlet.getCompanyUserName());
        request.setAttribute("listArticle", listDistributor);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../distributor-list.jsp");
        dispatcher.forward(request, response);
    }
    
    private void listDist(HttpServletRequest request, HttpServletResponse response)
    	    throws SQLException, IOException, ServletException {
    	try {
    		String distName = request.getParameter("distName");
    		List<Article> listDist = ArticleDAO.selectAllArticles(CompanyLoginServlet.getCompanyUserName(), distName);
    		request.setAttribute("listArticle", listDist);
    		RequestDispatcher dispatcher = request.getRequestDispatcher("../distributor-article.jsp");
    		dispatcher.forward(request, response);    		
    	}catch(Exception e) {
    		
    	}
    	    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("../distributor-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.err.println("Message: ");
        CompanyDistributor existingDistributor = companyDistributorDao.selectDistributor(id,CompanyLoginServlet.getCompanyUserName());
        RequestDispatcher dispatcher = request.getRequestDispatcher("../distributor-form.jsp");
        request.setAttribute("company", existingDistributor);
        dispatcher.forward(request, response);

    }

    private void insertDistributor(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String dist_name = request.getParameter("dist_name");
        String dist_city = request.getParameter("dist_city");
        String dist_pincode = request.getParameter("dist_pincode");
        String dist_username = request.getParameter("dist_username");
        String dist_password = request.getParameter("dist_password");
        CompanyDistributor newCompanyDistributor = new CompanyDistributor(dist_name, dist_city, dist_pincode, dist_username, dist_password);
        companyDistributorDao.insertDistributor(newCompanyDistributor,CompanyLoginServlet.getCompanyUserName());
        response.sendRedirect("list");
    }

    private void updateDistributor(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String dist_name = request.getParameter("dist_name");
        String dist_city = request.getParameter("dist_city");
        String dist_pincode = request.getParameter("dist_pincode");
        String dist_username = request.getParameter("dist_username");
        String dist_password = request.getParameter("dist_password");

        CompanyDistributor book = new CompanyDistributor(id,dist_name,dist_city, dist_pincode, dist_username, dist_password);
        companyDistributorDao.updateDistributor(book,CompanyLoginServlet.getCompanyUserName());
        response.sendRedirect("list");
    }

    private void deleteDistributor(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        companyDistributorDao.deleteDistributor(id,CompanyLoginServlet.getCompanyUserName());
        response.sendRedirect("list");
    }
}

