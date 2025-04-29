import queue

def solution(number, k):
    answer = []
    
    q = queue.PriorityQueue()
    for i, d in enumerate(number[:k + 1]):
        q.put((-int(d), i))
    last_added = k
    cur_idx = 0
        
    while not q.empty():
        d, i = q.get()
        if cur_idx <= i:
            answer.append(-d)
            k -= i - cur_idx
            cur_idx = i + 1
            for i, d in enumerate(number[last_added + 1: cur_idx + k + 1]):
                q.put((-int(d), i + last_added + 1))
            last_added = cur_idx + k
            if last_added == len(number):
                break
        
    return "".join(map(str, answer))
