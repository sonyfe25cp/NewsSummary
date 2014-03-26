package tac;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import model.tac.TacSentence;
import service.tac.TacSentenceService;

/**
 * 把文件名字中的日期提出来，并存到数据库
 * 
 * @author Sonyfe25cp 2014-3-25
 */
public class DatePrepare {

	public static void main(String[] args) throws Exception {
		TacSentenceService tacSentenceService = new TacSentenceService();
		List<TacSentence> all = tacSentenceService.findAllSentences();
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd");
		for (TacSentence sentence : all) {
			String docName = sentence.getDocName();
			try {
				String date = docName.substring(0, docName.indexOf("."))
						.substring(3);//3 or 8
				Date d = df.parse(date);
				String nd = df2.format(d);
				System.out.println(nd);
				sentence.setPublishDate(nd);
				tacSentenceService.update(sentence);
			} catch (ParseException e) {
//				String date = docName.substring(0, docName.indexOf("."))
//						.substring(8);//3 or 8
//				Date d = df.parse(date);
//				String nd = df2.format(d);
//				System.out.println(nd);
//				sentence.setPublishDate(nd);
//				tacSentenceService.update(sentence);
			}
		}
	}

}
