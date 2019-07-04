import os
import cv2
import numpy as np
import time
import argparse

parser = argparse.ArgumentParser()
#input arguments
parser.add_argument("--video")
args =  parser.parse_args()
cap = cv2.VideoCapture(args.video if args.video else 0)
Object_Cascade = cv2.CascadeClassifier("haarcascade_frontalface_alt.xml")
time.sleep(3)
count = 0
background = 0


for i in range(60):
    ret,background = cap.read()

while(cap.isOpened()):
    ret,img = cap.read()
    if not ret:
        break
    count += 1
    hsv = cv2.cvtColor(img , cv2.COLOR_BGR2HSV)
    lower_red = np.array([0,120,70])
    upper_red = np.array([10 , 255 , 255])
    mask1 = cv2.inRange(hsv , lower_red , upper_red)

    lower_red = np.array([170 , 120 , 70])
    upper_red = np.array([180 , 255 , 255])

    mask2= cv2.inRange(hsv ,lower_red , upper_red)

    mask1 = mask1 + mask2

    mask1 = cv2.morphologyEx(mask1 , cv2.MORPH_OPEN , np.ones((3,3),np.uint8),iterations =2)
    mask1 = cv2.dilate(mask1 , np.ones((3,3) , np.uint8),iterations = 1)
    mask2 = cv2.bitwise_not(mask1)

    res1 = cv2.bitwise_and(background , background , mask = mask1)
    res2 = cv2.bitwise_and(img , img , mask = mask2)
    final_output = cv2.addWeighted(res1 , 1 , res2 , 1 , 0)

    cv2.imshow('Magic!!!' , final_output)
    key_pressed = cv2.waitKey(1)&0xFF #Bitmasking to get last 8 bits
    if key_pressed==ord('q'): #ord-->ASCII Value(8 bit)
        break
cam.release()
cv2.destroyAllWindows()