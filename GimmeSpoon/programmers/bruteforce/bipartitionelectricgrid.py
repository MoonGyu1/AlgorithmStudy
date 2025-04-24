import collections, queue

def solution(n, wires):
    graph = collections.defaultdict(set)
    for a, b in wires:
        graph[a].add(b)
        graph[b].add(a)
        
    tree = collections.defaultdict(lambda : {"parent":0, "children":set(), "num_children":0, "depth":0})
    tree[1]["num_children"] = n - 1
    q = queue.Queue()
    q.put(1)
    
    depths = collections.defaultdict(list)
    
    while not q.empty():
        cur = q.get()
        for adj in graph[cur]:
            if adj not in tree:
                tree[cur]["children"].add(adj)
                tree[adj]["parent"] = cur
                tree[adj]["depth"] = tree[cur]["depth"] + 1
                depths[tree[adj]["depth"]].append(adj)
                q.put(adj)
        
        tree[cur]["num_children"] = len(tree[cur]["children"])
    
    for depth in sorted(depths.keys(), reverse=True):
        for cur in depths[depth]:
            tree[tree[cur]["parent"]]["num_children"] += tree[cur]["num_children"]
            
    min_diff = n
    for a, b in wires:
        if tree[a]["parent"] == b:
            diff = abs(n - 2 - tree[a]["num_children"] - tree[a]["num_children"])
        else:
            diff = abs(n - 2 - tree[b]["num_children"] - tree[b]["num_children"])
            
        if diff < min_diff:
            min_diff = diff
            
    return min_diff