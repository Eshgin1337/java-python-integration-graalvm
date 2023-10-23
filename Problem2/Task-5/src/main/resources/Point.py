import math

class Point:
    EPS = 0.0001
    def __init__(self, r, a, polar=False):
        x = r * math.cos(a) if polar else r
        y = r * math.sin(a) if polar else a
        self.x = int(x)
        self.y = int(y)
        if abs(self.x - x) >= Point.EPS or abs(self.y - y) >= Point.EPS:
            raise ValueError("Likely not integers!")
    def getX(self):
        return self.x

    def getY(self):
        return self.y

    def toString(self):
        return f"({self.x}, {self.y})"

