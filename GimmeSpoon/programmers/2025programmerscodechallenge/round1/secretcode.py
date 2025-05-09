import itertools

def solution(n, q, ans):
    pos_combs = [set()]
    neg_combs = [set()]
    for query, matched in zip(q, ans):
        _pos_combs = []
        _neg_combs = []
        for pos_comb, neg_comb in zip(pos_combs, neg_combs):
            for _pos_comb in itertools.combinations(query, matched):
                _neg_comb = neg_comb | (set(query) - set(_pos_comb))
                _pos_comb = set(_pos_comb) | pos_comb
                if len(_pos_comb) <= 5 and len(_pos_comb & _neg_comb) == 0:
                    _pos_combs.append(_pos_comb)
                    _neg_combs.append(_neg_comb)
        pos_combs = _pos_combs
        neg_combs = _neg_combs

    n_combs = 0
    for pos_comb, neg_comb in zip(pos_combs, neg_combs):
        if len(pos_comb) == 5:
            n_combs += 1
        else:
            left = set([x for x in range(1, n + 1)]) - pos_comb - neg_comb
            if len(left) >= 5 - len(pos_comb):
                right = 1
                for x in range(1, 5 - len(pos_comb) + 1):
                    right = right * (len(left) - x + 1) // x
                n_combs += right

    return n_combs

if __name__ == "__main__":
    print(solution(10, [[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [3, 7, 8, 9, 10], [2, 5, 7, 9, 10], [3, 4, 5, 6, 7]], [2, 3, 4, 3, 3]))
    print(solution(15, [[2, 3, 9, 12, 13], [1, 4, 6, 7, 9],[1, 2, 8, 10, 12], [6, 7, 11, 13, 15], [1, 4, 10, 11, 14]], [2, 1, 3, 0, 1]))
