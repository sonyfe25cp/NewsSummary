package pub;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.pub.PubSentence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import service.pub.PubSentenceService;

/**
 * Pubdata 的处理包括两部分
 * 1：是从原文件读入数据库
 * 2：如何处理标注数据（该数据集的标注数据并不一定是来自原文）
 * 		直接将标注入库，使用的时候再去比较
 * @author ChenJie
 * @date Mar 27, 2014
 */
public class DataPrepare {
	static Logger logger = LoggerFactory.getLogger(DataPrepare.class);
	public static void main(String[] args) {
		DataPrepare ppd = new DataPrepare();
//		ppd.run();
		ppd.runLabel();
	}

	private static String folderPath = "/home/coder/Downloads/Timeline17/Data";
	
	private PubSentenceService sentenceService = new PubSentenceService();
	
	private void run(){
		List<PubSentence> sentences = parse(new File(folderPath));
		int count = 0; 
		for(PubSentence sentence : sentences){
			sentenceService.insert(sentence);
			count ++;
			if(count %200 == 0){
				System.out.println("run ~~ "+ count);
			}
		}
	}
	private void runLabel(){
		List<PubSentence> sentences = parseLabel(new File(folderPath));
		int count = 0; 
		for(PubSentence sentence : sentences){
			sentenceService.insert(sentence);
			count ++;
			if(count %200 == 0){
				System.out.println("runLabel ~~ "+ count);
			}
		}
	}
	Pattern datePattern = Pattern.compile("^\\d+-\\d+-\\d+$");
	private boolean matchDate(String line){
		Matcher m = datePattern.matcher(line);
		if(m.find()){
			return true;
		}else{
			return false;
		}
	}
	private List<PubSentence> parseLabel(File folder){
		List<PubSentence> sentences = new ArrayList<>();
		for(File tmpFile : folder.listFiles()){
			String eventName = tmpFile.getName();
			for(File t : tmpFile.listFiles()){
				if( t.getName().equals("timelines")){
					for(File label : t.listFiles()){
						if(!label.getName().endsWith("txt")){
							logger.error("這是什麼文件混在裏面了？：{}", label.getAbsolutePath());
							continue;
						}
						BufferedReader br;
						try {
							br = new BufferedReader(new FileReader(label));
							String line =null;
							line = br.readLine();
						
							String date = null;
							while(line != null){
								boolean isDate = matchDate(line);
								if(isDate){
									date = line;
									line = br.readLine();
									continue;
								}
								if(line.contains("-------------------------")){
									line = br.readLine();
									continue;
								}
								PubSentence sentence = new PubSentence();
								sentence.setSummary(true);
								sentence.setPublishDate(date);
								sentence.setDocName(label.getName());
								sentence.setEventName(eventName);
								sentence.setContent(line);
								sentences.add(sentence);
								if(line.length() > 1000){
									logger.info(sentence.toString());
									logger.info("文件名：{}, 事件名: {}",label.getName(), eventName);
								}
								line = br.readLine();
							}
							br.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		return sentences;
	}
		
	private List<PubSentence> parse(File folder){
		List<PubSentence> sentences = new ArrayList<>();
		for(File tmpFile : folder.listFiles()){
			String eventName = tmpFile.getName();
			for(File t : tmpFile.listFiles()){
				if(t.getName().equals("InputDocs")){
					for(File dateFolder : t.listFiles()){
						String date = dateFolder.getName();
						if(date.equals(".DS_Store")){
							continue;
						}
						for(File file : dateFolder.listFiles()){
							try {
								BufferedReader br = new BufferedReader(new FileReader(file));
								String line = br.readLine();
								int index = 1;
								List<PubSentence> tmpList = new ArrayList<>();
								while(line != null){
									PubSentence sentence = new PubSentence();
									sentence.setEventName(eventName);
									sentence.setDocName(file.getName());
									sentence.setPublishDate(date);
									sentence.setSentenceId(index);
									sentence.setContent(line);
									tmpList.add(sentence);
									index ++;
									line = br.readLine();
								}
								for(PubSentence sentence : tmpList){
									sentence.setTotal(index);
								}
								sentences.addAll(tmpList);
								br.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}
				}
			}
		}
		return sentences;
	}
	

}
