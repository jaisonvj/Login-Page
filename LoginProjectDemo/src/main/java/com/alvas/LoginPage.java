package com.alvas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginPage extends HttpServlet {
public void service(HttpServletRequest req,HttpServletResponse res) throws IOException{
		
		String name1=req.getParameter("username");
		String entered_password=req.getParameter("password");
		//System.out.println(name+"\n"+entered_password);
		
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		java.sql.Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","rootroot");
		PreparedStatement st=con.prepareStatement("select password from new_table where name=?");
		st.setString(1,name1);
		ResultSet rs=st.executeQuery();
		rs.next();
		System.out.println(rs.getString("password"));
		PrintWriter out=res.getWriter();
		
		if(entered_password.equals(rs.getString("password")))
		{
			out.println("logged in successfully");
		}
		else 
		{
			out.println("username and password doesn't match");
		res.sendRedirect("loginpage.html");
		
		}
		

	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}


	
}
