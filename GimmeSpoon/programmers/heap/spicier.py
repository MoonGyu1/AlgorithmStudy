import heapq

def solution(scoville, K):
    heapq.heapify(scoville)
    answer = 0
    while scoville[0] < K:
        if len(scoville) == 1:
            return -1
        elif len(scoville) == 2:
            if scoville[0] + scoville[1] >= K:
                return answer + 1
            else:
                return -1
            
        heapq.heappush(scoville, scoville[0] + min(scoville[1], scoville[2]) * 2)
        heapq.heappop(scoville)
        heapq.heappop(scoville)
        answer += 1
    
    return answer