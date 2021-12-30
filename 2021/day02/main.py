def partOne(fileName):
    f = open(fileName, "r")
    x = 0
    y = 0

    for line in f:
        direction, val = line.strip().split(" ")
        val = int(val)
        if direction == "forward":
            x += val
        elif direction == "up":
            y -= val
        elif direction == "down":
            y += val
    return x*y

def partTwo(fileName):
    f = open(fileName, "r")
    x = 0
    y = 0
    aim = 0

    for line in f:
        direction, val = line.strip().split(" ")
        val = int(val)
        if direction == "forward":
            x += val
            y += val * aim
        elif direction == "up":
            aim -= val
        elif direction == "down":
            aim += val
    return x*y

if __name__ == "__main__":
    print(partOne("input.txt"))
    print(partTwo("sample.txt"))
    print(partTwo("input.txt"))
