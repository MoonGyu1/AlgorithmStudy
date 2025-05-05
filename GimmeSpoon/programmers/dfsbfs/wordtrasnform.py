import queue

def solution(begin, target, words):
    
    if target not in words:
        return 0
    
    words.append(begin)
    graph = {w1 : {w2 : False for w2 in words} for w1 in words}
    
    def adjacent(a, b):
        differs = False
        for c1, c2 in zip(a, b):
            if c1 != c2:
                if differs:
                    return False
                else:
                    differs = True
        return True
    
    for i, w1 in enumerate(words):
        for w2 in words[i + 1:]:
            if adjacent(w1, w2):
                graph[w1][w2] = graph[w2][w1] = True
                
    searched = set([begin])
    q = queue.Queue()
    q.put((0, begin))
    while not q.empty():
        itrs, w1 = q.get()
        if w1 == target:
            return itrs
        for w2, adj in graph[w1].items():
            if adj and w2 not in searched:
                searched.add(w2)
                q.put((itrs + 1, w2))
                
    return 0