
import cv2  # import computer vision library


cv2.VideoCapture(0)  # calling videocapturing fxn for starting the camera

cap = cv2.VideoCapture(0)

# status of camera
# check connection with camera drivers

if cap.isOpened() :
        print("camera open sucessfull")
else :
    print("check yor driver")


# its open the camera and ready to take picture but not show
# now taking image from camera using read

status,img=cap.read()  # take first picture

status,img1=cap.read()   # take second picture

# now imshow show the take picture
# img-110  --->  means its remove 110 from BGR  so picture look differently

cv2.imshow('live',img-110) 
cv2.imshow('live1',img1)

# waitkey is use for holding the image for a time

# if we passs "zero"   its wait for cursor action

cv2.waitKey(5000)    

# its stop the camera 
cap.release()   






