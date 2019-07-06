#### Importing Necessary Libraries ####

import numpy as np
import cv2
import time 

print("!! Invisibility is no more a Dream !!")

# Capturing Webcam Feed
cap = cv2.VideoCapture(0)

time.sleep(3)

count = 0

background = 0

# Capturing Static Background Frame

for i in range(60):

    ret, background = cap.read()


# Flip the Image

background = np.flip(background, axis=1)

while(cap.isOpened()):
    
    ret, img = cap.read()

    if not ret:
        break

    img = np.flip(img, axis=1)
    
#Converting from BGR to HSV
    hsv = cv2.cvtColor(img, cv2.COLOR_BGR2HSV)

    lower_red = np.array([0, 120, 70])
    upper_red = np.array([10, 255, 255])
    mask1 = cv2.inRange(hsv, lower_red, upper_red) #seprating the cloak part

    lower_red = np.array([170, 120, 70])
    upper_red = np.array([180, 255, 255])
    mask2 = cv2.inRange(hsv, lower_red, upper_red)
    
    mask1 = mask1 + mask2 #if there is any shade of red in mask1 or mask2 it would be stored in mask1
    #noise removal
    mask1 = cv2.morphologyEx(mask1, cv2.MORPH_OPEN, np.ones((3, 3), np.uint8), iterations=2)#this morphical function is used to remove the noise in the noise
    mask1 = cv2.morphologyEx(mask1, cv2.MORPH_DILATE, np.ones((3, 3), np.uint8), iterations=1)#it increases the white region
    #except the cloak       
    
    mask2 = cv2.bitwise_not(mask1)# this is bit complement function if the red color is available then the mask1 is true so the mask2 becomes false

    res1 = cv2.bitwise_and(background, background, mask = mask1)
    res2 = cv2.bitwise_and(img, img, mask = mask2)
    final_output = cv2.addWeighted(res1, 1, res2, 1, 0)

    cv2.imshow('Eureka !!', final_output)
    k = cv2.waitKey(10)
    if k == 27:
        break

cap.release()
cv2.destroyAllWindows()
