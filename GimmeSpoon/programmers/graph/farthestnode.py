def solution(n, edge):
    graph = {}
    for n, m in edge:
        if n not in graph:
            graph[n] = []
        if m not in graph:
            graph[m] = []
        graph[n].append(m)
        graph[m].append(n)
    
    depth = 0
    nodes = [1]
    visited = set([1])
    
    while len(nodes):
        _nodes = []
        for node in nodes:
            for _node in graph[node]:
                if _node not in visited:
                    visited.add(_node)
                    _nodes.append(_node)
                
        if len(_nodes) == 0:
            return len(nodes)
        else:
            nodes = _nodes
            
        depth += 1
    
    return depth
        