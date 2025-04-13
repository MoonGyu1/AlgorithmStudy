def solution(triangle):
    acc = triangle[0]
    for rs in triangle[1:]:
        _acc = []
        for i in range(len(rs)):
            _acc.append(max(acc[max(i - 1, 0)], acc[min(i, len(rs) - 2)]) + rs[i])
        acc = _acc
    return max(acc)