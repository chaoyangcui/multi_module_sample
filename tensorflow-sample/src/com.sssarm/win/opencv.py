# -*- coding: utf-8 -*-

import time
import cv2
import sys


def f_capture():
    # input_image_file = 'D:/Programs/hezhao.jpg'
    input_image_file = 'D:/Programs/trumpmokeer.jpg'
    # input_image_file = 'D:/Programs/capture.jpg'

    # 通过camera获取图片,并且保存为capture.jpg
    cap = cv2.VideoCapture(0)
    _, capture = cap.read()
    cv2.imwrite('D:/Programs/capture.jpg', capture)
    # input_image_file = capture

    # face_classifier = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")
    face_classifier = cv2.CascadeClassifier("D:/Programs/haarcascade_frontalface_default.xml")
    input_image_obj = cv2.imread(input_image_file)
    if input_image_obj is None:
        print('input empty.')
        # return
    cvt_image = cv2.cvtColor(input_image_obj, cv2.COLOR_BGR2GRAY)

    found_faces = face_classifier.detectMultiScale(cvt_image, scaleFactor=1.3, minNeighbors=9, minSize=(50, 50),
                                                flags=cv2.CASCADE_SCALE_IMAGE)
    print("找到{}个".format(len(found_faces)))
    print("找到%s个%s" % (len(found_faces), "face"))

    for (x, y, w, h) in found_faces:
        img = cv2.rectangle(input_image_obj, (x, y), (x + w, y + h), (0, 0, 255), 2)
        print(str(img.shape[0]) + '*' + str(img.shape[1]) + '*' + str(img.shape[2]))
        print('x:' + str(x) + 'y:' + str(y) + 'w:' + str(w) + 'h:' + str(h))
        cv2.imwrite('D:/Programs/__{}.jpg'.format(x), img=img[y:(y + h), x:(x + w)])  # cut img (y:y+cutH, x:x+cutW)
        # cv2.imshow(str(x), img[y:(y + h), x:(x + w)])

    # cv2.waitKey(0)
    # cv2.destroyAllWindows()

    # 通过camera获取图片
    # cap = cv2.VideoCapture(0)
    # _, capture = cap.read()
    # cv2.imwrite('D:/Programs/capture.jpg', capture)

    # print("Hello")
    # print(cv2.__version__)

if __name__ == '__main__':
    # while True:
        time.sleep(1)
        f_capture()
