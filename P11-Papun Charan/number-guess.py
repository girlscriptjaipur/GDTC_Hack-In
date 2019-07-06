#number game
import random

print("This is the Higher Lower Guessing Game, where you have to guess a number and we will check how well you can guess")


def play():
    gen = random.randint(1, 100)
    #print(gen)

    gus = 0
    tri = 0

    while gus != gen:
        gus = int(input("Guess a nummber: "))
        tri = tri + 1
        if gus > gen:
            print("you need to guess lower, try again ")
        elif gus < gen:
            print("you need to guess higher, try again ")
        else:
            print("Congrats, you guessed it correct and You took " + str(tri) + " guesses to reach to the correct number")
            print("If you want to continue enter 1 and if you want to exit enter 0")
            x = int(input())
            return (x)



p =1
while(True):
    if p==0:
        break
    else:
        p = play()
