
# DishBook: A Food Recipe Android Application

EasyFood is an app that makes making food easier and gives you full information about the selected meal including instructions of how to make that meal provided by a video. It incldues an Home, Favorites and Categories pages.

**On Home Screen**

- There is Random meal Card View which changes everytime the user opens the app.

- Also, it includes most popular meals Horizontal List on which the user can swipe and select any of the popular meals. 

- It also includes a Category Card View which displays all the categories available for different food items.

**On Favorites Screen**

- This screen allows user to select the favorites dishes whichever the user likes.

- Also, it includes swipe to delete functionality by which user can delete the item in favorites just by swiping left or right, which displays a Bottom popup saying "Meal Deleted" using Snackbar.

**Meals Screen**

- Whenever any user touches on the meal displayed, this screen opens which include the Image of the meal along with its Recipe with complete instructions.

- This screen also have the favorite button by which a dish can be marked as Favorites. Also, it has Youtube Link hyperlinked by Youtube Icon displayed in this screen.

**Categories Screen**

- In this screen, user can select any of the category looks for the dish he/she likes from selected category.



## Screenshots

- Splash Screen at the starting to Application.

![SS1](https://github.com/sidharth-085/DishBook/assets/130606629/26871c92-eb05-48f9-a8cc-785001d989d6)

- Home Screen

![SS2](https://github.com/sidharth-085/DishBook/assets/130606629/4e111aaa-3909-45b6-9671-36161cd24ed4)

- Search Screen

![SS3](https://github.com/sidharth-085/DishBook/assets/130606629/7f834dc2-ff28-4369-8081-b292c85674e1)

- Meal Information Screen

![SS4](https://github.com/sidharth-085/DishBook/assets/130606629/b8f9b021-f199-4376-bb6f-cc1a2354847a)

- Meals according to choosen category

![SS5](https://github.com/sidharth-085/DishBook/assets/130606629/f3158912-e01e-49d8-a7af-2553add5b364)

- Favorites Screen (without Favorites)

![SS6](https://github.com/sidharth-085/DishBook/assets/130606629/c7f7fcd1-ede0-4510-8845-798536c9a1b4)

- Meal Saved in Favorites Screen using Room DB

![SS7](https://github.com/sidharth-085/DishBook/assets/130606629/184a01c4-0fc6-4b55-a2e1-3944bc07351c)

- List of all Favorites

![SS8](https://github.com/sidharth-085/DishBook/assets/130606629/b7f40931-16f7-4a3b-893b-c9c69494e9e3)

- List of Remaining Favorites in the Favorites Screen after deleting Favorites item using Swipe Left or Right.

![SS9](https://github.com/sidharth-085/DishBook/assets/130606629/64238946-b839-47d0-938d-18eeb5d5bde7)

- All Categories Screen

![SS11](https://github.com/sidharth-085/DishBook/assets/130606629/1e964608-057b-4742-8392-f2efd77bcae3)








## 🔗 Links

**APK:** https://drive.google.com/file/d/1mpWaBt0SrObI0ANvurzWCdYWbffKH6i9/view?usp=sharing

**API Used:** https://www.themealdb.com/api.php






## Tech Stack

- **Navigation component** : one activity contains multiple fragments instead of creating multiple activites.

- **Retrofit** : making HTTP connection with the rest API and convert meal json file to Kotlin/Java object.

- **Room** : Save meals in local database.

- **MVVM & LiveData** : Saperate logic code from views and save the state in case the screen configuration changes.

- **Coroutines** : do some code in the background.

- **view binding** : instead of inflating views manually view binding will take care of that.

- **Glide** : Catch images and load them in imageView.

