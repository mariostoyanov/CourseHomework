#Mario Stoyanov 
#CS3001 HW2

import math
import pandas as pd
import numpy as np
import random as rnd

# visualization
import seaborn as sns
import matplotlib.pyplot as plt
#%matplotlib inline
# machine learning



pd.set_option('display.expand_frame_repr', False)

def euclidianDist (x,y):
    dist = math.sqrt((x-y)**2)
    print(dist)
    return dist;

def part2 ():
    box1x = 2
    box1y = 1
    box2x = 6
    box2y = 3
    box3x = 1
    box3y= 1
    box4x = 3
    box4y = 3

    print('box1: small rect ')
    print(euclidianDist(box1x,box1y))
    print('box2: big rect')
    print(euclidianDist(box2x,box2y))
    print('box3: small square ')
    print(euclidianDist(box3x,box3y))
    print('box4: big square')
    print(euclidianDist(box4x,box4y))


def header(msg):
	print('-' * 50)
	print('\n[ ' + msg + ' ]')


def part3():
    header("Begin Task 3")
    train_df = pd.read_csv('train.csv')
    header("Train DataFrame")
    print(train_df)
    test_df = pd.read_csv('test.csv')
    header("Test DataFrame")
    print(test_df)


    combine = [train_df, test_df]
    combo = pd.concat(combine)
    combo = combo.reset_index()
    header("Combine DataFrame of Train and Test")
    print(combo)

    #Question 7
    header("Q7: Statistical summary of each column")
    print("Train")
    print(train_df.describe()[['Age','SibSp','Parch','Fare']])
    print("Combo")
    print(combo.describe()[['Age','SibSp','Parch','Fare']])

    #Question 8
    header("Q8: categorical features")
    print("Train")
    print(train_df.describe(include=[np.object]))
    print("Combo")
    print(combo.describe(include=[np.object]))

    #Question 9
    header("Q9: Pclass on Survival")
    print(train_df[['Pclass', 'Survived']].groupby(['Pclass'], as_index=False).mean().sort_values(by='Survived', ascending=False))

    #Question 10
    header("Q9: Sex on Survival")
    print(train_df[["Sex", "Survived"]].groupby(['Sex'], as_index=False).mean().sort_values(by='Survived', ascending=False))

    #Question 11
    g = sns.FacetGrid(train_df, col='Survived')
    g.map(plt.hist, 'Age', bins=20)
    #plt.show()

    #Question 12
    grid = sns.FacetGrid(train_df, col='Survived', row='Pclass', height=2.2, aspect=1.6)
    grid.map(plt.hist, 'Age', alpha=.5, bins=20)
    grid.add_legend();
    #plt.show()

    #Question 13: histogram figure to illustrate the correlations of Embarked, Sex, Fare, and Survived
    grid = sns.FacetGrid(train_df, row='Embarked', col='Survived', height=2.2, aspect=1.6)
    grid.map(sns.barplot, 'Sex', 'Fare', alpha=.5, ci=None)
    grid.add_legend()
    #plt.show()

    #Question 14:
    header("Q14: Ticket on Survival")
    print(train_df[['Ticket', 'Survived']].groupby(['Ticket'], as_index=False).mean().sort_values(by='Survived', ascending=False))

    #Question 15:
    header("Q15: How many null values there are in the Cabin features of the combined dataset of training and test dataset")
    print(combo.describe(include=[np.object]))

    #Question 16:
    header("Q16: Change sex to gender")
    combo.rename(columns={'Sex': 'Gender'}, inplace=True)
    combo['Gender'] = combo['Gender'].apply({'male': 0,'female':1}.get)
    print(combo)

    #Question 17: A simple way is to generate random numbers between mean and standard deviation.
    #completing features with missing or null values for ages
    header("Q17: Missing ages")
    meanAge = combo["Age"].mean()
    standardDev = combo['Age'].std(ddof=1)
    def randomInt():
        return rnd.randint(int(standardDev), int(meanAge))

    combo1 = combo.reset_index()
    print(combo)

    combo1.Age = combo1.Age.replace(np.nan,800, regex=True)
    for index, row in combo1.iterrows():
        if row.Age == 800:
            combo1.loc[index, 'Age'] = randomInt()
    print(combo1)

    #Question 18: Fill Embarked nans with most common occurences
    header("Q18: Fill Embarked nans with most common occurences")
    modeEmbar = combo['Embarked'].mode()[0]
    print(modeEmbar)
    combo['Embarked'] = combo['Embarked'].fillna(modeEmbar)

    print(combo.describe(include=[np.object]))

    #Q19: Completing and converting a numeric feature. Please complete the Fare feature for single missing value in test dataset using mode to get the value that occurs most frequently for this feature.
    header("Q19: Fill Fare nans with mode")
    modeFare = combo['Fare'].mode()[0]
    combo['Fare'] = combo['Fare'].fillna(modeFare)

    print(combo.describe()[['Fare']])

    #Q20: Convert the Fare feature to ordinal values based on the FareBand defined follows:
    header("Q20: Convert the Fare feature to ordinal values")

    # meanAge = combo["Age"].mean()
    # standardDev = combo['Age'].std(ddof=1)
    # i
    combo2 = combo.reset_index()
    # print(combo2)

    for index, row in combo2.iterrows():
        if row.Fare <= 7.91:
            combo2.loc[index, 'Fare'] = 0
        elif row.Fare <=14.454:
            combo2.loc[index, 'Fare'] = 1
        elif row.Fare <=31.0:
            combo2.loc[index, 'Fare'] = 2
        else:
            combo2.loc[index, 'Fare'] = 3
    print(combo2)


def main():
    #part2()
    part3()


main()
