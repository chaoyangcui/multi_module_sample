# -*- coding: UTF-8 -*-
# coding=utf-8
# author: Eric

import time
import datetime


if __name__ == '__main__':
    now = datetime.datetime.now()
    print(now)
    print('1503626356433')
    print(int(time.time() * 1000))
    x = 10
    print("" + str(x))

    arr = [1, 2, 3]
    try:
        print(arr[3])
    except BaseException:
        print("Error")
    else:
        print("1111")
    finally:
        print("finally.")
    print("the last line.")




