from sklearn import  linear_model
from sklearn.naive_bayes import GaussianNB
import pandas
import numpy
from sklearn.preprocessing import LabelEncoder
from sklearn.gaussian_process import GaussianProcessRegressor
from sklearn import tree
from sklearn.linear_model import LogisticRegression
from sklearn.svm import SVR
import itertools as IT



names = ['Item_Identifier','Item_Weight','Item_Fat_Content','Item_Visibility','Item_Type','Item_MRP','Outlet_Identifier','Outlet_Establishment_Year','Outlet_Size',
         'Outlet_Location_Type','Outlet_Type','Item_Outlet_Sales']

testnames = ['Item_Identifier','Item_Weight','Item_Fat_Content','Item_Visibility','Item_Type','Item_MRP','Outlet_Identifier','Outlet_Establishment_Year','Outlet_Size',
         'Outlet_Location_Type','Outlet_Type']

df = pandas.read_csv('train_bm.csv',names=names)
test = pandas.read_csv('test_bm.csv',names=testnames)

test_backup = test

test_backup.sort_values(by=['Item_Identifier'])

# for index,row in test_backup.iterrows():
#     print(row["Item_Identifier"],":",row["Item_Weight"],":",row["Item_Fat_Content"],":",row["Item_Visibility"],":",row["Item_Type"],":",row["Item_MRP"],":",row["Outlet_Identifier"],
#           ":",row["Outlet_Establishment_Year"],":",row["Outlet_Size"],":",row["Outlet_Location_Type"],":",row["Outlet_Type"])

df_backup = df

le = LabelEncoder()

itemIdentifier_cat = le.fit_transform(df.Item_Identifier)
itemFatContent_cat = le.fit_transform(df.Item_Fat_Content)
itemType_cat = le.fit_transform(df.Item_Type)
outletIdentifier_cat = le.fit_transform(df.Outlet_Identifier)
outletSize_cat = le.fit_transform(df.Outlet_Size)
outletLocationType = le.fit_transform(df.Outlet_Location_Type)
outletType = le.fit_transform(df.Outlet_Type)

df_backup['itemIdentifier_cat'] = itemIdentifier_cat
df_backup['itemFatContent_cat'] = itemFatContent_cat
df_backup['itemType_cat'] = itemType_cat
df_backup['outletIdentifier_cat'] = outletIdentifier_cat
df_backup['outletSize_cat'] = outletSize_cat
df_backup['outletLocationType'] = outletLocationType
df_backup['outletType'] = outletType

testitemIdentifier_cat = le.fit_transform(test.Item_Identifier)
testitemFatContent_cat = le.fit_transform(test.Item_Fat_Content)
testitemType_cat = le.fit_transform(test.Item_Type)
testoutletIdentifier_cat = le.fit_transform(test.Outlet_Identifier)
testoutletSize_cat = le.fit_transform(test.Outlet_Size)
testoutletLocationType = le.fit_transform(test.Outlet_Location_Type)
testoutletType = le.fit_transform(test.Outlet_Type)


test_backup['testitemIdentifier_cat'] = testitemIdentifier_cat
test_backup['testitemFatContent_cat'] = testitemFatContent_cat
test_backup['testitemType_cat'] = testitemType_cat
test_backup['testoutletIdentifier_cat'] = testoutletIdentifier_cat
test_backup['testoutletSize_cat'] = testoutletSize_cat
test_backup['testoutletLocationType'] = testoutletLocationType
test_backup['testoutletType'] = testoutletType

dummy_fields = ['Item_Identifier','Item_Fat_Content','Item_Type','Outlet_Identifier','Outlet_Size','Outlet_Location_Type','Outlet_Type']

df_backup = df_backup.drop(dummy_fields,axis=1)
df_backup = df_backup.reindex(['itemIdentifier_cat','Item_Weight','itemFatContent_cat','Item_Visibility','itemType_cat','Item_MRP','outletIdentifier_cat','Outlet_Establishment_Year','outletSize_cat',
                               'outletLocationType','outletType','Item_Outlet_Sales'],axis=1)

test_backup = test_backup.drop(dummy_fields,axis=1)

test_backup = test_backup.reindex(['testitemIdentifier_cat','Item_Weight','testitemFatContent_cat','Item_Visibility','testitemType_cat','Item_MRP',
                                   'testoutletIdentifier_cat','Outlet_Establishment_Year','testoutletSize_cat','testoutletLocationType','testoutletType'],axis=1)

# print(test_backup)

# print(df_backup.head(10))


# model = linear_model.LinearRegression()
# model = linear_model.Lasso()
# model = GaussianNB()
# model = GaussianProcessRegressor()
model = tree.DecisionTreeRegressor()
# model = linear_model.LogisticRegression()
# model = SVR()

X = df_backup.iloc[:,:11]
Y = df_backup.iloc[:,-1]
# Y = Y.astype('float64')

# print(features.shape)
# print(target)

testX = test_backup.iloc[:,:11]

numpy.set_printoptions(threshold=numpy.nan)

# print(testX)
model.fit(X,Y)
# print(model.score(X,Y))
output = model.predict(testX)

for prediction in output:
    print(prediction)



