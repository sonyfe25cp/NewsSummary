//package action;
//
//import model.TestModel;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import service.SentenceService;
//import service.tfidf.TFIDFSummaryService;
//
//@Controller
//public class TestAction {
//
//	@Autowired
//	private SentenceService sentenseService;
//
//	@Autowired
//	private TFIDFSummaryService tFIDFSummaryService;
//	
//	@RequestMapping(value="/test", method=RequestMethod.POST)
//	public ModelAndView test(@RequestParam String html){
//		String hah = "chifan qu ";
//		TestModel tm = new TestModel();
//		tm.setId(1);
//		tm.setName("achen");
//		tm.setTitle("pytohn");
//		
//		String summary = tFIDFSummaryService.compute(html);
//		
//		return new ModelAndView("/test/hello")
//			.addObject("haha", hah)
//			.addObject("testModel", tm)
//			.addObject("summary", summary);
//	}
//
//	public SentenceService getSentenseService() {
//		return sentenseService;
//	}
//	public void setSentenseService(SentenceService sentenseService) {
//		this.sentenseService = sentenseService;
//	}
//}
