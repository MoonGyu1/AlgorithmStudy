def solution(sizes):
    minl, maxl = 0, 0
    
    for size in sizes:
        s, l = min(size), max(size)
        if s > minl:
            minl = s
        if l > maxl:
            maxl = l
    
    return minl * maxl