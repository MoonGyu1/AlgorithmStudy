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
