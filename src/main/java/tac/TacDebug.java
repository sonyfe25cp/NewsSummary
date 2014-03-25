package tac;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TacDebug {

	static Logger logger = LoggerFactory.getLogger(TacDebug.class);

	public static List<Entry<String, Integer>> sortIntegerMap(
			Map<String, Integer> map) {
		List<Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			@Override
			public int compare(Entry<String, Integer> o1,
					Entry<String, Integer> o2) {
				if (o1.getValue() > o2.getValue()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		for (Entry<String, Integer> entry : list) {
			logger.info("key: {}, value: {}", entry.getKey(), entry.getValue());
		}
		return list;
	}

	public static List<Entry<String, Double>> sortDoubleMap(
			Map<String, Double> map) {
		ArrayList<Entry<String, Double>> list = new ArrayList<>(map.entrySet());
		Collections.sort(list, new Comparator<Entry<String, Double>>() {
			@Override
			public int compare(Entry<String, Double> o1,
					Entry<String, Double> o2) {
				if (o1.getValue() - o2.getValue() > 0) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		for (Entry<String, Double> entry : list) {
			logger.info("key: {}, value: {}", entry.getKey(), entry.getValue());
		}
		return list;
	}
}
