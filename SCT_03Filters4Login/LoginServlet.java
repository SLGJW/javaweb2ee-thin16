package servlet����;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
	
	//��дservice
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req�������  getParameter������ȡǰ�˴��ݵ����ݣ�ͨ��nameֵ��ȡ��Ӧ��valueֵ
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Connection conn=null;
		Statement state=null;
		ResultSet rs=null;
				try {
					//��������
					Class.forName("com.mysql.cj.jdbc.Driver");
					//��ȡ����
					conn = DriverManager.getConnection("jdbc:mysql:///db2?serverTimezone=GMT%2B8", "root", "123456");
					state = conn.createStatement();
					System.out.println("���ݿ����ӳɹ�");
					String sql = "select * from user where uname = '" + username + "' and password = '" + password + "'";
					
					//ִ�в�ѯsql���ؽ����
					rs = state.executeQuery(sql);
					if (rs.next()) {
						System.out.println("1");
						resp.sendRedirect("Login.html");
					} else {
						System.out.println("2");
						resp.sendRedirect("Fail.html");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
				//�ͷ���Դ
				try {
					rs.close();
					state.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		
	}
}