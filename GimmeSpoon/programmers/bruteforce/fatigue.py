def solution(k, dungeons, exp = 0):
    
    if len(dungeons) == 0:
        return exp
    
    max_exp = exp
    for i, (min_req, cost) in enumerate(dungeons):
        if k >= min_req:
            explored = solution(k - cost, dungeons[:i] + dungeons[i+1:], exp + 1)
            if max_exp < explored:
                max_exp = explored
        
    return max_exp