from LinkedList import *


list = LinkedList()
list.append(1)
for x in range(10):
    list.append(str(x))
print(list)
print(list.get(4))
size = list.length()
list.pop(size - 1)
print(list)
list.push(8, list.length() - 1)
print(list)