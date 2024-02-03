
# DishBook: A Food Recipe Android Application

DishBook is an app that makes making food easier and gives you full information about the selected meal including instructions on how to make that meal provided by a video. It includes a Home, Favorites, and Categories pages.

**On Home Screen**

- There is a Random meal Card View that changes every time the user opens the app.

- Also, it includes the most popular meals. The Horizontal List on which the user can swipe and select any of the popular meals. 

- It also includes a Category Card View which displays all the categories available for different food items.

**On Favorites Screen**

- This screen allows the user to select the favorite dishes whichever the user likes.

- Also, it includes swipe-to-delete functionality by which the user can delete the item in favorites just by swiping left or right, which displays a Bottom popup saying "Meal Deleted" using the Snackbar.

**Meals Screen**

- Whenever any user touches on the meal displayed, this screen opens which includes the Image of the meal along with its Recipe with complete instructions.

- This screen also has the favorite button by which a dish can be marked as Favorites. Also, it has a YouTube link hyperlinked by the YouTube icon displayed in this screen.

**Categories Screen**

- In this screen, the user can select any of the categories and look for the dish he/she likes from the selected category.



## Screenshots

- **Screens for which Screenshots are taken:**

    - Splash Screen at the start of the Application.

    - Home Screen

  - Search Screen


  - Meal Information Screen


  - Meals according to chosen category


  - Favorites Screen (without Favorites)


  - Meal Saved in Favorites Screen using Room DB


  - List of all Favorites


  - List of Remaining Favorites in the Favorites Screen after deleting Favorites item using Swipe Left or Right.


  - All Categories Screen

- **All Screenshots:** https://drive.google.com/drive/folders/1f_9At9FoX6-hlCl7EWBeDoZd98xio7N6?usp=sharing
  
![1](https://github.com/sidharth-085/DishBook/assets/130606629/4880fdb1-b95b-4907-bac2-c103d3ee8621)
<br> <br>
![2](https://github.com/sidharth-085/DishBook/assets/130606629/c9c74f13-87c9-40e9-9adf-d3fc3ee068b9)
<br> <br>
![3](https://github.com/sidharth-085/DishBook/assets/130606629/2c96ac1b-02a2-429f-aabe-7f4be7d87881)
<br> <br>
![4](https://github.com/sidharth-085/DishBook/assets/130606629/290730c4-c805-47cb-9b4e-2af606907167)
<br> <br>


## 🔗 Links

**APK:** https://drive.google.com/file/d/1mpWaBt0SrObI0ANvurzWCdYWbffKH6i9/view?usp=sharing

**API Used:** https://www.themealdb.com/api.php


## Tech Stack

- **Navigation component** : One activity contains multiple fragments instead of creating multiple activities.

- **Retrofit** : Making HTTP connection with the REST API and converting meal JSON file to Kotlin/Java object.

- **Room** : Save meals in the local database.

- **MVVM & LiveData** : Separate logic code from views and save the state in case the screen configuration changes.

- **Coroutines** : Do some code in the background.

- **View Binding** : Instead of inflating views manually view binding will take care of that.

- **Glide** : Catch images and load them in imageView.

