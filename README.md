# Creating a Console Table

## Introduction

A lot of professors assign an end-of-semester project something like this.

> Create a table of values on the console.

I realize that's pretty generic.  Today, I saw a question on Stack Overflow where a professor asked for a console table.  Here's the output from one of my test runs.

    Welcome to a dice rolling game simulation.

    Three players will each take a turn rolling one die.

    At the end of each round, the die rolls are compared.  If
    two players have the same roll, their die roll value is
    doubled and added to their total score.  Otherwise, each
    player adds the die value to his total score.

    The player with the highest score after N rounds wins!

    Enter number of rounds: 20

    +-------+----------+----------+----------+----------------+----------------+----------------+
    | Round | Player 1 | Player 2 | Player 3 | Player 1 total | Player 2 total | Player 3 total |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |     1 |        3 |        4 |        2 |              3 |              4 |              2 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |     2 |        3 |        5 |        1 |              6 |              9 |              3 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |     3 |        3 |        4 |        2 |              9 |             13 |              5 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |     4 |        4 |        4 |        6 |             17 |             21 |             11 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |     5 |        3 |        4 |        4 |             20 |             29 |             19 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |     6 |        5 |        5 |        5 |             25 |             34 |             24 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |     7 |        2 |        6 |        5 |             27 |             40 |             29 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |     8 |        3 |        4 |        6 |             30 |             44 |             35 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |     9 |        5 |        3 |        4 |             35 |             47 |             39 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    10 |        6 |        4 |        4 |             41 |             55 |             47 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    11 |        4 |        2 |        1 |             45 |             57 |             48 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    12 |        2 |        2 |        3 |             49 |             61 |             51 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    13 |        2 |        5 |        3 |             51 |             66 |             54 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    14 |        6 |        1 |        3 |             57 |             67 |             57 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    15 |        6 |        2 |        5 |             63 |             69 |             62 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    16 |        3 |        4 |        1 |             66 |             73 |             63 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    17 |        3 |        3 |        4 |             72 |             79 |             67 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    18 |        4 |        2 |        6 |             76 |             81 |             73 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    19 |        4 |        6 |        3 |             80 |             87 |             76 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    |    20 |        6 |        3 |        1 |             86 |             90 |             77 |
    +-------+----------+----------+----------+----------------+----------------+----------------+
    
As you can see, this is a pretty comprehensive project.  There's a good bit of coding involved, without relying on too many Java classes.  I made some changes so the output is not exactly like the output the professor requested.

The programmer has to keep track of three players, implement the two player match rule, and write code to create the console table.

## Explanation

Whenever I code a Java application, I usually use the [model / view / controller](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93controller) (MVC) pattern.  This pattern allows me to separate my concerns and focus on one part of the application at a time.

First, I created a Player class.  The Player class is a plain Java getter / setter class.  The Player class holds a String player name, an int die roll, and an int total value.

Next, I created a GameModel class.  The GameModel class is a plain Java getter / setter class.  The GameModel class holds a Player array with three players.

Once I had the model done, I worked on coding the main class.  I made liberal use of the StringBuilder class to construct the strings that make up the console table.  I wrote methods to create the dashed line, the header line, and the detail lines.

The updateGameModel method is where the player's information is updated and displayed.  I used three if statements to implement the two-player match rule.
