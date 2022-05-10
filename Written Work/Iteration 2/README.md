# Iteration 2 Worksheet

## Technical Debt

Two instances where our group paid off technical debt created in iteration 1:

- Our group had very disorganized class files and improperly sorted our files into the wrong folders. We have since reorganized everything and paid off our *reckless and dilberate debt*. It is considered as such because we were aware of the debt we were creating, but we focused on getting the final product done.
  - [Commit to reorganizing files](9d83dd5e9f2844ecf89e9692b830d9fe0167365d).
- When our group was working through this iteration we had not implemented any sort of database (dummy or real) that would work properly with inputs from our application. We had to implement a dummy database that was not associated with the rest of our program as we did not have time. This debt was *prudent and deliberate* as we had run out of time but planned to deal with and resolve the issues this would cause during this iteration.
  - [Commit to implementing a database with our GUI layer](db2b85c86a78b9c03419a7d841ed9c1247c335cc)

## SOLID Violation in Group 5
Looking through Group 5's project, we could not find any SOLID violations. Their implementations and design is very well thought out and clear.

## Retrospective

With this iteration, we were able to better communicate and tackle larger problems together as a group. We still struggled with time estimates with the GitLab issues, but with how many large parts of the application were completed during this iteration, we have a much better grasp of how long features and user stories will take to implement. We were also better prepared for any merge issues we encountered in the last stage of the iteration. During this iteration we were unable to implement any tests as our application mostly relies on GUI and user inputs that cannot be tested. We instead did manual tests to make sure all components of our application are working properly. We have taken on some technical debt with our database (*reckless and deliberate*) that we will have to repay in the next iteration.

- [Commit of completed database](6be7b5c4b22c55faaa872da22976323d12ecc901)
- [Completing a large feature in the Password Manager](b2266f80c0ce76aaea343a0be89cfd5337a7f5d0)
- [Dealing with merge conflicts](734ae3d7c7de3efbc9de258b13d9a3abe295ce52)

## Design Patterns

During this iteration we learned about well-known design principles which greatly helped with development of our application. Our application has large features that are GUI heavy that we want to be the main focus. Using the design principle "[*Command*](https://refactoring.guru/design-patterns/command)", we created a very user friendly layer of different GUIs and were able to handle all the user input with a layer of business logic.

- [Handling empty fields in GUI](c6e73872c3f5e5cbc6543217075dcbfae5302292)
- [Three features that take input in the GUI layer (login/signup pages, a calendar, and a password manager)](b2266f80c0ce76aaea343a0be89cfd5337a7f5d0)

## Iteration 1 Feedback

In iteration 1 - we were given the issue of reorganizing our files into proper folders and having a more clearly thoughtout architecture. We fixed the architecture and refactored a lot of our code right at the end of the last iteration and before starting this one. It was a very important piece of feedback and has kept our project much more organized than it was in the last iteration. Since then, we have learned how important good organization is for a large scale project such as this.

- [Commit where we refactored and reorganized our code](3e3110a2b655c26823dfdfa2aae30165d04b762b).
