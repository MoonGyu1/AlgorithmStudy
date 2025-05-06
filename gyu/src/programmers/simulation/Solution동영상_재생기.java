// level 1

package src.programmers.simulation;

class Solution동영상_재생기 {

	static int toSeconds(String time) {
		String[] tmp = time.split(":");
		return Integer.parseInt(tmp[0]) * 60 + Integer.parseInt(tmp[1]);
	}

	static String toTime(int seconds) {
		return String.format("%02d:%02d", seconds / 60, seconds % 60);
	}

	public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
		int video = toSeconds(video_len);
		int current = toSeconds(pos);
		int start = toSeconds(op_start);
		int end = toSeconds(op_end);

		if (start <= current && current <= end) {
			current = end;
		}

		for (String c : commands) {
			if (c.equals("prev")) {
				current = Math.max(0, current - 10);
			} else {
				current = Math.min(current + 10, video);
			}

			if (start <= current && current <= end) {
				current = end;
			}
		}

		return toTime(current);
	}
}