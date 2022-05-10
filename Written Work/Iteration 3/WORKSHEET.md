# Iteration 3 Worksheet

## Technical debt

Throughout development, our project has incurred a lot of technical debt. After iteration 1, we had fixed some of the debt we had due to time contstraints. After iteration 2, we are going to be focusing repaying debt that we still have in our project.

### What technical debt has been cleaned up?

[Commit to database correction](b9349e6c721be8eea71d6e2cb6e2ba1d973eb39a)

During iteration 2, we incorrectly implemented a persistent database which left our project in a very incomplete state and lacking meaningful logic. In this iteration, our top priority was to fix the database by implementing the HSQL database and removing the SQLite implementation we used previously. This was a very severe case of *deliberate and prudent* debt and was an aspect of our project we could not leave the way it was.

### What technical debt did you leave?

## Feature/User Story that was cut/re-prioritized?

We had a lot of features that we wanted to tackle for this project. Our idea for a personal management application gave us a lot of creative freedom with what we could include, we ended up with a few too many features for the amount of time we had to work on the project. Below are features that we initially planned but did not end up being able to work on or were unable to finish.

- [Note taking](winter-2022-a02/group-6/ezy#3)
- [Financing & Budgetting](winter-2022-a02/group-6/ezy#11)
- [To-do List](winter-2022-a02/group-6/ezy#2)

## Acceptance Testing

All testing suites and design were created by Abhinav Sood and Dhariya Prajapati.

### Acceptance test/end-to-end

The largest test that we wrote was a system wide test that goes through all features of project and tests for all the edge cases that we anticipated. To avoid any flaky tests, we wrote all the tests to have expect outcomes with no random input/output. Meaning that every time we run the test we can expect the same result.

- [System wide test made with **Espresso**](9741b6c25415f75f7e1b2959ce05047fa356c5ce)

### Acceptance test, untestable

We did not run into any issues or difficulties when developing the tests themselves. Once we were able to get tests running, it revealed everywhere that we needed refactor and improved the quality of our code drastically.

## Velocity/Teamwork

During iterations 1 and 2, we tended to overestimate how much time we needed, this was due to not being sure how much effort and commitment certain features would need, so we tried to give ourselves more time. In this last iteration, while we were focusing much more on refactoring, fixing code, and wrapping up the project for our final release, we were able to estimate time a lot better got a lot more done in the time we had.

- [Iteration 1: Extreme overestimate](winter-2022-a02/group-6/ezy#24)
- [Iteration 2: Overestimate](winter-2022-a02/group-6/ezy#33)
- [Iteration 3: More accurate estimate](winter-2022-a02/group-6/ezy#37)
