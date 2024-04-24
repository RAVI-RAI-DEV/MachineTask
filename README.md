# How Points are Addressed in the Code

#Data Fetching and Rendering

The code fetches data from the API endpoint https://jsonplaceholder.typicode.com/posts using Retrofit.
Data is displayed in a paginated manner in a RecyclerView (PostList composable) using Jetpack Compose.

#Pagination
Pagination is implemented in the PostList composable, where more data is fetched and displayed as the user scrolls through the list.

#Heavy Computation Optimization
Heavy computation is simulated in the PostItem composable by introducing a delay to simulate processing time.
This delay is implemented when the user clicks on a list item, mimicking heavy computation for displaying additional details.

#Callback Memoization
Callback memoization is achieved by using the remember function to store the processing state (isProcessing) of each list item.
This prevents unnecessary re-computation or re-rendering of the list item when the user clicks on it.


## Code Overview

- `MainActivity`: Sets up the ViewModel and composes the main UI using Jetpack Compose.
- `MyApp`: Composable function that defines the main UI structure using Scaffold.
- `PostList`: Composable function that displays a paginated list of posts.
- `PostItem`: Composable function that displays a single post item in the list.
- `ShowDetail`: Activity that shows the detailed view of a selected post.
- `DetailScreen`: Composable function that defines the detailed view UI using Scaffold.

## Usage

1. Clone the repository: `git clone https://github.com/your-username/your-repository.git`
2. Open the project in Android Studio.
3. Run the project on an emulator or physical device.

## Dependencies

- Retrofit: For network operations and API communication.
- LiveData: For observing data changes in the ViewModel.
- Jetpack Compose: For building the UI.
- Material Components: For UI design components.


Feel free to explore the code and make any improvements or modifications as needed. If you have any questions or feedback, please let me know!
