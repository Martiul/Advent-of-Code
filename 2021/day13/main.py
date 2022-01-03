def print_grid(grid, y_bound, x_bound):
    for y in range(y_bound):
        for x in range(x_bound):
            if grid[y][x]:
                print("X", end='')
            else:
                print(".", end='')
        print()

def get_input(file_name):
    grid = [[False for x in range(1500)] for y in range(1500)]
    folds = []
    with open(file_name, "r", encoding = "UTF-8") as data:
        for line in data:
            line = line.strip()
            if line.find("fold") >= 0:
                axis, val = line.split("=")
                axis = axis[-1]
                folds.append([axis, int(val)])
            elif line != "":
                x, y = line.split(",")
                grid[int(y)][int(x)] = True
    return grid, folds

def fold_grid(grid, y_bound, x_bound, axis):
    for y in range(y_bound):
        for x in range(x_bound):
            if axis == 'y':
                mirror_y = y_bound + (y_bound - y)
                if mirror_y < len(grid) and grid[mirror_y][x]:
                    grid[y][x] = True
            else:
                mirror_x = x_bound + (x_bound - x)
                if mirror_x < len(grid[0]) and grid[y][mirror_x]:
                    grid[y][x] = True

def count_dots(grid, y_bound, x_bound):
    count = 0
    for y in range(y_bound):
        for x in range(x_bound):
            if grid[y][x]:
                count += 1
    return count

def part_one(file_name):
    grid, folds = get_input(file_name)
    y_bound = len(grid)
    x_bound = len(grid[0])

    ret = 0
    for fold in folds:
        axis, val = fold
        if axis == 'y':
            y_bound = val
        else:
            x_bound = val
        fold_grid(grid, y_bound, x_bound, axis)
        ret = count_dots(grid, y_bound, x_bound)
        return ret # part 1
    return ret

def part_two(file_name):
    grid, folds = get_input(file_name)
    y_bound = len(grid)
    x_bound = len(grid[0])
    
    for fold in folds:
        axis, val = fold
        if axis == 'y':
            y_bound = val
        else:
            x_bound = val
        fold_grid(grid, y_bound, x_bound, axis)
    print_grid(grid, y_bound, x_bound)
    
if __name__ == "__main__":
    print(part_one("sample.txt"))
    print(part_one("input.txt"))
    print(part_two("input.txt"))
