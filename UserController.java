package com.tj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/uc")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String filepath = "C:/test/users.csv";

	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter(); // out�� ���������� �ش� ���� �ۿ��� ��� ��ʦ
		
		String cmd = request.getParameter("cmd");
		if(cmd==null) {
			getUserList(response);
			return;
		}
		switch(cmd) {
		case "list":
			getUserList(response); // ��û�� browser�� ������ �ֱ����� response(�������� ��ü)���
			break;
		case "show_user":
			int num = Integer.parseInt(request.getParameter("num"));
			getUser(filepath, num, response);
			break;
		case "user_input":
			response.sendRedirect("user_input.html"); //�ش� html���� �����ؼ� �������
			break;
		case "user_save":
			System.out.println("user_save");
			
			if(userSave(request)) {
				out.println("����� ���� ���� ����!");
			}
			break;
		case "user_delete":
			UserDAO dao = new UserDAO();
			int no = Integer.parseInt(request.getParameter("num"));
			boolean deleted = dao.delete(filepath, no);
			if(deleted) {
				out.println("����� ���� ���� ����");
			}else {
				out.println("����� ���� ���� ����");
			}
			response.sendRedirect("uc");
			break;
		case "user_edit":
			showEditForm(filepath, request, response);
			break;
		case "user_update":
			boolean updated = update(request);
			response.sendRedirect("uc");
			break;
		}		
	}

	private boolean update(HttpServletRequest request) {
		UserVO u = new UserVO();
		u.setNum(Integer.parseInt(request.getParameter("num")));
		u.setName(request.getParameter("name"));
		u.setPhone(request.getParameter("phone"));
		u.setEmail(request.getParameter("email"));
		UserDAO dao = new UserDAO();
		return dao.update(filepath, u);
	}
	
	private void showEditForm(String path, HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		UserDAO dao = new UserDAO();
		UserVO u = dao.getUser(path, num);
		String editForm = "<form action='uc' method='post'>";
		editForm += "<input type = 'hidden' name='cmd' value='user_update'>";
		editForm += "<p>��ȣ <input type='text' name='num' value='"+u.getNum()+"'></p>";
		editForm += "<p>�̸� <input type='text' name='name' value='"+u.getName()+"'></p>";
		editForm += "<p>��ȣ <input type='text' name='phone' value='"+u.getPhone()+"'></p>";
		editForm += "<p>���� <input type='text' name='email' value='"+u.getEmail()+"'></p>";
		editForm += "<p><button type='submit'>����</button></p>";
		editForm += "<button type='reset'>���</button></p>";
		try {
			PrintWriter out = response.getWriter();
			out.print(editForm);
		} catch(IOException e) {
			e.printStackTrace();
		}		
	}

	private boolean userSave(HttpServletRequest request) {
		int no = Integer.parseInt(request.getParameter("num"));
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		UserVO u = new UserVO();
		u.setNum(no);
		u.setName(name);
		u.setPhone(phone);
		u.setEmail(email);
		UserDAO dao = new UserDAO();
				
		System.out.println("userSave : name : " + request.getParameter("name"));
		
		return dao.save(filepath, u);
	}

	private void getUserList(HttpServletResponse response) {
			PrintWriter out = null;
			try {
				out = response.getWriter();
			}catch(Exception ex) {
				return;
			}
			
			List<UserVO> list = new UserDAO().getUserList("/test/users.csv");
	
			String table = "<table border='1' style='border-collapse:collapse;'>";
			for(int i=0;i<list.size();i++) {
			UserVO u = list.get(i);
			table += "<tr>";
			table +="<td><a href='uc?cmd=show_user&num="+u.getNum()+"'>"+u.getNum()+"</a></td>";
			table +="<td>"+u.getName()+"</td>";
			table +="<td>"+u.getPhone()+"</td>";
			table +="<td>"+u.getEmail()+"</td>";
			table += "</tr>";
		}
		
		table += "</table>";
		out.println(list);
	}
	
	private void getUser(String path, int num, HttpServletResponse response) {
		UserVO u = new UserDAO().getUser(path, num); // filepath���� �ش� num�� �̿��ڸ� ã�Ƽ� �������
		try {
			PrintWriter out = response.getWriter();
			String nm = u.getName();
			int no = u.getNum();
			
			String style = "<style> a {text-decoration:none;}</style>";
		
			String div = "<div style='border:1px solod black; padding:10px;'>";
			div += no+" "+nm+" "+u.getPhone()+" "+u.getEmail();
			div += "</div>";
			
			String link= "<p>[<a href ='uc?cmd=list'>list����</a>]";
			link += "[<a href ='uc?cmd=user_edit&num="+no+"'>����</a>]";
			link += "[<a href ='uc?cmd=user_delete&num="+no+"'>����</a>]</p>";
			out.println(style+div+link);
		}catch(Exception ex) {}
	}
}
