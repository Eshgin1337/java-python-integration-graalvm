import math

class Segment:
    def __init__(self, p1, p2):
        if p1.getX() == p2.getX() and p1.getY() == p2.getY():
            raise ValueError("Points must differ!")
        self.p1 = p1
        self.p2 = p2

    def getP1(self):
        return self.p1

    def getP2(self):
        return self.p2

    def length(self):
        return math.sqrt((self.p1.getX() - self.p2.getX()) ** 2 + (self.p1.getY() - self.p2.getY()) ** 2)

    def toString(self):
        return f"({self.p1}, {self.p2})"
