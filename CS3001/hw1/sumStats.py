import math, random, sys
from mergeSort import mergeSort

def getMin(num_list):
    min_value = sys.maxsize

    for _, val in enumerate(num_list):
        if val < min_value:
            min_value = val

    return min_value

def getMax(num_list):
    max_value = -sys.maxsize - 1

    for i, val in enumerate(num_list):
        if val > max_value:
            max_value = val

    return max_value

def getMean(num_list):
    current_sum = 0

    for _, val in enumerate(num_list):
        current_sum += val
    
    return current_sum / len(num_list)


def getMedian(num_list):
    sorted_list = mergeSort(num_list)

    if len(sorted_list) % 2 == 0:
        print("Element 1: " + str(sorted_list[len(sorted_list) // 2]))
        print("Element 2: " + str(sorted_list[(len(sorted_list) // 2) + 1]))
        return (sorted_list[len(sorted_list) // 2] + sorted_list[(len(sorted_list) // 2) + 1]) / 2

    return sorted_list[len(sorted_list) // 2]

def getStandardDeviation(num_list):
    num_list_copy = num_list[:]
    mean = getMean(num_list_copy)

    for i, _ in enumerate(num_list_copy):
        num_list_copy[i] = math.pow(num_list_copy[i] - mean, 2)

    mean = getMean(num_list_copy)

    return math.pow(mean, 0.5)

def getPercentile(num_list, percentile):
    if percentile < 0 or percentile > 1:
        return "Incorrect percentile provided"
    
    sorted_list = mergeSort(num_list)
    percentile_index = math.floor(len(sorted_list) * percentile)

    return sorted_list[percentile_index]


def summaryStatistics(num_list):
    # Max value of list
    max_value = getMax(num_list)
    print("Max Value: " + str(max_value))

    # Min value of list
    min_value = getMin(num_list)
    print("Min Value: " + str(min_value))

    # Mean of list
    mean_value = getMean(num_list)
    print("Mean Value: " + str(mean_value))

    # Standard deviation of list
    standard_deviation_value = getStandardDeviation(num_list)
    print("Standard Deviation: " + str(standard_deviation_value))

    # Median of list
    median_value = getMedian(num_list)
    print("Median Value: " + str(median_value))

    # 25th percentile
    percentile_value25 = getPercentile(num_list, 0.25)
    print("25th Percentile: " + str(percentile_value25))

    # 75th percentile
    percentile_value75 = getPercentile(num_list, 0.75)
    print("75th Percentile: " + str(percentile_value75))
    
    print()

    return [max_value, 
            min_value, 
            mean_value,
            standard_deviation_value,
            median_value,
            percentile_value25,
            percentile_value75]

def generateResults():
    # Generate three sets of random data
    listA = [random.randint(0, 9) for _ in range(1000)]
    listB = [random.gauss(5, 3) for _ in range(1000)]
    listC = [math.exp(random.gauss(1, 0.5)) for _ in range(1000)]

    # Complete the following code to calculate and plot summary statistics of 
    ssA = summaryStatistics(listA)
    ssB = summaryStatistics(listB)
    ssC = summaryStatistics(listC)

    return [ssA, ssB, ssC]

if __name__ == "__main__":
    generateResults()