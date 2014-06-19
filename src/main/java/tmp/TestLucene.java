package tmp;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLucene {

	static Logger logger = LoggerFactory.getLogger(TestLucene.class);

	// public static void main(String[] args) {
	static String str = "The sheriff's initial estimate of as many as 25 dead in the Columbine High massacre"
			+ " was off the mark apparently because the six SWAT teams that swept the building counted some victims more than once";
	// SnowballAnalyzer sa = new SnowballAnalyzer(Version.LUCENE_36, name);
	//
	// Stemmer stemmer = new Stemmer();
	// for (String s : str.split(" ")) {
	// stemmer.add(s);
	// stemmer.stem();
	// String rs = stemmer.toString();
	// logger.info(s + "  -->  " + rs);
	// }
	//
	// }

	private static String STR = "i am lihan, i am a boy, i come from Beijing，我是来自北京的李晗";

	// WhitespaceAnalyzer 分析方法：空格分割
	public static Analyzer testWhitespaceAnalyzer() {
		Analyzer alalyzer = new WhitespaceAnalyzer();
		return alalyzer;
	}

	// SimpleAnalyzer 分析方法：空格及各种符号分割
	public static Analyzer testSimpleAnalyzer() {
		Analyzer analyzer = new SimpleAnalyzer();
		return analyzer;
	}

	// StopAnalyzer 分析方法：空格及各种符号分割,去掉停止词，停止词包括 is,are,in,on,the等无实际意义的词
	public static Analyzer testStopAnalyzer() {
		Analyzer analyzer = new StopAnalyzer(Version.LUCENE_36);
		return analyzer;
	}

	// StandardAnalyzer 分析方法：混合分割,包括了去掉停止词，支持汉语
	public static Analyzer testStandardAnalyzer() {
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		return analyzer;
	}

	public static void main(String[] a) {
		Analyzer analyzer = testStopAnalyzer();
		Reader r = new StringReader(str);
		// WhitespaceAnalyzer 和SimpleAnalyzer使用Tokenizer
		// Tokenizer ts = (Tokenizer) analyzer.tokenStream("", r);
		// StopAnalyzer和StandardAnalyzer 使用StopFilter
//		StopFilter ts = (StopFilter) analyzer.tokenStream("", r);
		TokenStream ts = analyzer.tokenStream("", r);
		try {
			while (ts.incrementToken()) {
				CharTermAttribute ta = ts.getAttribute(CharTermAttribute.class);
				System.out.println(new String(ta.toString()));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
