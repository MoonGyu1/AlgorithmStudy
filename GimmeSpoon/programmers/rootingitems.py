# 1000 (8) Right
# 0100 (4) Below
# 0010 (2) Left
# 0001 (1) Up

deltas = ((1, 0), (0, -1), (-1, 0), (0, 1))
d_enum = (8, 4, 2, 1)

# [0] : Left-Bottom, [1] : Left-Top, [2] : Right-Top, [3] : Right-Bottom
corner_types = (2 | 4, 2 | 1, 8 | 1, 8 | 4)
# [0] : vertical (Right Open), [0] : vertical (Left Open)
# [2] : horizontal (Up Open), [3] : horizontal (Below Open)
edge_types = (1 | 4, 1 | 4 | 16, 2 | 8, 2 | 8 | 16)

repr = {
    -1 : "X",
    0 : " ",
    3 : "┘",
    5 : "│",
    6 : "┐",
    9 : "└",
    10 : "─",
    12 : "┌",
    21 : "│",
    26 : "─"
}

def print_terrain(terrain, target = None):
    for y in range(51, -1, -1):
        for x in range(52):
            if target is not None and (x, y) == target:
                print("X", end=" ")
            else:
                print(repr[terrain[x][y]], end=" ")
        print()
    
def solution(rectangle, characterX, characterY, itemX, itemY):
    
    if characterX == itemX and characterY == itemY:
        return 0
    
    terrain = [[0 for _ in range(52)] for _ in range(52)]
    
    for x1, y1, x2, y2 in rectangle:
        
        for x in range(x1, x2 + 1):
            for y in range(y1, y2 + 1):
                if terrain[x][y] >= 0:
                    if x in (x1, x2):
                        if terrain[x][y] in edge_types[2:]: # Intersection
                            if x == x1: # Left
                                if not terrain[x][y] & 16: # Up
                                    terrain[x][y] = corner_types[1]
                                else: # Below
                                    terrain[x][y] = corner_types[0]
                            else: # Right
                                if not terrain[x][y] & 16: # Up
                                    terrain[x][y] = corner_types[2]
                                else: # Below
                                    terrain[x][y] = corner_types[3]
                        else:
                            if x == x1: # Left Open
                                terrain[x][y] = edge_types[1]
                            else:
                                terrain[x][y] = edge_types[0]
                                
                    elif y in (y1, y2):
                        if terrain[x][y] in edge_types[:2]: # Intersection
                            if y == y1: # Below
                                if not terrain[x][y] & 16: # Right
                                    terrain[x][y] = corner_types[3]
                                else: # Left
                                    terrain[x][y] = corner_types[0]
                            else: # Up
                                if not terrain[x][y] & 16: # Right
                                    terrain[x][y] = corner_types[2]
                                else: # Left
                                    terrain[x][y] = corner_types[1]
                        else:
                            if y == y1: # Below Open
                                terrain[x][y] = edge_types[3]
                            else:
                                terrain[x][y] = edge_types[2]
                    else:
                        terrain[x][y] = -1
        
        for d, (x, y) in enumerate(((x2, y2), (x2, y1), (x1, y1), (x1, y2))):
            if terrain[x][y] >= 0:
                terrain[x][y] = corner_types[d]
        
    print_terrain(terrain, (itemX, itemY))
                
    #print_terrain(terrain)
                
    # find the directions
    dist = 0
    TARGET = (itemX, itemY)
    coords = [(characterX, characterY), (characterX, characterY)]
    visited = set([(characterX, characterY)])
    directions = []
    
    for i, d in enumerate(d_enum):
        if d & terrain[characterX][characterY]:
            directions.append(i)
    
    
    while coords[0] != TARGET and coords[1] != TARGET:
        
        if dist > 100:
            break
            
        for i, ((x, y), d) in enumerate(zip(coords, directions)):
            
            if terrain[x][y] in corner_types:
                d = (d + 1) % 4
                if d_enum[d] & terrain[x][y]:
                    coords[i] = (x + deltas[d][0], y + deltas[d][1])
                    directions[i] = d
                    continue
                    
                d = (d + 2) % 4
                if d_enum[d] & terrain[x][y]:
                    coords[i] = (x + deltas[d][0], y + deltas[d][1])
                    directions[i] = d
            else:
                x, y = x + deltas[d][0], y + deltas[d][1]
                coords[i] = (x, y)
        
        dist += 1
    
    return dist
