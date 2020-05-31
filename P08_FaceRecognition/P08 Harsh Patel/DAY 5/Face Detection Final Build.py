# Face Detection Improvement with Live Stream Face Recognition [Harsh Patel]

import cv2
import imutils

# using the Haar Cascade Classifier
faceCascade = cv2.CascadeClassifier('../day 2/haarcascade_frontalface_default.xml')

id = input("Enter the Face Recognition ID:")
sampleNum = 0
cap = cv2.VideoCapture(0)

while True:
   ret, image = cap.read()
   image = imutils.resize(image, height=300)
   gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)

   # Detect faces in the image
   faces = faceCascade.detectMultiScale(
       gray,
       scaleFactor=1.2,
       minNeighbors=5,
       minSize=(30, 30),
       flags=cv2.CASCADE_SCALE_IMAGE
   )

   print("Found {0} faces!".format(len(faces)))

   # Draw a rectangle around the faces
   for (x, y, w, h) in faces:
       sampleNum = sampleNum + 1
       cv2.imwrite("dataset/user." + str(id) + "." + str(sampleNum) + ".jpg", gray[y:y + h, x:x + w])
       cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
       cv2.waitKey(100)

   cv2.imshow("Faces found", image)
   cv2.waitKey(1)
   if (sampleNum > 40):
       break
cv2.destroyAllWindows()

# Training the Module for Facial Recognition

import os
import numpy as np
import cv2
from PIL import Image

recognizer = cv2.face.LBPHFaceRecognizer_create()
path = 'dataset'

def getImageWithID(path):
   imagePaths = [os.path.join(path, f) for f in os.listdir(path)]
   faces = []
   IDs = []
   for imagePath in imagePaths:
       faceImg = Image.open(imagePath).convert('L')
       faceNp = np.array(faceImg, 'uint8')
       ID = int(os.path.split(imagePath)[-1].split('.')[1])
       faces.append(faceNp)
       IDs.append(ID)
       cv2.imshow("traning", faceNp)
       cv2.waitKey(10)
   return IDs, faces

Ids, faces = getImageWithID(path)
recognizer.train(faces, np.array(Ids))
recognizer.save('recognizer/trainningData.yml')
cv2.destroyAllWindows()



# Recognizing the Face impressions saved and trying for Face Recognition

import cv2

recognizer = cv2.face.LBPHFaceRecognizer_create()
recognizer.read('recognizer\\trainningData.yml')
id = 0
font = cv2.FONT_HERSHEY_SIMPLEX
names = []
cap = cv2.VideoCapture(0)
frame_num = 1
faceCascade = cv2.CascadeClassifier('../day 2/haarcascade_frontalface_default.xml')

while True:
   ret, image = cap.read()
   # Create the haar cascade
   gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
   if frame_num == 1:

       # Detect faces in the image
       faces = faceCascade.detectMultiScale(
           gray,
           scaleFactor=1.2,
           minNeighbors=5,
           minSize=(30, 30),
           flags=cv2.CASCADE_SCALE_IMAGE
       )

       print("Found {0} faces!".format(len(faces)))

       # Draw a rectangle around the faces
       for (x, y, w, h) in faces:
           cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
           tracker = cv2.TrackerCSRT_create()
           tracker.init(image, (x, y, w, h))

   (success, box) = tracker.update(image)
   # check to see if the tracking was a success
   if success:
       frame_num = frame_num + 1
       (x, y, w, h) = [int(v) for v in box]
       cv2.rectangle(image, (x, y), (x + w, y + h),
                     (255, 0, 0), 2)
       id, conf = recognizer.predict(gray[y:y + h, x:x + w])
       print(str(id) + "  matched")

       cv2.putText(image, names[int(id)], (x, y + h + 20), font, .6, (0, 255, 0), 2)

   cv2.imshow("Faces found", image)
   if cv2.waitKey(1) == 27:
       break
cv2.destroyAllWindows()

