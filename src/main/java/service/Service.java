package service;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Service {

	SqlSession session;

	public Service() {
		getSqlSession();
	}

	private void getSqlSession() {
		SqlSessionFactory sqlSessionFactory = null;
		String resource = "mybatis.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.session = sqlSessionFactory.openSession();
	}
	public void commit(){
		session.commit();
	}
}
