import sys, io, os, collections

readline = sys.stdin.readline
write = sys.stdout.write

N = int(readline())
tree = {}

for _ in range(N):
    root, left, right = readline().split()
    tree[root] = {"left": left, "right": right}



def search_tree(root):
    left, right = tree[root]["left"], tree[root]["right"]
    preorder, inorder, postorder = [], [], []

    preorder.append(root)

    if left != ".":
        preord, inord, postord = search_tree(left)
        preorder += preord
        inorder += inord
        postorder += postord
    
    inorder.append(root)

    if right != ".":
        preord, inord, postord = search_tree(right)
        preorder += preord
        inorder += inord
        postorder += postord

    postorder.append(root)

    return preorder, inorder, postorder

preord, inord, postord = search_tree("A")

write("".join(preord))
write("\n")
write("".join(inord))
write("\n")
write("".join(postord))