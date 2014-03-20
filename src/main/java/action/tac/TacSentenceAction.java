package action.tac;

import java.util.List;

import mapper.tac.TacSentenceMapper;
import model.tac.TacSentence;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tac")
public class TacSentenceAction {

	private TacSentenceMapper sentenceMapper;

	@RequestMapping("/events")
	public ModelAndView listEvents() {
		List<String> events = sentenceMapper.findEvents();
		return new ModelAndView("/tac/events").addObject("events", events);
	}

	@RequestMapping("/events/byName")
	public ModelAndView listDocs(@RequestParam String eventName) {

		List<String> docs = sentenceMapper.findDocumentsByEventname(eventName);

		return new ModelAndView("/tac/event").addObject("docs", docs);
	}

	@RequestMapping("/docs/byName")
	public ModelAndView listSentences(@RequestParam String docName) {

		List<TacSentence> sentences = sentenceMapper.findByDocName(docName);

		return new ModelAndView("/tac/event").addObject("sentences", sentences);
	}

}
