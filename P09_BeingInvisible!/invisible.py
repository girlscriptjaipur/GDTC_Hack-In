import cv2
import numpy as np
import time
import argparse

parser = argparse.ArgumentParser()
#input arguments
parser.add_argument("--video")
args =  parser.parse_args()

#0 is index of the device's primary camera 
capture_video = cv2.VideoCapture(args.video if args.video else 0)
Object_Cascade = cv2.CascadeClassifier("haarcascade_frontalface_alt.xml")

#allow camera to initialize
time.sleep(3)
count = 0
background = 0

#capture background for replacing
for i in range(60):
    ret, background = capture_video.read()
    if not ret:
        continue

#flipping of the frame
background = np.flip(background, axis=1)

print("Press q to exit.")
while(capture_video.isOpened()):
    ret, img = capture_video.read()
    if not ret:
        print("Error in reading!")
        break
    img = np.flip(img, axis=1)
    count += 1

    #Conversion to Hue Saturation Value mode
    hsv = cv2.cvtColor(img , cv2.COLOR_BGR2HSV)

    #selecting colour range to replace
    lower_red = np.array([170 , 120 , 70])
    upper_red = np.array([180 , 255 , 255])
    mask= cv2.inRange(hsv ,lower_red , upper_red)

    mask = mask

    #refining the mask by performing opening followed by closing
    mask = cv2.morphologyEx(mask , cv2.MORPH_OPEN , np.ones((1,1),np.uint8),iterations = 2)
    mask = cv2.morphologyEx(mask , cv2.MORPH_CLOSE , np.ones((3,3),np.uint8),iterations = 3)

    mask = cv2.dilate(mask , np.ones((1,1) , np.uint8),iterations = 1)
    mask = cv2.bitwise_not(mask)                      

    res1 = cv2.bitwise_and(background , background , mask = mask1)
    res2 = cv2.bitwise_and(img , img , mask = mask)
    final_output = cv2.addWeighted(res1 , 1 , res2 , 1 , 0)

    cv2.imshow('Magic!!!' , final_output)
    key_pressed = cv2.waitKey(1)&0xFF #Bitmasking to get last 8 bits
    if key_pressed==ord('q'): #ord-->ASCII Value(8 bit)
        break
capture_video.release()
cv2.destroyAllWindows()
