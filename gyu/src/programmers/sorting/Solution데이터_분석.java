// level 1

package src.programmers.sorting;

import java.util.*;

//시간복잡도: O(N^2)
class Solution {

	Map<String, Integer> ids = Map.of(
			"code", 0,
			"date", 1,
			"maximum", 2,
			"remain", 3
	);

	public int[][] solution(int[][] data, String ext, int val_ext, String sort_by) {
		return Arrays.stream(data)
				.filter(d -> d[ids.get(ext)] < val_ext)
				.sorted((a, b) -> a[ids.get(sort_by)] - b[ids.get(sort_by)])
				.toArray(int[][]::new);
	}
}