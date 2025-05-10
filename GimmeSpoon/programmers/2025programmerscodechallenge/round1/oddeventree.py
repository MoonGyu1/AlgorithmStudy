def solution(nodes, edges):
    forest = {node : [] for node in nodes}
    for a, b in edges:
        forest[a].append(b)
        forest[b].append(a)

    trees = []
    included = set()
    for node in nodes:
        if node not in included:
            tree = []
            searches = [node]
            included.add(node)
            while len(searches) > 0:
                _node = searches.pop()
                tree.append(_node)
                for n in forest[_node]:
                    if n not in included:
                        searches.append(n)
                        included.add(n)
            trees.append(tree)

    ret = [0, 0]
    for tree in trees:
        straight, reverse = 0, 0
        for node in tree:
            if (node & 1 and len(forest[node]) & 1) or (not(node & 1) and not(len(forest[node]) & 1)): # st when root, rv when child
                reverse += 1
            else:
                straight += 1
        if straight == len(tree) - 1:
            ret[0] += 1
        if reverse == len(tree) - 1:
            ret[1] += 1

    return ret
