import sys

N = int(sys.stdin.read())

TOP = "  *  "
MID = " * * "
BTM = "*****"
EPT = "     "

if N == 3:
    print(TOP)
    print(MID)
    print(BTM)
else:
    num_tri = N // 3
    width = num_tri * 6 - 1

    def is_filled(h, l, i):

        halfh = h // 2
        if h == 2:
            return True
        if halfh <= l < h - 1 and l - halfh < i < halfh:
            return False
        else:
            return is_filled(halfh, l % halfh, i % halfh)


    for layer in range(0, num_tri):
        filled = [is_filled(num_tri, layer, i) for i in range(0, layer + 1)]
        top, mid, bottom =  [TOP if f else EPT for f in filled], \
                            [MID if f else EPT for f in filled], \
                            [BTM if f else EPT for f in filled]
        print(" ".join(top).center(width))
        print(" ".join(mid).center(width))
        print(" ".join(bottom).center(width))