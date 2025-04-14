def normalize_block(block):
    
    minr, minc = 50, 50
    for r, c in block:
        if r < minr:
            minr = r
        if c < minc:
            minc = c
            
    for i, (r, c) in enumerate(block):
        block[i] = (r - minr, c - minc)
        
    return block

def get_blocks(board, target=0):
    
    D = len(board)
    seen = set()
    blocks = []
    
    def get_block(r, c):
        
        block = []
        stack = [(r, c)]
        
        while len(stack) > 0:
            
            r, c = stack.pop()
            if (r, c) not in seen and board[r][c] == target:
                seen.add((r, c))
                block.append((r, c))
                if r + 1 < D:
                    stack.append((r + 1, c))
                if r > 0:
                    stack.append((r - 1, c))
                if c + 1 < D:
                    stack.append((r, c + 1))
                if c > 0:
                    stack.append((r, c - 1))
        
        return block
        
    for r, row in enumerate(board):
        for c, v in enumerate(row):
            if v == target and (r, c) not in seen:
                
                block = normalize_block(get_block(r, c))
                blocks.append(block)
                
    return blocks

def cmp_blocks(b1, b2):
    b1, b2 = set(b1), set(b2)
    if b1 == b2:
        return True
    for _ in range(3):
        nb = []
        for r, c in b1:
            nb.append((-c, r))
        b1 = set(normalize_block(nb))
        if b1 == b2:
            return True
        
    return False

def solution(game_board, table):
    board_blocks = get_blocks(game_board, 0)
    table_blocks = get_blocks(table, 1)

    filled = 0
    filled_idx = set()
    for block in table_blocks:
        for i, entry in enumerate(board_blocks):
            if i not in filled_idx and cmp_blocks(block, entry):
                filled_idx.add(i)
                filled += len(block)
                break
    
    return filled