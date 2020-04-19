package servlet代码;

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
	
	//重写service
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//req请求对象  getParameter方法获取前端传递的数据，通过name值获取对应的value值
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		
		Connection conn=null;
		Statement state=null;
		ResultSet rs=null;
				try {
					//加载驱动
					Class.forName("com.mysql.cj.jdbc.Driver");
					//获取连接
					conn = DriverManager.getConnection("jdbc:mysql:///db2?serverTimezone=GMT%2B8", "root", "123456");
					state = conn.createStatement();
					System.out.println("数据库连接成功");
					String sql = "select * from user where uname = '" + username + "' and password = '" + password + "'";
					
					//执行查询sql返回结果集
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
				//释放资源
				try {
					rs.close();
					state.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
		
	}
}