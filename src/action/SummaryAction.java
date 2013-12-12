package action;

import model.SummaryRequest;
import model.SummaryResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


public class SummaryAction {

	@RequestMapping(value = "/summaryService", method=RequestMethod.POST)
	@ResponseBody
	public SummaryResponse generateSummary(@RequestParam String requestJson){
		
		SummaryRequest requestBody = null;
		
//		List<String> texts 
		
		return null;
	}
}
