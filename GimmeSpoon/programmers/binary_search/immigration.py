def solution(n, times):
    
    def bs(t, r, rts):
        
        if t == 0:
            return False
        
        if t == 1_000_000_000_000_000_000:
            return True
        
        for rt in rts:
            r -= t // rt
            if r <= 0:
                return True
        return False
    
    times = sorted(times)

    start = 0
    end = 1_000_000_000_000_000_000
    k = (start + end) // 2
    
    while start < k < end:
        if bs(k, n, times):
            end = k
        else:
            start = k
        k = (start + end) // 2
            
    if bs(start, n, times):
        return start
    elif bs(k, n, times):
        return k
    else:
        return end