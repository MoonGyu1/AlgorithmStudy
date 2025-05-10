import queue

def solution(storage, requests):
    storage = list(map(list, storage))
    leftover = len(storage) * len(storage[0])
    
    def expand (r, c, expanded, req):
        ret = 0
        to_expand = [(r, c)]
        expanded.add((r, c))

        while len(to_expand) > 0:
            r, c = to_expand.pop()
            for _r, _c in ((r + 1, c), (r - 1, c), (r, c + 1), (r, c - 1)):
                if 0 <= _r < len(storage) and 0 <= _c < len(storage[0]) and (_r, _c) not in expanded:
                    expanded.add((_r, _c))
                    if storage[_r][_c] == " ":
                        to_expand.append((_r, _c))
                    elif storage[_r][_c] == "*":
                        storage[_r][_c] = " "
                        to_expand.append((_r, _c))
                    elif storage[_r][_c] == req:
                        storage[_r][_c] = " "
                        ret += 1
        return ret

    for req in requests:
        if len(req) == 2: #crane
            for r, row in enumerate(storage):
                for c, t in enumerate(row):
                    if t == req[0]:
                        storage[r][c] = "*"
                        leftover -= 1
        else: #forklift
            checked = set()
            for r, row in enumerate(storage):
                for c, t in enumerate(row):
                    if (r, c) not in checked:
                        if t == " ":
                            leftover -= expand(r, c, checked, req)
                        elif r in (0, len(storage) - 1) or c in (0, len(storage[0]) - 1):
                            if storage[r][c] == req:
                                storage[r][c] = " "
                                leftover -= 1
                            elif storage[r][c] == "*":
                                storage[r][c] = " "
                                leftover -= expand(r, c, checked, req)
                            checked.add((r, c))

        for row in storage:
            print(row)
        print(leftover)
    
    return leftover
