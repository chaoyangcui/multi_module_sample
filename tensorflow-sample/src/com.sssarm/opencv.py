# -*- coding: utf-8 -*-

import time
import cv2
import sys


def f_capture():
    # inputImageFile = 'D:/Programs/hezhao.jpg'
    inputImageFile = 'D:/Programs/trumpmokeer.jpg'
    # inputImageFile = 'D:/Programs/capture.jpg'

    # 通过camera获取图片
    cap = cv2.VideoCapture(0)
    _, capture = cap.read()
    cv2.imwrite('D:/Programs/capture.jpg', capture)
    # inputImageFile = capture

    # faceClassifier = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")
    faceClassifier = cv2.CascadeClassifier("D:/Programs/haarcascade_frontalface_default.xml")
    objImage = cv2.imread(inputImageFile)
    if objImage is None:
        print('input empty.')
        # return
    cvtImage = cv2.cvtColor(objImage, cv2.COLOR_BGR2GRAY)

    foundFace = faceClassifier.detectMultiScale(cvtImage, scaleFactor=1.3, minNeighbors=9, minSize=(50, 50),
                                                flags=cv2.CASCADE_SCALE_IMAGE)
    print("找到{}个".format(len(foundFace)))
    print("找到%s个%s" % (len(foundFace), "face"))

    for (x, y, w, h) in foundFace:
        img = cv2.rectangle(objImage, (x, y), (x + w, y + h), (0, 0, 255), 2)
        print(str(img.shape[0]) + '*' + str(img.shape[1]) + '*' + str(img.shape[2]))
        print('x:' + str(x) + 'y:' + str(y) + 'w:' + str(w) + 'h:' + str(h))
        cv2.imwrite('D:/Programs/{}.jpg'.format(x), img=img[y:(y + h), x:(x + w)])  # cut img (y:y+cutH, x:x+cutW)
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
