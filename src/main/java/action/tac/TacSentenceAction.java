package action.tac;

import java.util.List;

import model.tac.TacSentence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import service.tac.TacSentenceService;

@Controller
@RequestMapping("/tac")
public class TacSentenceAction {
	@Autowired
	private TacSentenceService sentenceService;

	@RequestMapping("/events")
	public ModelAndView listEvents() {
		List<String> events = sentenceService.findEvents();
		return new ModelAndView("/tac/events").addObject("eventNames", events);
	}

	@RequestMapping("/events/byName")
	public ModelAndView listDocs(@RequestParam String eventName) {

		List<String> docs = sentenceService.findDocumentsByEventname(eventName);

		return new ModelAndView("/tac/eventsByDoc").addObject("docNames", docs);
	}

	@RequestMapping("/docs/byName")
	public ModelAndView listSentences(@RequestParam String docName) {

		List<TacSentence> sentences = sentenceService.findByDocName(docName);

		return new ModelAndView("/tac/sentences").addObject("sentences",
				sentences);
	}

}
