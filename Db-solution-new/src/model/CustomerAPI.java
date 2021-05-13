package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CustomerAPI")
public class CustomerAPI extends HttpServlet {
	
		private static final long serialVersionUID = 1L;



		Customer customerObj = new Customer();

		public CustomerAPI() {
			super();
		// TODO Auto-generated constructor stub
		}

		protected void doget(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
		}

		protected void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		String outputString = customerObj.insertCustomer(request.getParameter("customer_name"),
		request.getParameter("email"),
		request.getParameter("phone_number"),
		request.getParameter("country"));

		response.getWriter().write(outputString);
		}

		protected void doPut(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		Map paras = getParasMap(request);

		String outputString = customerObj.updateCustomer(
		paras.get("customer_id").toString(),
		paras.get("customer_name").toString(),
		paras.get("email").toString(),
		paras.get("phone_number").toString(),
		paras.get ("country").toString());

		response.getWriter().write(outputString);

		}
		protected void doDelete(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {

		Map paras = getParasMap(request);
		String output = customerObj.deleteCustomer(paras.get("customer_id").toString());
		response.getWriter().write(output);
		}




		private Map getParasMap(HttpServletRequest request) {
		// TODO Auto-generated method stub
		Map<String, String> map = new HashMap<String, String>();

		try {
		Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
		String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
		scanner.close();

		String[] params = queryString.split("&");
		for (String param : params) {
		String[] p = param.split("=");
		map.put(p[0], p[1]);
		}
		}catch (Exception e) {
		}

		return map;

		}

		}

