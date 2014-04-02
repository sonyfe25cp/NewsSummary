package service.pub;

import java.util.HashMap;
import java.util.Map;

public class CountMapper {
	private Map<String, Integer> countMap = new HashMap<String, Integer>();;
	int num = -1;

	public int getSeqNumber(String str) {// 返回从0开始
		if (countMap.containsKey(str)) {
			return countMap.get(str);
		} else {
			num++;
			countMap.put(str, num);
		}
		return num;
	}
}
