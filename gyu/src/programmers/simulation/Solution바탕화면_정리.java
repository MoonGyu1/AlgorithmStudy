// level 1

package src.programmers.simulation;

//시간복잡도: O(N^2)
class Solution바탕화면_정리 {
	public int[] solution(String[] wallpaper) {
		int lux = Integer.MAX_VALUE, luy = Integer.MAX_VALUE;
		int rdx = Integer.MIN_VALUE, rdy = Integer.MIN_VALUE;

		for (int i = 0; i < wallpaper.length; i++) {
			for (int j = 0; j < wallpaper[0].length(); j++) {
				if (wallpaper[i].charAt(j) == '.')
					continue;

				lux = Math.min(lux, i);
				luy = Math.min(luy, j);
				rdx = Math.max(rdx, i);
				rdy = Math.max(rdy, j);
			}
		}

		return new int[] { lux, luy, rdx + 1, rdy + 1 };
	}
}