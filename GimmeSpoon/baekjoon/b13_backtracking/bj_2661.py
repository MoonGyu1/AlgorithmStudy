import sys, copy, queue

N = int(sys.stdin.read())

def lt(seq1:list, seq2:list):
    if seq1 is None:
        return False
    if seq2 is None:
        return True
    for a, b in zip(seq1, seq2):
        if a < b:
            return True
        if a > b:
            return False

q = queue.PriorityQueue()
q.put((-1, 1, [1]))

while not q.empty():

    l, last_elem, seq = q.get()

    if l == -N:
        print("".join(list(map(str, seq))))
        break

    for cur in (1, 2, 3):

        nseq = copy.deepcopy(seq)
        nseq.append(cur)

        for subset_range in range(1, len(nseq) // 2 + 1):
            if nseq[-subset_range:] == nseq[-subset_range-subset_range:-subset_range]:
                break
        else:
            q.put((l - 1, cur, nseq))