import sys

inp = sys.stdin.readlines()
N, M = list(map(int, inp[0].split()))

def compare(gene1, gene2):
    merged = []
    for g1, g2 in zip(gene1, gene2):
        if g1 != ".":
            if g2 != ".":
                if g1 != g2:
                    return None
                else:
                    merged.append(g1)
            else:
                merged.append(g1)
        else:
            merged.append(g2)

    return merged

ret = 0
covered_templates = {
    i : [] for i in range(1, N + 1)
}

for i, t in enumerate(list(map(str.strip, inp[1:]))):
    covered_templates[1].append({
        "repr" : t,
        "idx" : set([i])
    })

for n, templates in covered_templates.items():
    if n > 1:
        for t1 in enumerate(templates):
            for t2 in enumerate(covered_templates[1]):
                if len(t1["idx"] & t2["idx"]) == 0:
                    possible_gene = compare(t1, t2)
                    if possible_gene is not None:
                        templates.append({
                            "repr" : possible_gene,
                            "idx" : t1["idx"] | t2["idx"]
                        })

print(covered_templates)
print(ret)
    