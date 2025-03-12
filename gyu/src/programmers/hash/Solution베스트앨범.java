// level 3
package src.programmers.hash;

import java.util.*;

// 시간복잡도: O(NlogN)
class Solution베스트앨범 {
	public int[] solution(String[] genres, int[] plays) {
		HashMap<String, PriorityQueue<int[]>> played = new HashMap<>(); // [장르: [(재생 횟수, 고유 번호)]]
		HashMap<String, Integer> total = new HashMap<>(); // [장르: 재생 횟수]

		// 1. genres와 plays 배열을 순회하면서, 맵에 재생 횟수 저장하기
		for(int i = 0; i < genres.length; i++) {
			String genre = genres[i];
			int play = plays[i];

			// 우선순위: 재생 횟수 내림차순 > 고유 번호 오름차순
			if(!played.containsKey(genre)){
				played.put(genre, new PriorityQueue<>((a, b) -> {
					return a[0] == b[0] ? a[1] - b[1] : b[0] - a[0];
				}));
			}

			played.get(genre).add(new int[]{play, i}); // 장르: [(재생 횟수, 고유 번호)]
			total.put(genre, total.getOrDefault(genre, 0) + play); // 장르 += 재생 횟수
		}

		// 2. 우선순위큐로 장르별 재생 횟수 내림차순으로 정렬
		PriorityQueue<Genre> pq = new PriorityQueue<>((a, b) -> b.cnt - a.cnt);

		for(Map.Entry<String, Integer> e : total.entrySet()) {
			String genre = e.getKey();
			int play = e.getValue();

			pq.add(new Genre(genre, play));
		}

		ArrayList<Integer> answer = new ArrayList<>();

		while(!pq.isEmpty()) {
			String g = pq.poll().name;

			// 3. 장르별 최대 두 곡씩 꺼내기
			for(int j = 0; j < 2; j++) {
				if(!played.get(g).isEmpty()) { // 주의: 장르에 노래가 1곡만 있는 경우 예외 처리
					answer.add(played.get(g).poll()[1]);
				}
			}
		}


		return answer.stream().mapToInt(x->x).toArray();
	}

	static class Genre {
		String name;
		int cnt;

		Genre(String name, int cnt) {
			this.name = name;
			this.cnt = cnt;
		}
	}
}