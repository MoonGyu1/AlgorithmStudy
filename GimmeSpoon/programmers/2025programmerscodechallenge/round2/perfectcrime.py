def solution(info, n, m):
    total_trcs = sum([a for a, _ in info])
    a_trcs = [[0 for _ in range(m)]]
    for a, b in info:
        a_trcs.append([])
        for b_total in range(m):
            if b <= b_total:
                a_trcs[-1].append(max(a_trcs[-2][b_total], a_trcs[-2][b_total - b] + a))
            else:
                a_trcs[-1].append(a_trcs[-2][b_total])
    
    a_trc = total_trcs - max(a_trcs[-1])
    if a_trc < n:
        return total_trcs - max(a_trcs[-1])
    else:
        return -1
