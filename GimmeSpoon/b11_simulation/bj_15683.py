import io, os, itertools

txt = list(map(lambda x: x.decode().split(), io.BytesIO(os.read(0, os.fstat(0).st_size)).readlines()))

N, M = int(txt[0][0]), int(txt[0][1])

cameras = []

office = txt[1:]

for r in range(N):
    for c in range(M):
        if office[r][c] != "0" and office[r][c] != "6":
            cameras.append((r, c, office[r][c]))

def ray(office, r, c, direction, fill = "#"):
    
    checked = []
    if direction == "w":
        while r >= 0 and office[r][c] != "6":
            if office[r][c] == "0":
                office[r][c] = fill
                checked.append((r, c))
            r -= 1
    if direction == "a":
        while c >= 0 and office[r][c] != "6":
            if office[r][c] == "0":
                office[r][c] = fill
                checked.append((r, c))
            c -= 1
    if direction == "s":
        while r < N and office[r][c] != "6":
            if office[r][c] == "0":
                office[r][c] = fill
                checked.append((r, c))
            r += 1
    if direction == "d":
        while c < M and office[r][c] != "6":
            if office[r][c] == "0":
                office[r][c] = fill
                checked.append((r, c))
            c += 1

    return checked

def clean(office, checked):
    for r, c in checked:
        office[r][c] = "0"

def min_blind(office, cameras):
    
    if len(cameras) == 0:
        ret = 0
        for r in range(N):
            for c in range(M):
                if office[r][c] == "0":
                    ret += 1
        return ret
    
    cidx = -len(cameras)
    r, c, camera = cameras[0]

    minimum_blind = N * M
    if camera == "1":
        for d in ("w", "a", "s", "d"):
            checked = ray(office, r, c, d)
            blind = min_blind(office, cameras[1:])
            clean(office, checked)
            if minimum_blind > blind:
                minimum_blind = blind
    if camera == "2":
        for d in ("ws", "ad"):
            checked = ray(office, r, c, d[0])
            checked += ray(office, r, c, d[1])
            blind = min_blind(office, cameras[1:])
            clean(office, checked)
            if minimum_blind > blind:
                minimum_blind = blind
    if camera == "3":
        for d in ("wd", "ds", "sa", "aw"):
            checked = ray(office, r, c, d[0])
            checked += ray(office, r, c, d[1])
            blind = min_blind(office, cameras[1:])
            clean(office, checked)
            if minimum_blind > blind:
                minimum_blind = blind
    if camera == "4":
        for d in ("awd", "wds", "dsa", "saw"):
            checked = ray(office, r, c, d[0])
            checked += ray(office, r, c, d[1])
            checked += ray(office, r, c, d[2])
            blind = min_blind(office, cameras[1:])
            clean(office, checked)
            if minimum_blind > blind:
                minimum_blind = blind
    if camera == "5":
        checked = ray(office, r, c, "w")
        checked += ray(office, r, c, "a")
        checked += ray(office, r, c, "s")
        checked += ray(office, r, c, "d")
        blind = min_blind(office, cameras[1:])
        clean(office, checked)
        if minimum_blind > blind:
            minimum_blind = blind
    
    return minimum_blind

print(min_blind(office, cameras))