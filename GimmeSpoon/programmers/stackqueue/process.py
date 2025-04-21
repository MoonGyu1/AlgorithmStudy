def solution(priorities, location):
    pcs = [[] for _ in range(9)]
    target_priority = 0
    for i, p in enumerate(priorities):
        pcs[p - 1].append(i)
        if i == location:
            target_priority = p - 1
        
    order = 0
    cur_idx = 0
    executed = [False for _ in range(len(priorities))]
    for p in range(8, -1, -1):
        if len(pcs[p]) > 0:
            if p != target_priority:
                order += len(pcs[p])
                cur_idx = sorted(pcs[p], key=lambda x: (x - cur_idx) % len(priorities))[-1]
            else:
                return sorted(pcs[p], key=lambda x: (x - cur_idx) % len(priorities)).index(location) + order + 1