import math

class Segment:
    def __init__(self, p1, p2):
        if p1[0] == p2[0] and p1[1] == p2[1]:
            raise ValueError("Points must differ!")
        self.p1 = p1
        self.p2 = p2

    def getP1(self):
        return self.p1

    def getP2(self):
        return self.p2

    def length(self):
        return math.sqrt((self.p1[0] - self.p2[0]) ** 2 + (self.p1[1] - self.p2[1]) ** 2)

    def toString(self):
        return f"({self.p1}, {self.p2})"