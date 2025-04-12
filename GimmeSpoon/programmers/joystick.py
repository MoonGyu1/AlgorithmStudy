def solution(name):
    
    total_actions = 0
    non_A_indexes = [0]
    
    for i, char in enumerate(name):
        if char != "A":
            char = ord(char)
            total_actions += min(char - ord("A"), ord("Z") - char + 1)
            if i > 0:
                non_A_indexes.append(i)
            
    min_moves = len(name)
    
    for i, idx in enumerate(non_A_indexes):
        if i < len(non_A_indexes) - 1:
            moves = min(idx * 2 + len(name) - non_A_indexes[i + 1],
                        idx + (len(name) - non_A_indexes[i + 1]) * 2)
        elif i > 0:
            moves = min(idx, len(name) - idx + non_A_indexes[i - 1] * 2)
        else:
            moves = 0
            
        if moves < min_moves:
            min_moves = moves
            
    return total_actions + min_moves
