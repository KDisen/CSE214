"""
Keven Disen
111433335
CSE 337
Homework 1
9/28/2020
"""


# Problem 1
def isValid(s):
    inStr = []
    for i in range(len(s)):  # Turns input to a list
        inStr.append(s[i])
    for val in inStr:
        for key in dictAlpha:
            if ord(val) == ord(key):  # checks if character inputted is same as a letter in the dictionary
                dictAlpha[key] += 1  # adds one to the value of the key matched

    counter = []
    for key in dictAlpha:
        if dictAlpha[key] > 0:
            counter.append(dictAlpha[key])  # adds the frequency of the letters to counter list
    # print(counter)
    for i in range(len(counter)):
        if max(counter) == counter[i]:
            counter[i] = counter[i] - 1  # finds the max and subtracts it by 1
            break
    flag = False
    for i in range(len(counter)):
        if flag: break
        # searches list, if there's another max and frequencies are not the same then flag False
        for j in range(i + 1, len(counter)):
            if counter[i] != counter[j]:
                flag = True

    if flag:
        print("NO")  # if flag is still True, then it is Invalid
    else:
        print("YES")


alphabet = 'abcdefghijklmnopqrstuvwxyz'
alpha = []
for i in range(len(alphabet)):  # turns alphabet into a list
    alpha.append(alphabet[i])
dictAlpha = dict.fromkeys(alpha, 0)  # converts list to a dictionary, with value zeros


# Problem 2

def isBalanced(brack):
    b = []  # list to hold open brackets
    fun = False
    for k in brack:
        if k in bracketDict.keys():  # Follows stack method, adds open brackets to stack
            b.insert(0, k)
            # print(b)
        elif k in bracketDict.values():
            if len(b) == 0:  # if b is empty, then input was empty
                fun = False

            if b[0] == getKey(k):  # gets Key based on value
                b.pop(0)  # pops open bracket if closed bracket found
                # print(b.pop(0))
                fun = True
            else:
                fun = False

    if len(b) == 0:  # The stack should be empty
        fun = True
    else:
        fun = False

    if fun:  # If fun is true print that brackets or valid
        print("Yes")
    else:
        print("No")


# Return key when given its value
def getKey(n):
    for key, value in bracketDict.items():
        if n == value:
            return key
    return False


# Create dictionary based on open and closed bracket
bracketDict = {'{': '}', '[': ']', '(': ')'}



# Problem 3
# Part 1
class Node:

    def __init__(self, n, left=None, right=None):
        self.n = n
        self.left = left
        self.right = right

    # Part 2
    # traverse in order through binary tree
    def inOrder(self):
        global inTree  # calls global list
        if self.n is None:  # if root is empty tree is empty
            return inTree
        if self.left is not None:
            Node.inOrder(self.left)
        inTree.append(self.n)
        if self.right is not None:
            Node.inOrder(self.right)
        return inTree

    # traverse pre order through binary tree
    def preOrder(self):
        global preTree
        if self.n is None:
            return preTree
        preTree.append(self.n)
        if self.left is not None:
            Node.preOrder(self.left)
        if self.right is not None:
            Node.preOrder(self.right)
        return preTree

    # traverse post order through binary tree
    def postOrder(self):
        global postTree
        if self.n is None:
            return postTree
        if self.left is not None:
            Node.postOrder(self.left)
        if self.right is not None:
            Node.postOrder(self.right)
        postTree.append(self.n)
        return postTree

    # Part 3
    # Adds up all of the nodes
    def sumTree(self):
        global total
        if self.n is None:
            return total
        total = self.n + total
        if self.left is not None:
            Node.sumTree(self.left)
        if self.right is not None:
            Node.sumTree(self.right)

        return total


total = 0
inTree = []
preTree = []
postTree = []

