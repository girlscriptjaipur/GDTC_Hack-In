#Rock Paper Scissors Game

import random
options =["rock", "paper", "scissors","rock", "paper", "scissors","rock", "paper", "scissors"]
print("Welcome to Rock Paper Scissors Game")

play = "yes"
coscore = 0
myscore = 0
while play == "yes":
    comove= random.choice(options)
    mymove = input("What will you choose? rock, paper or scissors: ")
    print("Computer choose ", comove)
    if mymove==comove:
        print("it's a tie")
    elif mymove=="scissors" and comove=="rock":
        print("Computer wins")
        coscore = coscore + 1
    elif mymove == "scissors" and comove == "paper":
        print("Player wins")
        myscore = myscore+1
    elif mymove=="paper" and comove=="rock":
        print("Player wins")
        myscore = myscore+1
    elif mymove=="paper" and comove=="scissors":
        print("Computer wins")
        coscore = coscore + 1
    elif mymove=="rock" and comove=="paper":
        print("Computer wins")
        coscore = coscore+1
    elif mymove=="rock" and comove=="scissors":
        print("Player wins")
        myscore = myscore+1
    print("Your score is: ",myscore)
    print("Computer's score is: ",coscore)
    print("Do you want to play agin: yes/no")
    play = input()
