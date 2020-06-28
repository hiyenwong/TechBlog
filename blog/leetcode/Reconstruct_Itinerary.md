
# Reconstruct Itinerary

Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:

If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
All airports are represented by three capital letters (IATA code).
You may assume all tickets form at least one valid itinerary.
One must use all the tickets once and only once.
## Example 1:
```
Input: [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
Output: ["JFK", "MUC", "LHR", "SFO", "SJC"]
Example 2:
```

```
Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
```

## code

```java
class Solution {
	public List<String> findItinerary(List<List<String>> tickets) {
		for (List<String> pair:tickets){
			String key = pair.get(0);
			String value = pair.get(1);
			if(!map.containsKey(key)){
				PriorityQueue<String> p = new PriorityQueue<>();
				p.add(value);
				map.put(key, p);
			} else{
				map.get(key).add(value);
			}
		}
		getPath("JFK");
		return res;
	}
	private void getPath(String begin){
		while(map.containsKey(begin) && map.get(begin).size() != 0){
			getPath(map.get(begin).poll());
		}
		res.add(0, begin);
	}
	private List<String> res = new ArrayList<>();
	private Map<String, PriorityQueue<String>> map = new HashMap<>();
}
```