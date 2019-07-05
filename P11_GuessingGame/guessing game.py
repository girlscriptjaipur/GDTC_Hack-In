import random
import sys

def guessNumber():
    number=random.randint(1,1000)
    print(number)
    count=1
    guess= int(input("Enter your guess between 1 and 1000: "))

    while guess !=number:
        count+=1
        if guess > (number + 10):
            print("Too high!")
        elif guess < (number - 10):
            print("Too low!")
        elif guess > number:
            print("Getting warm but still high!")
        elif guess < number:
            print("Getting warm but still Low!")

        guess = int(input("Try again "))

    if guess == number:
        print("You rock! You guessed the number in ", count, " tries!")
        return

guessNumber()

again = str(input("Do you want to play again (type yes or no): "))
if again == "yes":
    guessNumber()
else:
    sys.exit(0)