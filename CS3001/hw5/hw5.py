#  Name:       Mario Stoyanov
#  Class:      CS 3001
#  Assignment: HW5 

from sklearn import tree
from sklearn.model_selection import KFold
from sklearn.neighbors import KNeighborsClassifier
import pandas as pd
import pickle

#### Iris Data #####
irisData = pd.read_csv("iris.txt")
features = list(irisData.columns[0:4])
label = list(irisData.columns[4:5])
data = irisData[features]
target = irisData[label]

##### Data splitting into K folds for cross validation #####
kf = KFold(n_splits=5, random_state=None, shuffle=True)

irisDataFrames = []
irisDataFrameResults = []
testDataFrames = []
testDataFrameResults = []

for train_index, test_index in kf.split(data):

    irisDataFrames.append(data.loc[list(train_index)])
    irisDataFrameResults.append(target.loc[train_index])
    testDataFrames.append(data.loc[list(test_index)])
    testDataFrameResults.append(target.loc[list(test_index)])

decisionTreeAccuracy = 0
kNNAccuracy = 0
for i in range(5):
    ##### Performing accuracy measurment and decision tree predicion #####
    model = tree.DecisionTreeClassifier()
    model.fit(irisDataFrames[i], irisDataFrameResults[i])

    with open('decisionTree.txt', 'wb') as f:
        pickle.dump(model, f)
    with open('decisionTree.txt', 'rb') as f:
        loadedModel = pickle.load(f)
    generatedResults = loadedModel.predict(testDataFrames[i])

    index = 0
    correct = 0
    for k, row in testDataFrameResults[i].iterrows():
        if generatedResults[index] == row['class']:
            correct += 1
        index += 1
    print("The number of correct values of decision tree model on iteration " + str(i) + " was: " + str(correct))
    print("The accuracy of decision tree model on iteration " + str(i) + " was: " + str(100 * correct / len(generatedResults)) + "%")
    decisionTreeAccuracy += 100 * correct / len(generatedResults)

    ##### kNN Classifier Trainer #####
    neigh = KNeighborsClassifier()
    neigh.fit(irisDataFrames[i], irisDataFrameResults[i]['class'])

    with open('classifier.txt', 'wb') as f:
        pickle.dump(neigh, f)
    with open('classifier.txt', 'rb') as f:
        loadedModel = pickle.load(f)
    generatedResults = loadedModel.predict(testDataFrames[i])

    index = 0
    correct = 0
    for k, row in testDataFrameResults[i].iterrows():
        if generatedResults[index] == row['class']:
            correct += 1
        index += 1
    print("The number of correct values for kNN on iteration " + str(i) + " was: " + str(correct))
    print("The accuracy of kNN on iteration " + str(i) + " was: " + str(100 * correct / len(generatedResults)) + "%")
    kNNAccuracy += 100 * correct / len(generatedResults)

decisionTreeAccuracy /= 5
print("The overall accuracy of the decision tree model was: " + str(decisionTreeAccuracy) + "%")

kNNAccuracy /= 5
print("The overall accuracy of the kNN classifier was: " + str(kNNAccuracy) + "%")