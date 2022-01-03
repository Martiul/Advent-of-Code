def get_input(file_name):
    d = []
    with open(file_name, "r", encoding = "UTF-8") as data:
        for line in data:
            line = line.strip()
            d.append(line)
    return d

def part_one(file_name):
    _ = get_input(file_name)
    return []

def part_two(file_name):
    _ = get_input(file_name)
    return []

if __name__ == "__main__":
    print(part_one("sample.txt"))
    print(part_one("input.txt"))
    print(part_two("sample.txt"))
    print(part_two("input.txt"))
