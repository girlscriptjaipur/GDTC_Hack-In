import os
import cv2
import numpy as np
import faceRecognition_Yukti as fr

#To save training data in trainingData.yml file
##faces,faceID=fr.labels_for_training_data('trainingImages')
##face_recognizer=fr.train_classifier(faces,faceID)
##face_recognizer.write('trainingData.yml')


face_recognizer = cv2.face.LBPHFaceRecognizer_create()
face_recognizer.read('trainingData.yml')#Load saved training data

name = {0 : "Shahrukh",1 :"Yukti", 2:"Kangna"}


cap=cv2.VideoCapture(0)

while True:
    ret,test_img=cap.read()
    faces_detected,gray_img=fr.faceDetection(test_img)



    for (x,y,w,h) in faces_detected:
      cv2.rectangle(test_img,(x,y),(x+w,y+h),(255,0,0),thickness=7)

    resized_img = cv2.resize(test_img, (1000, 700))
    cv2.imshow('face detection',resized_img)
    cv2.waitKey(10)


    for face in faces_detected:
        (x,y,w,h)=face
        roi_gray=gray_img[y:y+w, x:x+h]
        label,confidence=face_recognizer.predict(roi_gray) #predicting the label of given image
        print("confidence:",confidence)
        print("label:",label)
        fr.draw_rect(test_img,face)
        predicted_name=name[label]
        if confidence < 37: #If confidence less than 37 then don't print predicted face text on screen
            fr.put_text(test_img,predicted_name,x,y)


    resized_img = cv2.resize(test_img, (1000, 700))
    cv2.imshow('face recognition ',resized_img)
    if cv2.waitKey(10) == ord('q'): #wait until 'q' key is pressed
        break


cap.release()
cv2.destroyAllWindows

