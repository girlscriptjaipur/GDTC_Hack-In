import cv2
import numpy as np
import time

#initialization for using web cam
camera = cv2.VideoCapture(0)
time.sleep(1)
background = 0

print("remembring background")
#for background capturing
for i in  range(30):
	index, background = camera.read()
print("Press ESC to exit the magic window")

#for real time magic !!
while (camera.isOpened()):
	index, image = camera.read()
	if not index:
		print("NO furthur Video Captured")
		break
	#converting to hsv for color selection
	hsv_img = cv2.cvtColor(image,cv2.COLOR_BGR2HSV)
	
	#selecting colour range
	low_red = np.array([0,120,70])
	up_red = np.array([10,255 ,255])
	mask1 = cv2.inRange(hsv_img,low_red,up_red)

	low_red = np.array([170 , 120 , 70])
	up_red = np.array([180,255,255])
	mask2= cv2.inRange(hsv_img ,low_red , up_red)

	#adding all the range of cloak
	cloak_mask = mask1 + mask2
	
	#structuring element for preserving edges
	kernel = np.array([[0,1,0],[1,1,1],[0,1,0]],np.uint8)
	
	#removing salt and pepper noise and for sharp edges of invisible cloth
	opnImg = cv2.morphologyEx(cloak_mask, cv2.MORPH_OPEN, kernel,iterations = 1)
	clsImg = cv2.morphologyEx(opnImg, cv2.MORPH_CLOSE, kernel, iterations = 2)

	#selecting non cloak mask
	mask = cv2.bitwise_not(cloak_mask)

	res1 = cv2.bitwise_and(background , background , mask = cloak_mask)
	res2 = cv2.bitwise_and(image , image , mask = mask)
	final_output = cv2.addWeighted(res1 , 1 , res2 , 1 , 0)

	#for ending the magic
	cv2.imshow("ALAS!!!!",final_output) 
	key = cv2.waitKey(5)
	if key == 27:
		break
cap.release()
cv2.destroyAllWindows()