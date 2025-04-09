import queue

def solution(name):
    answer = 0
    indexes = [0]
    for i, c in enumerate(name):
        action = min(ord(c) - ord("A"), ord("Z") - ord(c) + 1)
        answer += action
        if i != 0 and action > 0:
            indexes.append(i)
            
    max_dist = 0
    max_pair = None
    for i, idx in enumerate(indexes):        
        d = (idx - indexes[i - 1]) % len(name)
        if d > max_dist:
            max_dist = d
            max_pair = (indexes[i - 1], idx)
            
    if max_pair is not None:
        i, j = min(max_pair), max(max_pair)
        if i == 0 or j == 0:
            answer += len(name) - max_dist
        else:
            answer += min(i, len(name) - j) * 2 + max(i, len(name) - j)
    
    return answer
