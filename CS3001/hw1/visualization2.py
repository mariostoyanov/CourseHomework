import matplotlib.pyplot as plt
from sumStats import generateResults
from normalize import generateNormalizedData, countOccurences

def VisualB():
    listA, listB, listC = generateNormalizedData()
    freqA, freqB, freqC = countOccurences(listA), countOccurences(listB), countOccurences(listC)

    print(freqA)
    print(freqB)
    print(freqC)

    plt.plot(freqA.keys(), freqA.values(), marker='.', linestyle="-", c="r", label="Rescaled Data 1")
    plt.plot(freqB.keys(), freqB.values(), marker='+', linestyle="-.", c="g", label="Rescaled Data 2")
    plt.plot(freqC.keys(), freqC.values(), marker='x', linestyle=":", c="b", label="Rescaled Data 3")

    plt.legend()
    plt.show()

if __name__=='__main__':
    VisualB()