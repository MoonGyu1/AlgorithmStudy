def solution(progresses, speeds):
    answer = []
    
    eld = 0
    dists = 0
    for prog, spd in zip(progresses, speeds):
        
        if prog + eld * spd >= 100:
            dists += 1
            continue
        else:
            if dists > 0:
                answer.append(dists)
            dists = 1
            
        eld = (100 - prog) // spd + bool(prog % spd)
    
    if dists > 0:
        answer.append(dists)
        
    return answer