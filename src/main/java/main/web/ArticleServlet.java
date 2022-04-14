package main.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import main.dao.ArticleDAO;
import main.model.Article;

/**
 * ControllerServlet.java
 * This servlet acts as a page controller for the application, handling all
 * requests from the user.
 */

@WebServlet("/article/*")
public class ArticleServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ArticleDAO articleDAO;

    public void init() {
        articleDAO = new ArticleDAO();
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
                    insertArticle(request, response);
                    break;
                case "/delete":
                    deleteArticle(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateArticle(request, response);
                    break;
                case "/list":
                	listArticle(request, response);
                	break;
                default:
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listArticle(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ServletException {
        List < Article > listArticle = ArticleDAO.selectAllArticles(CompanyLoginServlet.getCompanyUserName(), DistributorLoginServlet.getDistUserName());
        request.setAttribute("listArticle", listArticle);
        RequestDispatcher dispatcher = request.getRequestDispatcher("../article-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("../article-form.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Article existingArticle = articleDAO.selectArticle(id, CompanyLoginServlet.getCompanyUserName(), DistributorLoginServlet.getDistUserName());
        RequestDispatcher dispatcher = request.getRequestDispatcher("../article-form.jsp");
        request.setAttribute("article", existingArticle);
        dispatcher.forward(request, response);

    }

    private void insertArticle(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        String article_name = request.getParameter("article_name");
        int article_quantity = Integer.parseInt(request.getParameter("article_quantity"));
        int article_price = Integer.parseInt(request.getParameter("article_price"));
        Article newArticle = new Article(article_name,article_quantity,article_price);
        articleDAO.insertArticle(newArticle, CompanyLoginServlet.getCompanyUserName(), DistributorLoginServlet.getDistUserName());
        response.sendRedirect("list");
    }

    private void updateArticle(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String article_name = request.getParameter("article_name");
        int article_quantity = Integer.parseInt(request.getParameter("article_quantity"));
        int article_price = Integer.parseInt(request.getParameter("article_price"));

        Article book = new Article(id,article_name,article_quantity,article_price);
        articleDAO.updateArticle(book, CompanyLoginServlet.getCompanyUserName(), DistributorLoginServlet.getDistUserName());
        response.sendRedirect("list");
    }

    private void deleteArticle(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        articleDAO.deleteArticle(id, CompanyLoginServlet.getCompanyUserName(), DistributorLoginServlet.getDistUserName());
        response.sendRedirect("list");

    }
}