# SneepHackChallengeFA23

**App Name :** Sneep

**App Tagline:** Track insights and log your dreams and sleeps in a whimsical and relaxing app! 

**Link(s) to any other public GitHub repo(s) of your app:**
BACKEND - https://github.com/sophiaawang/hackchallengefa23

**Some screenshots of your app:**

home

![image](https://github.com/vstarzf/SneepHackChallengeFA23/assets/63558657/b43b4b80-1f40-4274-b93b-8c320f211305)

sleep history

![image](https://github.com/vstarzf/SneepHackChallengeFA23/assets/63558657/fa951c37-b088-45fd-a402-afd95d4284e3)

sleep log (reached by + floating action button or clicking on a sleep)

![image](https://github.com/vstarzf/SneepHackChallengeFA23/assets/63558657/06b61ba8-c000-42a3-89d3-8edbd319748b)

statistics

![image](https://github.com/vstarzf/SneepHackChallengeFA23/assets/63558657/84907107-197a-4828-90a2-781a9ecdfccf)


**A short description of your app:**

Sneep is a sleep (and dream) tracking app that allows a user to view their past logged sleeps in a scrollable list, add new sleeps by clicking a '+' button and adding information such as the number of hours slept, describing the dream they had, and choosing a rating to describe how well rested they feel, and using whimsical graphics that change based on user input (displaying a sad moon when the user gets little hours of sleep, but a happy moon when they get many). The user can also tap on a previously entered sleep to get the edit screen pre-populated with information that they can then change. Additionally, Sneep uses numerical optimization that analyzes how the number of hours slept corresponds to the rating score to analyse what number of hours is expected to give the user the best amount of sleep. Sneep also uses natural language processing to analyze the words found in the 'dreams' text-- excluding common words like and, the, etc. -- and find what words are found most often, so a user knows what themes or messages come up the most in their dreams. This data science backend is found on the 'Statistics' page. 

**A list of how your app addresses each of the requirements:**

_Android requirements:_

A completed RecyclerView with a custom adapter: 

The recyclerView in SleepHistoryFragment with corresponding adapter SleepAdapter, layout sleep_item.xml, and data class Sleep. 

Integration with an API created by backend with use of OkHTTP and Moshi: 

Connects to Backend's API in SleepHistoryFragment and SleepLogFragment to get the list of sleeps, get the statistics infoarmation, post new sleeps and dreams, and remove sleeps. 

Use of two 3rd party libraries (we’ve showcased a little bit of Firebase, showcased Glide, or feel free to explore: here are others):

Uses OkHTTP to send network requests and LoggingInterceptor in SleepHistory to print HTTP log messages. 

At the minimum, three fully functional fragments with some system of navigation:

We have four fragments: HomeFragment, SleepHistoryFragment, SleepLogFragment, and DreamFragment, that use a bottom navigation bar to navigate between them (except for SleepLogFragment, which connects to SleepHistoryFragment using a floating action button or an item click). 

_Backend Requirements:_

At least 4 routes (1 must be GET, 1 must be POST, 1 must be DELETE): 

Has 7 routes- GET/api/sleeps (to get list of sleeps), POST/api/sleeps (adds a new sleep), POST /api/sleeps/id/ (edits existing sleep), DELETE /api/sleeps/sleep_id/ (deletes a specific sleep), POST /api/dreams/id/ (Adds a new dream to a corresponding sleep), GET /api/dreams/id/ (Gets a dream), GET /api/dreams/common-words/ (returns the most common words found in the dreams), GET /api/sleeps/best-hours-slept/ (returns most best number of hours to sleep)

At least 2 tables in database with a relationship between them: 

'Sleeps' table and 'Dreams' table with a 1-to-1 relationship (each entry in Sleep has a corresponding entry in dream, and vice-versa) 

API specification explaining each implemented route: 

https://docs.google.com/document/d/1G26wvJeF_tVZh7fBeuH8WhrrWmNRx0iRZ_2rkmcYEBQ/edit?usp=sharing


Anything else you want your grader to know: N/A
