import cv2
import numpy as np
import os
#eucledian distance calculation
def distance(p1 , p2):
    return np.sqrt(np.sum((p2-p1)**2))
#KNN CODE
def knn(train, test , k=5):
    
    
    d=[]
    for i in range(train.shape[0]):
        #get the vecotr and label
        ix = train[i , :-1]
        iy = train[ i , -1]
        dist = distance(test , ix)
        d.append([dist , iy])
    # Sort based on distance and get top k
    dk = sorted(d, key=lambda x: x[0])[:k]
    # Retrieve only the labels
    labels = np.array(dk)[:, -1]

    # Get frequencies of each label
    output = np.unique(labels, return_counts=True)
    # Find max frequency and corresponding label
    index = np.argmax(output[1])
    pred =output[0][index]        
    
    return pred
    
cam = cv2.VideoCapture(0)
face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_alt.xml')
skip = 0
dataset_path ='./Data/'
face_data=[]

labels = []

class_id = 0
names = {} #mapping btw id - name

for fx in os.listdir(dataset_path):
    if fx.endswith('.npy'):
        names[class_id] = fx[ :-4] #create the mapping btw classid and name
        print("Loading file ",fx)
        data_item = np.load(dataset_path + fx)
        face_data.append(data_item)

        #create label for the class
        target = class_id*np.ones((data_item.shape[0],))
        class_id += 1
        labels.append(target)

face_dataset = np.concatenate(face_data , axis=0)
face_labels = np.concatenate(labels , axis =0).reshape((-1 , 1))
print(face_dataset.shape)
print(face_labels.shape)

trainset = np.concatenate((face_dataset , face_labels) , axis =1)
print(trainset.shape)


#testing
while True:
	ret,frame = cam.read()
	if ret==False:
		print("Something Went Wrong!")
		continue

	key_pressed = cv2.waitKey(1)&0xFF #Bitmasking to get last 8 bits
	if key_pressed==ord('b'): #ord-->ASCII Value(8 bit)
		break
	faces = face_cascade.detectMultiScale(frame,1.3,5)
	if(len(faces)==0):
		cv2.imshow("Faces Detected",frame)
		continue

	for face in faces:
		x,y,w,h = face
		face_section = frame[y-10:y+h+10,x-10:x+w+10];
		face_section = cv2.resize(face_section,(100,100))
		cv2.rectangle(frame,(x,y),(x+w,y+h),(0,255,255),2)

		pred = knn(trainset,face_section.flatten())
		name = names[int(pred)]
		cv2.putText(frame,name,(x,y-10),cv2.FONT_HERSHEY_SIMPLEX,1,(255,0,0),2,cv2.LINE_AA)

	cv2.imshow("Faces Detected",frame)

cam.release()
cv2.destroyAllWindows()
