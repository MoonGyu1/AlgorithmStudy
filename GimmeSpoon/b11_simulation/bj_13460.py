import io, os, queue, copy

txt = io.BytesIO(os.read(0, os.fstat(0).st_size)).read().decode().split()
N, M = int(txt[0]), int(txt[1])

def hittest(lboard, r, c, direction):

    if direction == "w":

        while lboard[r - 1][c] == ".":
            r -= 1
        
        if lboard[r - 1][c] == "O":
            return (r - 1, c, True)
        else:
            return (r, c, False)

    elif direction == "a":
        
        while lboard[r][c - 1] == ".":
            c -= 1
        
        if lboard[r][c - 1] == "O":
            return (r, c - 1, True)
        else:
            return (r, c, False)

    elif direction == "s":

        while lboard[r + 1][c] == ".":
            r += 1
        
        if lboard[r + 1][c] == "O":
            return (r + 1, c, True)
        else:
            return (r, c, False)

    elif direction == "d":

        while lboard[r][c + 1] == ".":
            c += 1

        if lboard[r][c + 1] == "O":
            return (r, c + 1, True)
        else:
            return (r, c, False)

red, blue = None, None
for r, row in enumerate(txt[3:]):
    for c, cell in enumerate(row):
        if cell == "R":
            red = (r + 1, c)
        if cell == "B":
            blue = (r + 1, c)
        if red is not None and blue is not None:
            break
    else:
        continue
    break

q = queue.Queue()
q.put((red, blue, 0, list(map(list, txt[2:]))))
visited = {}

