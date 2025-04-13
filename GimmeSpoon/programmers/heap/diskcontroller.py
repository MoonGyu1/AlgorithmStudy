import heapq

def solution(jobs):
    
    answer = 0

    jobs = sorted([(s, l, i) for i, (s, l) in enumerate(jobs)])
    t = 0
    q = []
    heapq.heapify(q)
    acc_time = 0
    
    for s, l, i in jobs:
        
        while t < s and len(q) > 0:
            _l, _s, _ = heapq.heappop(q)
            t = max(t, _s) + _l
            acc_time += t - _s
        
        heapq.heappush(q, (l, s, i))
        
    while len(q):
        
        l, s, i = heapq.heappop(q)
        t = max(t, s) + l
        print(t - s)
        acc_time += t - s
        
    return acc_time // len(jobs)