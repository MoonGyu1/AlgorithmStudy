def solution(n, w, num):
    row = (num - 1) // w
    if row & 1: # right to left
        col = w - 1 - (num - 1) % w
    else: # left to right
        col = (num - 1) % w
    highest_row = (n - 1) // w
    if highest_row & 1:
        return highest_row - row + int(col >= w - 1 - (n - 1) % w)
    else:
        return highest_row - row + int(col <= (n - 1) % w)
