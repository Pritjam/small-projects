class LinkedList:
    class LLNode:
        def __init__(self, data = None) -> None:
            self.data = data
            self.prev = None
            self.next = None
        def setNext(self, node):
            self.next = node
        def getNext(self):
            return self.next
        def setPrev(self, node):
            self.prev = node
        def getPrev(self):
            return self.prev
        def setData(self, data):
            self.data = data
        def getData(self):
            return self.data
        
    def __init__(self) -> None:
        self.root = self.LLNode()
        self.root.setNext(self.root)
        self.root.setPrev(self.root)
        self.size = 0
    def __str__(self) -> str:
        ret = "["
        current = self.root
        while not current.getNext() is self.root :
            current = current.getNext()
            ret += str(current.getData()) + ", "
        ret = ret[0:-2]
        ret += "]"
        return ret
    def length(self):
        return self.size
    def append(self, data):
        current = self.root
        while not current.getNext() is self.root :
            current = current.getNext()
        new = self.LLNode(data)
        current.setNext(new)
        new.setPrev(current)
        new.setNext(self.root)
        self.size += 1
    def get(self, index):
        if(index >= self.size or index < 0):
            raise IndexError("Index " + index + " out of bounds for list size " + self.size)
        i = 0
        current = self.root
        while i <= index:
            current = current.getNext()
            i += 1
        return current.getData()
    def pop(self, index):
        if(index >= self.size or index < 0):
            raise IndexError("Index " + str(index) + " out of bounds for list size " + str(self.size))
        i = 0
        current = self.root
        while i <= index:
            current = current.getNext()
            i += 1
        current.getPrev().setNext(current.getNext())
        current.getNext().setPrev(current.getPrev())
        self.size -= 1
        return current.getData()
    def push(self, data, index = 0):
        i = 0
        current = self.root
        while i < index:
            current = current.getNext()
            i += 1
        new = self.LLNode(data)
        new.setNext(current.getNext())
        new.getNext().setPrev(new)
        current.setNext(new)
        new.setPrev(current)
