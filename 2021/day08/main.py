# 0: 6
# 1: 2 U
# 2: 5
# 3: 5
# 4: 4 U
# 5: 5
# 6: 6
# 7: 3 U
# 8: 7 U
# 9: 6

# partOne: Count number of challenges with len 2,3,4,7
def partOne(file_name):
    _, challenges = getInput(file_name)
    n = 0
    for c in challenges:
        n += len(list(filter(lambda s : len(s) in [2, 3, 4, 7], c)))
    return n

def findKeyOfMap(m, value):
    for k,v in m.items():
        if v == value:
            return k
    return 0

def partTwo(file_name):
    all_samples, all_challenges = getInput(file_name)
    sum = 0
    for i in range(0, len(all_samples)):
        number_to_set = {}
        samples = all_samples[i]
        fives = []
        sixes = []
        challenges = all_challenges[i]
        for sample in samples:
            s = set(sample)
            l = len(s)
            if l == 2:
                number_to_set[1] = s
            elif l == 3:
                number_to_set[7] = s
            elif l == 4:
                number_to_set[4] = s
            elif l == 7:
                number_to_set[8] = s
            elif l == 5:
                fives.append(s)
            elif l == 6:
                sixes.append(s)
        
        seg_e_f = number_to_set[4] - number_to_set[1]
        seg_f = fives[0].intersection(fives[1]).intersection(fives[2]).intersection(seg_e_f)
        seg_e = seg_e_f - seg_f
  
        number_to_set[0] = number_to_set[8] - seg_f
        for n in fives:
            intersect_4 = n.intersection(number_to_set[4])
            if len(intersect_4) == 2:
                number_to_set[2] = n
            elif len(seg_e.intersection(intersect_4)) == 1:
                number_to_set[5] = n
            else:
                number_to_set[3] = n
        for n in sixes:
            intersect_4 = n.intersection(number_to_set[4])
            if n != number_to_set[0]:
                if len(intersect_4) == 4:
                    number_to_set[9] = n
                else:
                    number_to_set[6] = n

        challengeValue = 0
        for c in challenges:
            challengeValue *= 10
            challengeValue += findKeyOfMap(number_to_set, set(c))
            # print(set(c))
            # print("Translated: ", findKeyOfMap(number_to_set, set(c)))
        sum += challengeValue

    return sum

def getInput(file_name):
    f = open(file_name, "r")
    samples, challenges = [], []
    for line in f:
        sample, challenge = line.split("|")
        samples.append(sample.split())
        challenges.append(challenge.split())
        
    return samples, challenges

if __name__ == "__main__":
    print(partOne("sample.txt"))
    print(partOne("input.txt"))
    print(partTwo("basic.txt"))
    print(partTwo("sample.txt"))
    print(partTwo("input.txt"))

