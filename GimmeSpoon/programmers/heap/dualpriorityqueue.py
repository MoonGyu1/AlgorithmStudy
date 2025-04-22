class Node:
    def __init__(self, p):
        self.value = None
        self.right = None
        self.left = None
        self.parent = p       
        
    def goup(self):
        if self.parent and self.parent.value < self.value:
            temp = self.value
            self.value = self.parent.value
            self.parent.value = temp
            self.parent.goup()
            
    def godown(self):
        if self.left and self.left.value < self.value:
            temp = self.value
            self.value = self.left.value
            self.left.value = temp
            self.left.godown()
        elif self.right and self.right.value < self.value:
            temp = self.value
            self.value = self.right.value
            self.right.value = temp
            self.right.godown()
            
    
            
class Heap:
    
    def __init__(self):
        self.data = []
        self.max = -1
        self.depth = 0
    
    def parent(self, i):
        if i==0:
            return 0
        else:
            return int((i-1)/2)
    
    def children(self, i):
        return ((i+1)*2, (i+1)*2+1)
    
    def swap(self, i, j):
        temp = self.data[i]
        self.data[i] = self.data[j]
        self.data[j] = temp
        
    def findMin(self):
        if self.max < 0:
            return -1
    
        if self.max == 2**self.depth - 2:
            start = 2**(self.depth-1)-1
        else:
            start = self.parent(self.max)+1
        minimum = self.data[start]
        minindex = start
    
        for i in range(start, self.max+1):
            if self.data[i] < minimum:
                minindex = i
                minimum = self.data[i]
        
        return minindex
        
    def goup(self, i):
        while i > 0:
            parent = self.parent(i)
            if self.data[i] > self.data[parent]:
                self.swap(i, parent)
                i = parent
            else:
                break
    
    def godown(self, i):
        while True:
            children = self.children(i)
            if children[0] <= self.max and self.data[i] < self.data[children[0]]:
                self.swap(i, children[0])
                i = children[0]
            elif children[1] <= self.max and self.data[i] < self.data[children[1]]:
                self.swap(i, children[1])
                i = children[1]
            else:
                break
    
    def insert(self, num):
        self.max += 1
        if self.max == 0:
            self.depth = 1
        elif self.max+1 == 2**self.depth:
            self.depth += 1
        self.data.append(num)
        self.goup(self.max)
        
    def deleteMax(self):
        if not self.max < 0:
            self.data[0] = self.data[self.max]
            del self.data[self.max]
            self.max -= 1
            if self.max <= 2**(self.depth-1):
                self.depth -= 1
            self.godown(0)
            
    def deleteMin(self):
        if not self.max < 0:
            i = self.findMin()
            self.data[i] = self.data[self.max]
            del self.data[self.max]
            self.max -= 1
            if self.max <= 2**(self.depth-1):
                self.depth -= 1
            if i != self.max + 1:
                self.goup(i)
        
    def maxmin(self):
        if self.max >= 0:
            return (self.data[0], self.data[self.findMin()])
        else:
            return (0,0)

def solution(operations):
    answer = [0, 0]
    heap = Heap()
    
    for operation in operations:
        (operator, operand) = operation.split(' ')
        if operator == "I":
            heap.insert(int(operand))
        if operator == "D":
            if int(operand) == 1:
                heap.deleteMax()
            else:
                heap.deleteMin()
                
    print(heap.data)
    (answer[0], answer[1]) = heap.maxmin()
    
    return answer