import collections, math, random

def normalize(num_list):
        return [int(round(((i - min(num_list)) / (max(num_list) - min(num_list))) * 9)) for i in num_list]

def countOccurences(num_list):
    occurences = {}

    for i in num_list:
        # Add key if the number hasn't been encountered yet. Set initial count to 1.
        if i not in list(occurences.keys()): 
            occurences[i] = 1
            continue
        # Otherwise, increment that value by 1
        occurences[i] += 1

    return collections.OrderedDict(sorted(occurences.items()))

def generateNormalizedData():
    listA = [random.randint(0, 9) for _ in range(1000)]
    listB = [random.gauss(5, 3) for _ in range(1000)]
    listC = [math.exp(random.gauss(1, 0.5)) for _ in range(1000)]

    normalized_listA = normalize(listA)
    normalized_listB = normalize(listB)
    normalized_listC = normalize(listC)

    return normalized_listA, normalized_listB, normalized_listC

if __name__ == "__main__":
    listA, listB, listC = generateNormalizedData()

    print(listA)
    print(countOccurences(listA))
    print(listB)
    print(countOccurences(listB))
    print(listC)
    print(countOccurences(listC))