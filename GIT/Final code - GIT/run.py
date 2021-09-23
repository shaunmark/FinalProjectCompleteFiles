import os
import glob
import cv2 as cv
from  Car_Number_Plate_Detection import *

path = glob.glob("./data/images/*.png")
i=1
for img in path:
    path1 = 'python detect.py --weights ./checkpoints/custom-416 --size 416 --model yolov4 --images ./data/images/car'+str(i)+'.png --crop'
    os.system(path1)
    path2 = './detections/crop/car'+str(i)+'/license_plate_1.png'
    read_plate_number(path2)
    i = i+1
#os.system('python Car_Number_Plate_Detection.py ')

print("DONE")