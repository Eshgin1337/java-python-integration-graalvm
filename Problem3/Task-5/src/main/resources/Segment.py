import math

class Segment:
    def __init__(self, p1x, p1y, p2x, p2y):
        if p1x == p2x and p1y == p2y:
            raise ValueError("Points must differ!")
        self.p1x = p1x
        self.p1y = p1y
        self.p2x = p2x
        self.p2y = p2y

    def length(self):
        return math.sqrt((self.p1x - self.p2x) ** 2 + (self.p1y - self.p2y) ** 2)


class ToPython:
    def __init__(self, p1, p2):
        self.p1 = p1
        self.p2 = p2
    def getP1(self):
        return self.p1

    def getP2(self):
        return self.p2


