package cn.ucai.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import cn.ucai.bean.User;

public class Test {
	public static void main(String[] args) {
		List<User> list = getAll();
		//遍历集合
		for(User u:list){
			System.out.println(u);
		}
		
	}
	// 查询表中的全部数据，将数据封装为List集合	
	public static List<User> getAll(){
		// 声明list集合		
		List<User> list = new ArrayList<User>();
		try {
			//加载驱动			
			Class.forName("com.mysql.jdbc.Driver");
			//连接数据库			
			Connection conn = 
					DriverManager.
					getConnection("jdbc:mysql://localhost:3306/nd?user=root&password=root");
			System.out.println(conn);
			//得到操作数据库的类的对象
			Statement st = conn.createStatement();
			//操作数据库，得到结果集
			ResultSet rs = st.executeQuery("select * from t_user");
			while (rs.next()) {
				int id = rs.getInt("id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				System.out.println("id为："+id+"用户为："+username+"密码为："+password);
				//创建实体类对象
				User user = new User(id,username,password);
				//将实体类对象添加进集合
				list.add(user);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	
	
	
	
	
	
	
}
