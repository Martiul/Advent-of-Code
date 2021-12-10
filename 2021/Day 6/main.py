def getInput(file_name):
    f = open(file_name, "r")
    l = [int(n) for n in f.readline().strip().split(",")]
    return l

# Bottom up DP
def partTwo(file_name, days):
    grid = [[0 for timer in range(10)] for days in range(days+1)]
    # grid[t][d] = number of fish when a fish with timer i has j days left
    # grid[t][d] = 1 if t > d
    # grid[t][d] = grid[7, d-t] + grid[9, d-t]
    for days in range(0, len(grid)):
        for timer in range(0, len(grid[0])):
            val = 0
            if timer == 0:
                val = 0 # invalid
            elif timer > days:
                val = 1
            else:
                val = grid[days-timer][7] + grid[days-timer][9]
            grid[days][timer] = val
    
    sum = 0
    fish = getInput(file_name)
    for f in fish:
        sum += grid[days][f+1]
    return sum

def partOne(file_name, iters):
    fish = getInput(file_name)
    i = 0
    while i < iters:
        num_existing_fish = len(fish)
        for idx in range(0, num_existing_fish):
            fish[idx] -= 1
            if fish[idx] < 0:
                fish[idx] = 6
                fish.append(8)
        i += 1
    return len(fish)

if __name__ == "__main__":
    print(partOne("sample.txt", 80))
    print(partOne("input.txt", 80))
    print(partTwo("sample.txt", 80))
    print(partTwo("input.txt", 80))
    print(partTwo("input.txt", 256))

