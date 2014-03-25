package tac;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.StopAnalyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;

/**
 * 基本的英文分词，不进行词根化
 * 
 * @author Sonyfe25cp 2014-3-25
 */
public class TokenizerUtils {

	static Analyzer analyzer = new StopAnalyzer(Version.LUCENE_36);

	public static List<String> tokenizer(String str) {
		List<String> list = new ArrayList<>();
		Reader r = new StringReader(str);
		TokenStream ts = analyzer.tokenStream("", r);
		try {
			while (ts.incrementToken()) {
				CharTermAttribute ta = ts.getAttribute(CharTermAttribute.class);
				list.add(ta.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	public static Map<String, Integer> tokenizerMap(String str) {
		Map<String, Integer> map = new HashMap<>();
		Reader r = new StringReader(str);
		TokenStream ts = analyzer.tokenStream("", r);
		try {
			while (ts.incrementToken()) {
				CharTermAttribute ta = ts.getAttribute(CharTermAttribute.class);
				String t = ta.toString();
				if(t == null || t.length() < 2){
					continue;
				}
				Integer count = map.get(t);
				if (count == null) {
					count = 1;
				} else {
					count++;
				}
				map.put(t, count);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}
}
