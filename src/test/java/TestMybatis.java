
import java.io.IOException;
import java.io.InputStream;

import mapper.SentenceMapper;
import model.Sentence;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import service.SentenceService;

public class TestMybatis {

	public static void main(String[] args){
		TestMybatis tm = new TestMybatis();
		tm.testMybatis();
		
		SentenceService ss = new SentenceService();
		Sentence s = ss.getFirstSenceOfDoc("APW19990422.0082");
		System.out.println(s);
	}
	
	public void testMybatis() {
		String resource = "mybatis.xml";
		InputStream inputStream;
		SqlSessionFactory sqlSessionFactory = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		SqlSession session = sqlSessionFactory.openSession();
		try {
			SentenceMapper mapper = session.getMapper(SentenceMapper.class);
			Sentence sentence = mapper.getSentenceById(1);
			System.out.println(sentence);
		} finally {
			session.close();
		}

	}
}
