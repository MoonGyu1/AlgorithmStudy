def solution(distance, rocks, n):
    
    if n == len(rocks): # trivial case
        return distance
    
    def shortest_interval(arr, max_interval, s):
        """Determine if the intervals between rocks are equal or greater than
        the given distance 'max_interval'.
            arr : position of the rocks and the destination
            max_interval : the distance to be tested
            s : the number of rocks to be excluded
        """    
        last_rock = 0
        
        for r in arr:
            if r - last_rock < max_interval:
                if s == 0:
                    return False
                s -= 1
            else:
                last_rock = r
                
        return True
    
    rocks = sorted(rocks) + [distance] # add destination to the list
    
    start, end = 0, distance
    min_interval = (start + end) // 2
    
    # binary search for minimum interval between rocks
    while start < min_interval < end:
        
        if shortest_interval(rocks, min_interval, n):
            start = min_interval
        else:
            end = min_interval

        min_interval = (start + end) // 2
        
    if shortest_interval(rocks, start, n):
        return start
    elif shortest_interval(rocks, min_interval, n):
        return min_interval    
    else:
        return end
