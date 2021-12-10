def parseInput(file_name):
    f = open(file_name, "r")
    points = []
    for l in f:
        cur_points = l.strip().split("->")
        cur_points = [p.split(",") for p in cur_points]
        for p in cur_points:
            p[0] = int(p[0])
            p[1] = int(p[1])
        points.append(cur_points)
    return points

def partOne(file_name):
    points = parseInput(file_name)
    grid = [[0 for i in range(1000)] for j in range(1000)]
    
    for pair_points in points:
        x1, y1 = pair_points[0]
        x2, y2 = pair_points[1]
        if x1 == x2:
            for y in range(min(y1, y2), max(y1, y2) + 1):
                grid[x1][y] += 1
        elif y1 == y2:
            for x in range(min(x1, x2), max(x1, x2) + 1):
                grid[x][y1] += 1
    
    n = 0
    for i in range(1000):
        for j in range(1000):
            if grid[i][j] >= 2:
                n += 1
    return n


def partTwo(file_name):
    points = parseInput(file_name)
    grid = [[0 for i in range(1000)] for j in range(1000)]
    
    for pair_points in points:
        x1, y1 = pair_points[0]
        x2, y2 = pair_points[1]
        if x1 == x2:
            for y in range(min(y1, y2), max(y1, y2) + 1):
                grid[x1][y] += 1
        elif y1 == y2:
            for x in range(min(x1, x2), max(x1, x2) + 1):
                grid[x][y1] += 1
        else:
            x, y = x1, y1
            while x != x2 and y != y2:
                grid[x][y] += 1
                x += int((x2 - x1) / abs(x2 - x1))
                y += int((y2 - y1) / abs(y2 - y1))
            grid[x][y] += 1
    
    n = 0
    for i in range(1000):
        for j in range(1000):
            if grid[i][j] >= 2:
                n += 1
    return n


if __name__ == "__main__":
    print(partOne("sample.txt"))
    print(partOne("input.txt"))
    print(partTwo("sample.txt"))
    print(partTwo("input.txt"))
    # print(partTwo("input.txt"))
