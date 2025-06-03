def solution(grid):
    
    def find_chain(graph, r, c, d):
        to_search = [(r, c, _d) for _d in (1, 2, 4, 8) if d & _d]
        searched = set([(r, c)])
        ret = set([(r, c, d)])
        while len(to_search) > 0:
            r, c, d = to_search.pop()
            for _r, _c, _d in ((r + 1, c, 1), (r - 1, c, 4), (r, c + 1, 8), (r, c - 1, 2)):
                if d in (_d * 4, _d // 4) and 0 <= _r < len(graph) and 0 <= _c < len(graph[0]) and (_r, _c) not in searched:
                    to_search.append((_r, _c, graph[_r][_c][_d]))
                    searched.add((_r, _c))
                    ret.add((_r, _c, _d + graph[_r][_c][_d]))

        return ret

    graph = []
    searched = set()
    max_l = 0

    for r, row in enumerate(grid):
        graph.append([])
        for c, diag in enumerate(row):
            if diag == 1:
                graph[r].append({1:8, 2:4, 4:2, 8:1})
            else:
                graph[r].append({1:2, 2:1, 4:8, 8:4})

    for r, row in enumerate(grid):
        for c, diag in enumerate(row):
            if diag == 1:
                t1, t2 = ((r, c, 9), (r, c, 6))
            else:
                t1, t2 = ((r, c, 3), (r, c, 12))

            if t1 not in searched:
                s = find_chain(graph, *t1)
                l = len(s)
                if l > max_l:
                    max_l = l
                searched = searched | s

            if t2 not in searched:
                s = find_chain(graph, *t2)
                l = len(s)
                if l > max_l:
                    max_l = l
                searched = searched | s

    return max_l
