import sys, itertools, copy

inp = sys.stdin.readlines()
n, m = list(map(int, inp[0].split()))
trees = []
for line in inp[1:n+1]:
    trees.append(list(map(int, line.split())))
friends = []
for line in inp[n+1:]:
    friends.append(tuple(map(lambda x: int(x) - 1, line.split())))

def dfs(n_fruits, sec, visited, friends):

    for friend in friends:
        if friend not in visited:
            n_fruits += trees[friend[0]][friend[1]]

    if sec == 3:
        return n_fruits
    
    max_fruits = 0
    next_state = []
    for directions in itertools.product(((1, 0), (-1, 0), (0, -1), (0, 1)), repeat=len(friends)):
        next_friends = []
        for direction, friend in zip(directions, friends):
            coord = (direction[0] + friend[0], direction[1] + friend[1])
            if coord[0] >= n or coord[0] < 0 or coord[1] >= n or coord[1] < 0 or coord in next_friends:
                break
            next_friends.append(coord)
        else:
            next_visited = copy.deepcopy(visited)
            for friend in friends:
                next_visited.add(friend)
            res = dfs(n_fruits, sec + 1, next_visited, next_friends)
            if res > max_fruits:
                max_fruits = res
    
    return max_fruits

print(dfs(0, 0, set(), friends))