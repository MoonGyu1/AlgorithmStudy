import collections, queue

def solution(n, results):
    
    graph = collections.defaultdict(
        lambda: {"lost_to" : [], "win_against" : []}
    )
    
    # Constitute a graph
    for a, b in results:
        graph[a]["win_against"].append(b)
        graph[b]["lost_to"].append(a)
        
    def count(g, p, f):
        """
        Count the number of nodes in a uni-direction
        """
        ret = 0
        s = set([p])
        q = queue.Queue()
        q.put(p)
        while not q.empty():
            p = q.get()
            for _p in g[p][f]:
                if _p not in s:
                    ret += 1
                    s.add(_p)
                    q.put(_p)
        return ret
            
    answer = 0
    for player in range(1, n + 1):
        # count n - 1 == num of highers + num of lowers
        w = count(graph, player, "win_against")
        l = count(graph, player, "lost_to")
        if w + l == n - 1:
            answer += 1

    return answer