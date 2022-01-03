import sys
import heapq

def print_grid(grid):
    for y in range(len(grid)):
        for x in range(len(grid[0])):
            print(grid[y][x], end='')
        print()

def get_input(file_name):
    d = []
    with open(file_name, "r", encoding = "UTF-8") as data:
        for line in data:
            row = []
            line = line.strip()
            for c in line:
                row.append(int(c))
            d.append(row)
    return d

def get_neighbours(y, x, max_y, max_x):
    re = []
    if y > 0:
        re.append((y - 1, x))
    if x > 0:
        re.append((y, x - 1))
    if y < max_y:
        re.append((y + 1, x))
    if x < max_x:
        re.append((y, x + 1))
    return re

def part_one(file_name):
    grid = get_input(file_name)
    return solve(grid)

def solve(grid):
    max_y, max_x = len(grid) - 1, len(grid[0]) - 1
    min_dist = [[sys.maxsize for col in range(max_x + 1)] for row in range(max_y + 1)]
    visited = [[False for col in range(max_x + 1)] for row in range(max_y + 1)]

    min_dist[0][0] = 0

    pq = [(0,0,0)]

    while pq:
        dist, y, x = heapq.heappop(pq)

        # Visited
        if visited[y][x]:
            continue
        visited[y][x] = True

        neighbours = get_neighbours(y, x, max_y, max_x)
        for (dest_y, dest_x) in neighbours:
            if not visited[dest_y][dest_x]:
                new_dist = dist + grid[dest_y][dest_x]
                min_dist[dest_y][dest_x] = new_dist
                heapq.heappush(pq, (new_dist, dest_y, dest_x))

    # print(min_dist)
    return min_dist[-1][-1]

def next_num(i):
    return max(1, (i+1) % 10)

def expand_grid(grid):
    rows = len(grid)
    cols = len(grid[0])
    for y in range(rows, rows * 5):
        new_row = []
        for x in range(cols):
            new_row.append(next_num(grid[y - rows][x]))
        if y % rows == 0:
            new_row[0] = 0
        grid.append(new_row)

    for y, row in enumerate(grid):
        for x in range(cols, cols * 5):
            row.append(next_num(grid[y][x - cols]))
            if (y % rows == 0 and x % cols == 0):
                row[-1] = 0

def part_two(file_name):
    grid = get_input(file_name)
    expand_grid(grid)
    grid[0][0] = 0
    # print_grid(grid)
    return solve(grid)

if __name__ == "__main__":
    print(part_one("sample.txt"))
    print(part_one("input.txt"))
    print(part_two("sample.txt"))
    print(part_two("input.txt"))
