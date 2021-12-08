package framework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestStatusContainer {

	private static Map<String, List<String>> statusContainer = new HashMap<>();

	private static Map<String, String> statusContainerString = new HashMap<>();

	
	public Map<String, List<String>> getStatusContainer() {
		return statusContainer;
	}
	
	public Map<String, String> getStatusContainerString() {
		return statusContainerString;
	}

	public void addObjList(String key, List<String> valueList) {
		statusContainer.put(key, valueList);

	}
	
	public void addString(String key, String valueList) {
		statusContainerString.put(key, valueList);

	}
	
	public String getString(String key) {
		return statusContainerString.get(key);

	}
	
	public List<String> getObjList(String key) {
		return statusContainer.get(key);

	}
	
}
