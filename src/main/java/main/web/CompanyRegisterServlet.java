package main.web;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.RequestDispatcher;

import main.dao.CompanyRegisterDao;
import main.model.CompanyRegister;

@WebServlet("/register")
public class CompanyRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private CompanyRegisterDao  companyRegisterDao = new CompanyRegisterDao();
    public CompanyRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
    	response.getWriter().append("Served at :").append(request.getContextPath());
    	
    	RequestDispatcher dispatcher = request.getRequestDispatcher("companyRegister.jsp");
    	dispatcher.forward(request,response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String firmName = request.getParameter("firmName");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String address = request.getParameter("address");
        String contact = request.getParameter("contact");

        CompanyRegister companyRegister = new CompanyRegister();
        companyRegister.setFirmName(firmName);
        companyRegister.setUserName(username);
        companyRegister.setPassword(password);
        companyRegister.setContact(contact);
        companyRegister.setAddress(address);

        try {
            companyRegisterDao.registerCompany(companyRegister);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("login-company.jsp");
    	dispatcher.forward(request,response);
	}

}
