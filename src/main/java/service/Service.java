package service;

import java.io.InputStream;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Service {

	protected SqlSession session;

	public Service() {
		getSqlSession();
	}

	private void getSqlSession() {
		SqlSessionFactory sqlSessionFactory = null;
		String resource = "mybatis.xml";
		InputStream inputStream;
		inputStream = Service.class.getClassLoader().getResourceAsStream(resource);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		this.session = sqlSessionFactory.openSession();
	}

	public void commit() {
		session.commit();
	}
}
