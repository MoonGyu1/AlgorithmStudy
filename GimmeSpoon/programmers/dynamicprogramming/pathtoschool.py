def solution(m, n, puddles):
    possible_ways = [[1 for _ in range(m)] for _ in range(n)]
    for c, r in puddles:
        possible_ways[r - 1][c - 1] = 0
    for r in range(n):
        for c in range(m):
            if possible_ways[r][c] == 0:
                if r == 0 and c < m - 1:
                    possible_ways[r][c + 1] = 0
                elif c == 0 and r < n - 1:
                    possible_ways[r + 1][c] = 0
            elif r > 0 and c > 0:
                possible_ways[r][c] = possible_ways[r - 1][c] + possible_ways[r][c - 1]
                
    print(possible_ways)
            
    return possible_ways[n - 1][m - 1] % 1_000_000_007