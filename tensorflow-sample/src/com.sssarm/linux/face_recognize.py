# encoding: utf-8

import os
import cv2
import hashlib


def faces_recognize_and_save(image_file, head_image_path_template):
    """
    识别传入的图片是否有人脸，识别之后保存至相应文件夹中

    Args:
        :param image_file:  需要识别的图片路径
        :param head_image_path_template: 检测出来的人脸的保存路径模版 eg:/User/tmp/{}.jpg

    :return:
    """
    # image_file = "/Users/cuiguiyang/test_image.jpg"
    capture = cv2.imread(image_file)
    if capture is None:
        print('input empty.')
        return

    # 此xml文件可在D:\My Documents\Downloads\opencv\sources\data\haarcascades下找到。
    # faceclassifier = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")
    # cvt_image = cv2.cvtColor(capture, cv2.COLOR_BGR2GRAY)
    # found_face = faceclassifier.detectMultiScale(
    #     cvt_image,
    #     scaleFactor=1.3,
    #     minNeighbors=9,
    #     minSize=(50, 50),
    #     flags=cv2.CASCADE_SCALE_IMAGE)
    # print len(found_face)

    # you can find this file in this folder:/usr/local/Cellar/opencv/2.4.13.2/share/OpenCV/haarcascades
    # face_cascade = cv2.CascadeClassifier("../haarcascades/haarcascade_frontalface_alt2.xml")
    face_cascade = cv2.CascadeClassifier("haarcascade_frontalface_default.xml")

    gray = cv2.cvtColor(capture, cv2.COLOR_BGR2GRAY)
    gray = cv2.equalizeHist(gray)
    # cv2.imwrite("/Users/cuiguiyang/gray.jpg", gray)
    faces = face_cascade.detectMultiScale(
        gray,
        scaleFactor=1.1,
        minNeighbors=3,
        minSize=(30, 30),
        flags=cv2.CASCADE_SCALE_IMAGE
    )

    print(len(faces))
    if len(faces) == 0:
        os.remove(image_file)
        return
    # 如果头像目录不存在则创建
    if not os.path.exists(head_image_path_template):
        os.makedirs(head_image_path_template)

    # headimage 保存绝对路径
    md5.update(image_file.encode('utf-8'))
    head_image_name = md5.hexdigest()
    head_img = head_image_path_template + head_image_name + HEAD_IMG_SUFFIX

    for (x, y, w, h) in faces:
        cv2.rectangle(capture, (x, y), (x + w, y + h), (0, 0, 255), 2)
        img = capture
        # print('h:'+str(img.shape[0]) + '*w:' + str(img.shape[1]) + '*' + str(img.shape[2]))
        # print('x:' + str(x) + '*y:' + str(y) + ',w:' + str(w) + '*h:' + str(h))
        # cut img (y:y+cutH, x:x+cutW)
        cv2.imwrite(head_img, img=img[y:(y + h), x:(x + w)])

        # print("找到{}个".format(len(found_face)))


def list_files(image_dir_path):
    file_list = os.listdir(image_dir_path)
    print(file_list)
    images = []
    for item in file_list:
        if item != '.DS_Store':
            image_path = image_dir_path + item
            print("image_path:{}".format(image_path))
            images.append(image_path)
    return images

"""
Global Const
"""
md5 = hashlib.md5()
HEAD_IMG_SUFFIX = '.jpg'

if __name__ == '__main__':
    print()

