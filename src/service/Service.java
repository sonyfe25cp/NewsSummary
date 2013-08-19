package service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Service {

	SqlSession session;
	
	public Service(){
		getSqlSession();
	}
	private void getSqlSession(){
		String resource = "mybatis.xml";
		InputStream inputStream;
		SqlSessionFactory sqlSessionFactory = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.session = sqlSessionFactory.openSession();
	}
}
