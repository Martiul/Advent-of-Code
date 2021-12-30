from functools import reduce

def getInput(file_name):
    f = open(file_name, "r")
    return [int(n) for n in f.readline().strip().split(",")]

def findMedian(numbers):
    n = len(numbers)
    mid = int(n/2)
    numbers = sorted(numbers)
    
    if n % 2 == 1:
        return numbers[mid]
    else:
        return round((numbers[mid] + numbers[mid - 1]) / 2)

def partTwo(file_name):
    positions = getInput(file_name)
    lowest_cost = -1
    lo = min(positions)
    hi = max(positions)
    for m in range(lo, hi+1):
        cost = 0
        for p in positions:
            d = abs(p - m)
            cost += (d*d + d) / 2
        cost = int(cost)
        if lowest_cost < 0:
            lowest_cost = cost
        lowest_cost = min(lowest_cost, cost)
    return int(lowest_cost)

def partOne(file_name):
    positions = getInput(file_name)
    median = findMedian(positions)
    print("Median: ", median)
    sum = 0
    for p in positions:
        sum += abs(p - median)
    return sum

if __name__ == "__main__":
    print(partOne("sample.txt"))
    print(partOne("input.txt"))
    print(partTwo("sample.txt"))
    print(partTwo("input.txt"))
