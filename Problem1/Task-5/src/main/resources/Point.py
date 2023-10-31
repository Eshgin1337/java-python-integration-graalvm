import math

class Point:
    EPS = 0.0001

    def __init__(self, r, a, polar=False):
        x = 0
        y = 0
        if(polar == "False" or polar==False):
            polar = False
        else:
            polar = True
        if polar:
            x = r * math.cos(a)
            y = r * math.sin(a)
        else:
            x = r
            y = a
        self.x = int(x)
        self.y = int(y)
        if abs(self.x - x) >= Point.EPS or abs(self.y - y) >= Point.EPS:
            raise ValueError(x, y)

    def getX(self):
        return self.x

    def getY(self):
        return self.y

    def toString(self):
        return f"({self.x}, {self.y})"