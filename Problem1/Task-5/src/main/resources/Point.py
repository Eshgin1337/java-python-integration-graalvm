import math

class Point:
    def __init__(self, EPS, r, a, polar=False):
        self.EPS = EPS
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
        if abs(self.x - x) >= self.EPS or abs(self.y - y) >= self.EPS:
            raise ValueError(x, y)

    def getX(self):
        return self.x

    def getY(self):
        return self.y

    def toString(self):
        return f"({self.x}, {self.y})"