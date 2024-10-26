import io, os, sys, collections

trees = list(map(lambda x: str(x).strip(), sys.stdin.readlines()))

trees = collections.Counter(trees)

for tree in sorted(trees.keys()):
    sys.stdout.write(f"{tree} {trees[tree] / trees.total() * 100:.4f}\n")