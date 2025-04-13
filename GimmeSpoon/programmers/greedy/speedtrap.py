def solution(routes):
    
    num_cameras = 0
    t = -30001
    routes = sorted(routes, key=lambda x: (x[1], x[0]))
    for i, o in routes:
        if t < i:
            num_cameras += 1
            t = o
    
    return num_cameras