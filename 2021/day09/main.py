from collections import deque

INVALID_BASIN = -1

def getInput(file_name):
    f = open(file_name, "r")
    grid = []
    for line in f:
        l = [int(c) for c in line.strip()]
        grid.append(l)
    return grid

def isLowPoint(grid, y, x):
    for dy in (-1, 0, 1):
        for dx in (-1, 0, 1):
            if abs(dy + dx) == 1 and 0 <= y + dy < len(grid) and 0 <= x + dx < len(grid[0]) and grid[y+dy][x+dx] <= grid[y][x]:
                return False
    return True

def canExtendBasin(grid, y, x, low_point):
    return 0 <= y < len(grid) and 0 <= x < len(grid[0]) and grid[y][x] > low_point and grid[y][x] < 9

def findBasinSize(grid, start_y, start_x):
    low_point = grid[start_y][start_x]
    size = 1
    visited = [[False for i in range(0, len(grid[0]))] for j in range(0, len(grid))]

    q = deque()
    q.append((start_y - 1, start_x))
    q.append((start_y + 1, start_x))
    q.append((start_y, start_x - 1))
    q.append((start_y, start_x + 1))
    visited[start_y][start_x] = True

    while len(q) != 0:
        y, x = q.pop()
        
        if canExtendBasin(grid, y, x, low_point) and not visited[y][x]:
            size += 1
            q.append((y-1, x))
            q.append((y+1, x))
            q.append((y, x-1))
            q.append((y, x+1))
            visited[y][x] = True    
    # +1 for low point
    return size

def partOne(file_name):
    grid = getInput(file_name)
    total_risk = 0
    
    for y in range(0, len(grid)):
        for x in range(0, len(grid[0])):
            if isLowPoint(grid, y, x):      
                total_risk += grid[y][x] + 1
    return total_risk

def partTwo(file_name):
    grid = getInput(file_name)
    basinSizes = []
    for y in range(0, len(grid)):
        for x in range(0, len(grid[0])):
            if isLowPoint(grid, y, x):
                basinSizes.append(findBasinSize(grid, y, x))
    basinSizes.sort()
    return basinSizes[-1] * basinSizes[-2] * basinSizes[-3]



if __name__ == "__main__":
    print(partOne("sample.txt"))
    print(partOne("input.txt"))
    print(partTwo("sample.txt"))
    print(partTwo("input.txt"))

