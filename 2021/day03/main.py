def findGammaEpsilon(countOnes, numLines):
    gamma = 0
    epsilon = 0
    for count in countOnes:
        gamma = gamma << 1
        epsilon = epsilon << 1
        if count > numLines/2:
            gamma += 1
        else:
            epsilon += 1
    print("Gamme / Epsilon", gamma, epsilon)
    return gamma, epsilon

def partOne(fileName):
    f = open(fileName, "r")
    numLines = 0
    countOnes = []

    for line in f:
        line = line.strip()
        numLines += 1
        for i in range(0, len(line)):
            if len(countOnes) <= i:
                countOnes.append(0)
            if line[i] == '1':
                countOnes[i] += 1
    print(countOnes, numLines)
    gamma, epsilon = findGammaEpsilon(countOnes, numLines)
    return gamma * epsilon

MOST_COMMON = 1
LEAST_COMMON = 0

def getLifeSupportFactorRatingWithPosition(binaries, criteria, bit_position):
    if len(binaries) == 1:
        return binaries[0]
    hasOne = []
    hasZero = []
    for binary_string in binaries:
        hasOne.append(binary_string) if binary_string[bit_position] == '1' else hasZero.append(binary_string)
    
    more_common = hasOne if len(hasOne) >= len(hasZero) else hasZero
    less_common = hasZero if len(hasOne) >= len(hasZero) else hasOne

    if criteria == MOST_COMMON:
        return getLifeSupportFactorRatingWithPosition(more_common, criteria, bit_position + 1)
    return getLifeSupportFactorRatingWithPosition(less_common, criteria, bit_position + 1)


def getLifeSupportFactorRating(lines, criteria):
    return getLifeSupportFactorRatingWithPosition(lines, criteria, 0)

def dec_to_binary(binary_string):
    val = 0
    for bit in binary_string:
        val = val << 1
        if bit == '1':
            val += 1
    return val

def partTwo(fileName):
    f = open(fileName, "r")
    binaries = []

    for line in f:
        binaries.append(line.strip())
    
    oxygenRating = dec_to_binary(getLifeSupportFactorRating(binaries, MOST_COMMON))
    co2Rating = dec_to_binary(getLifeSupportFactorRating(binaries, LEAST_COMMON))
    return oxygenRating * co2Rating
        
if __name__ == "__main__":
    # print(partOne("sample.txt"))
    # print(partOne("input.txt"))
    print(partTwo("sample.txt"))
    print(partTwo("input.txt"))
