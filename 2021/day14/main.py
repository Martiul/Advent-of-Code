def get_input(file_name):
    polymer, rules = [], {}
    with open(file_name, "r", encoding = "UTF-8") as data:
        polymer = data.readline().strip()
        for line in data:
            line = line.strip()
            if line != "":
                pattern, result = line.split("->")
                rules[pattern.strip()] = result.strip()
    return polymer, rules

def polymer_step2(pair_count, rules, element_count):
    new_pairs = {}
    for k,v in pair_count.items():
        new_element = rules[k]
        np1 = k[0] + new_element
        np2 = new_element + k[1]
        for np in [np1, np2]:
            if np not in new_pairs:
                new_pairs[np] = 0
            new_pairs[np] += v

        if new_element not in element_count:
            element_count[new_element] = 0
        element_count[new_element] += v

    return new_pairs, element_count

def upperchar_to_int(c):
    return ord(c) - ord("A")

def part_two(file_name, steps):
    polymer, rules = get_input(file_name)

    element_count = {}
    for c in polymer:
        if c not in element_count:
            element_count[c] = 0
        element_count[c] += 1

    pair_count = {}
    for i in range(len(polymer) - 1):
        pair = polymer[i:i+2]
        if pair not in pair_count:
            pair_count[pair] = 0
        pair_count[pair] += 1

    for _ in range(steps):
        pair_count, element_count = polymer_step2(pair_count, rules, element_count)
    return max(element_count.values()) - min(element_count.values())

if __name__ == "__main__":
    print(part_two("sample.txt", 10))
    print(part_two("input.txt", 10))
    print(part_two("sample.txt", 40))
    print(part_two("input.txt", 40))