while not q.empty():

    (rr, rc), (br, bc), cnt, board = q.get()

    if cnt >= 10:
        continue
    # Vertical Hittest

    if rr < br: # Red first for upward, Blue first for downward

        # Upward (Red first)

        wboard = copy.deepcopy(board)
        moved = False
        eol = False

        r, _, hole = hittest(wboard, rr, rc, "w")

        if rr != r:
            moved = True
            wboard[rr][rc] = "."

            if hole:
                eol = True
            else:
                wboard[r][rc] = "R"

        red = r
        
        r, _, hole = hittest(wboard, br, bc, "w")

        if not hole:

            if eol:
                print(cnt + 1)
                break

            if br != r:
                moved = True
                wboard[br][bc] = "."
                wboard[r][bc] = "B"

            if moved and (red, rc, r, bc) not in visited:
                visited[(red, rc, r, bc)] = 0
                q.put(((red, rc), (r, bc), cnt + 1, wboard))

        # Downward (Blue first)

        wboard = copy.deepcopy(board)
        moved = False

        r, c, hole = hittest(wboard, br, bc, "s")

        if not hole:

            if br != r:
                moved = True
                wboard[br][bc] = "."
                wboard[r][bc] = "B"        

            blue = r
            
            r, c, hole = hittest(wboard, rr, rc, "s")

            if hole:
                print(cnt + 1)
                break

            if rr != r:
                moved = True
                wboard[rr][rc] = "."
                wboard[r][rc] = "R"

            if moved and (r, rc, blue, bc) not in visited:
                visited[(r, rc, blue, bc)] = 0
                q.put(((r, rc), (blue, bc), cnt + 1, wboard))

    else: # Blue first for upward, Red first for downward
        
        # Upward (Blue first)

        wboard = copy.deepcopy(board)
        moved = False

        r, c, hole = hittest(wboard, br, bc, "w")

        if not hole:

            if br != r:
                moved = True
                wboard[br][bc] = "."
                wboard[r][bc] = "B"  

            blue = r
        
            r, c, hole = hittest(wboard, rr, rc, "w")

            if hole:
                print(cnt + 1)
                break

            if rr != r:
                moved = True
                wboard[rr][rc] = "."
                wboard[r][rc] = "R"

            if moved and (r, rc, blue, bc) not in visited:
                visited[(r, rc, blue, bc)] = 0
                q.put(((r, rc), (blue, bc), cnt + 1, wboard))

        # Downward (Red first)

        wboard = copy.deepcopy(board)
        moved = False
        eol = False

        r, c, hole = hittest(wboard, rr, rc, "s")

        if rr != r:
            moved = True
            wboard[rr][rc] = "."

            if hole:
                eol = True
            else:
                wboard[r][rc] = "R"

        red = r

        r, c, hole = hittest(wboard, br, bc, "s")

        if not hole:

            if eol:
                print(cnt + 1)
                break

            if br != r:
                moved = True
                wboard[br][bc] = "."
                wboard[r][bc] = "B" 

            if moved and (red, rc, r, bc) not in visited:
                visited[(red, rc, r, bc)] = 0
                q.put(((red, rc), (r, bc), cnt + 1, wboard))

    # Horizontal

    if rc < bc: # Red first for left, Blue first for right

        # Left (Red first)

        wboard = copy.deepcopy(board)

        moved = False
        eol = False
        r, c, hole = hittest(wboard, rr, rc, "a")

        if rc != c:
            moved = True
            wboard[rr][rc] = "."

            if hole:
                eol = True
            else:
                wboard[rr][c] = "R"

        red = c
        
        r, c, hole = hittest(wboard, br, bc, "a")

        if not hole:

            if eol:
                print(cnt + 1)
                break

            if bc != c:
                moved = True
                wboard[br][bc] = "."
                wboard[br][c] = "B" 

            if moved and (rr, red, br, c) not in visited:
                visited[(rr, red, br, c)] = 0
                q.put(((rr, red), (br, c), cnt + 1, wboard))

        # Right (Blue first)

        wboard = copy.deepcopy(board)
        moved = False

        r, c, hole = hittest(wboard, br, bc, "d")

        if not hole:

            if bc != c:
                moved = True
                wboard[br][bc] = "."
                wboard[br][c] = "B" 

            blue = c
            
            r, c, hole = hittest(wboard, rr, rc, "d")

            if hole:
                print(cnt + 1)
                break

            if rc != c:
                moved = True
                wboard[rr][rc] = "."
                wboard[rr][c] = "R"

            if moved and (rr, c, br, blue) not in visited:
                visited[(rr, c, br, blue)] = 0
                q.put(((rr, c), (br, blue), cnt + 1, wboard))

    else: # Blue first for left, Red first for right
        
        # Left (Blue first)

        wboard = copy.deepcopy(board)

        moved = False
        r, c, hole = hittest(wboard, br, bc, "a")

        if not hole:

            if bc != c:
                moved = True
                wboard[br][bc] = "."
                wboard[br][c] = "B" 

            blue = c

            r, c, hole = hittest(wboard, rr, rc, "a")

            if hole:
                print(cnt + 1)
                break

            if rc != c:
                moved = True
                wboard[rr][rc] = "."
                wboard[rr][c] = "R"
                
            if moved and (rr, c, br, blue) not in visited:
                visited[(rr, c, br, blue)] = 0
                q.put(((rr, c), (br, blue), cnt + 1, wboard))

        # Right (Red first)

        wboard = copy.deepcopy(board)
        moved = False
        eol = False

        r, c, hole = hittest(wboard, rr, rc, "d")

        if rc != c:
            moved = True
            wboard[rr][rc] = "."

            if hole:
                eol = True
            else:
                wboard[rr][c] = "R"

        red = c

        r, c, hole = hittest(wboard, br, bc, "d")

        if not hole:

            if eol:
                print(cnt + 1)
                break

            if bc != c:
                moved = True
                wboard[br][bc] = "."
                wboard[br][c] = "B" 

            if moved and (rr, red, br, c) not in visited:
                visited[(rr, red, br, c)] = 0
                q.put(((rr, red), (br, c), cnt + 1, wboard))
else:
    print(-1)