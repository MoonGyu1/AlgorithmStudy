import collections, sys

DIRECTIONS = (
    ((0, 2),),
    ((1, 1), (1, 1)),
    ((2, 0),),
    ((1, -1), (1, -1)),
    ((0, -2),),
    ((-1, -1), (-1, -1)),
    ((-2, 0),),
    ((-1, 1), (-1, 1)),
)

def solution (arrows):
    n_rooms = 0
    x, y = 0, 0

    vertices = set([(x, y)])
    edges = set()

    for d in arrows:
        for dx, dy in DIRECTIONS[d]:
            nx, ny = x + dx, y + dy
            edge = frozenset([(x, y), (nx, ny)])
            if (nx, ny) in vertices: # Loop
                if edge not in edges:
                    n_rooms += 1
            vertices.add((nx, ny))
            edges.add(edge)
            x, y = nx, ny
        
    return n_rooms 
