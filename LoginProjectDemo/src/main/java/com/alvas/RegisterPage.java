package com.alvas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterPage extends HttpServlet{
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {
		String name = req.getParameter("username");
		String roll_no = req.getParameter("roll_no");
		String gender = req.getParameter("gender");
		String phone = req.getParameter("phone");
		String password = req.getParameter("password");

		System.out.println(name + "\n" + roll_no + "\n" + gender + "\n" + phone);

		// Establishing connection

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root",
					"rootroot");
			System.out.println("conection estblished");
			PreparedStatement st = con.prepareStatement("insert into new_table value(?,?,?,?,?)");
			st.setString(1, name);
			st.setString(2, roll_no);
			st.setString(3, gender);
			st.setString(4, phone);
			st.setString(5, password);

			st.execute();
			st.close();
			con.close();
			PrintWriter out = res.getWriter();
			out.println("registration is successfully");

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

	

}
