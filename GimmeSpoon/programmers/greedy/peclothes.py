def solution(n, lost, reserve):
    answer = n
    lost, reserve = set(lost), set(reserve)
    for s in sorted(lost):
        if s not in reserve:
            if s - 1 not in reserve:
                if s + 1 not in reserve or s + 1 in lost:
                    answer -= 1
                else:
                    reserve.remove(s + 1)
        else:
            reserve.remove(s)        
                    
    return answer