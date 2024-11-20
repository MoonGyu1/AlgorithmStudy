import sys

N = int(sys.stdin.read())

if N == 3:
    print("***")
    print("* *")
    print("***")
else:

    def exists (n, r, c):
        if n == 3:
            if r == 1 and c == 1:
                return False
            else:
                return True
        else:
            triplet = n // 3
            if triplet <= r < triplet + triplet and triplet <= c < triplet + triplet:
                return False
            else:
                return exists(triplet, r % triplet, c % triplet)

    num_triples = N // 3

    for r in range(num_triples):
        edge = []
        mid = []
        for c in range(num_triples):
            if exists(num_triples, r, c):
                edge.append("***")
                mid.append("* *")
            else:
                edge.append("   ")
                mid.append("   ")

        print("".join(edge))
        print("".join(mid))
        print("".join(edge))