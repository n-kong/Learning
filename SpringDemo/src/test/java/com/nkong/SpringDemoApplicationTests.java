package com.nkong;

import com.nkong.bean.Dog;
import com.nkong.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
class SpringDemoApplicationTests {

//	@Autowired
//	private Dog dog;
//	@Autowired
//	private Person person;
	@Autowired
	private DataSource dataSource;

	@Test
	void contextLoads() {
//		System.out.println(dog);
//		System.out.println(person);
		System.out.println(dataSource.getClass());
		try {
			Connection connection = dataSource.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from test");
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String string = resultSet.getString(1);
				String string1 = resultSet.getString(2);
				String string2 = resultSet.getString(3);
				System.out.println(string+ ":" + string1 + ":" + string2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
