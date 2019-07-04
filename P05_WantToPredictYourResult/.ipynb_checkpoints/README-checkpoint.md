# Hack-In P05- Want to Predict Your Result?

Hi Everyone! I congratulate you for starting the process of data science and machine learning. This is going to be very exciting and work oriented experience. First of all please signup on gitter with your github id and share it to me. I will add you all to that workspace. 

No matter if you are starting from scratch or know a lot of deal regarding this field. It’s gonna help you anyway. The Project repository has a folder named P05-WantToPredictYourResult, this is our repository and you have to do your all of the work inside the participant folder after creating a folder of your name in it. Easy peasy!

Ex: If i am XYZ i go in the participant folder and create a folder XYZ in it and work in it. No where else.

**Work** : I update tasks daily and you have to work through them. There will be worksheets which you need to complete in order to successfully complete this project. We will clear doubts on regular basis but since we won’t be able to answer all of them, I advise everyone to first thoroughly search for your doubts and then let me answer them.


**This projects aim to make machine learning more accessible and relatable to real life events happening around you. It aims to lay a strong foundation and reason to work in this field. Dedicate your time over it diligently and you will be able to see the results for yourself.**

**Layout**: We have a rough dataset of pdfs for result. We would be having three pdfs for each semester and we will use them to do the overall analytics. Ex: Using Semester-1, Semester-2 marks to predict marks of Semester-3.

**Execution**: First we clean the data and then we find some general insights in it. We create some hypthesis to test over it and then we start the actual machine learning. It's a multi-step process with each step equally necessary for the next one.

This projects run in the following parts:

* **Data Cleaning and Arranging into csv Files** (Difficulty Medium - Can be Skipped if first time)
* **Data Analysis using Numpy and Pandas** (Difficulty - Easy to Medium - Cannot be Skipped)
* **Data Visualization using Matplotlib and Seaborn** (Difficulty - Easy to Medium - Cannot be Skipped)
* **Machine Learning using Regression and Random Forest** (Difficulty - Medium - Shouldn’t be Skipped)



## **Important Points:**

**Forking and How Updating of Repository Works:** Check out this [Link](https://gist.github.com/Chaser324/ce0505fbed06b947d962). Repository will be updated many times a day hence you need to keep it updated before you make any pull request.

### **How to make a pull request?**

* Make sure you have git bash installed.
* Fork the Repository to your github account.
* Use the command: ```git clone <your_repo_link>``` to clone it your laptop/desktop.
* Now where-ever you have saved the github repo : Move one directory inside.
* The folder that will be on your computer will have name GDTC_Hack-In.

**You have to be inside this folder for next steps.**

* Open git bash and type : 
```  
git remote add upstream https://github.com/iamchiragsharma/GDTC_Hack-In.git
git fetch upstream
git merge upstream/master master
git pull --rebase
```



* **Create a Folder inside GDTC_Hack-In/P05_WantToPredictYourResult/Participants/"Your Name"/"Your Files Here** (Paste the files there or if you are doing it second time make sure files are in that folder only.)

Again in the git bash type: 
```
git add .
git commit -m “Added Files xyz” 
git push origin master
```

*Go on and check you Github for the files that are uploaded. Click on the pull request tab and then click the pull request (Green) button to make a pull request. Again check in the pull request that you haven’t changed files other than the files in the folder inside participants/"your name".*

**Great! You are on the last step!** 

After Clicking the green button make sure it looks like [this](https://drive.google.com/open?id=1rOfVmCLRJHx-daA17u32the5GZgbMD7Q). On right side your repository (head) and base is iamchiragsharma/GDCT_Hack-in


### **Resources:**

* Statistics
    * [Repository for codes on Statistical Tools](https://github.com/iamchiragsharma/Statistica) If you like then star it and if you can make it better please fork and start working.
    
    
    * **After First Half**
        * [Intro to Variance, Covariance and Correlation](https://www.surveygizmo.com/resources/blog/variance-covariance-correlation/)
        * [Correlation & Covariance](https://www.countbayesie.com/blog/2015/2/21/variance-co-variance-and-correlation)
        * [Correlation Matrix & Covariance Matrix](https://towardsdatascience.com/let-us-understand-the-correlation-matrix-and-covariance-matrix-d42e6b643c22)
        * [Ben Lambert Correlation & Covariance](https://www.youtube.com/watch?v=KDw3hC2YNFc)

* Pandas
    * [Pandas CheatSheet](https://www.dataquest.io/blog/pandas-cheat-sheet/)
    * [Pandas Basic CheatSheet PDF](https://assets.datacamp.com/blog_assets/PandasPythonForDataScience.pdf)
    * [Pandas CheatSheet PDF](https://pandas.pydata.org/Pandas_Cheat_Sheet.pdf)
    * [Pandas DataFrame Notes](https://www.webpages.uidaho.edu/~stevel/504/Pandas%20DataFrame%20Notes.pdf)

* Matplotlib
    * [Real Python Basics](https://realpython.com/python-matplotlib-guide/)
    * [Matplotlib Gallery](https://matplotlib.org/gallery.html)
    * [Codes for Curves](https://pythonspot.com/matplotlib/)
    * [Edureka Visualizations](https://www.youtube.com/watch?v=yZTBMMdPOww)
    * [Corey Schafer - Creating Plots](https://www.youtube.com/watch?v=UO98lJQ3QGI)

* Machine Learning:
    * [Validation and Cross Validation](https://dziganto.github.io/cross-validation/data%20science/machine%20learning/model%20tuning/python/Model-Tuning-with-Validation-and-Cross-Validation/)
    * [KNN with K-Fold & GridSearch](https://towardsdatascience.com/building-a-k-nearest-neighbors-k-nn-model-with-scikit-learn-51209555453a)
    * [Linear Regression](https://www.ritchieng.com/machine-learning-evaluate-linear-regression-model/)
    * [Random Forest and OverFitting](https://mljar.com/blog/random-forest-overfitting/)
    * [Lasso and Ridge with Regularization](https://towardsdatascience.com/regularization-in-machine-learning-76441ddcf99a)
