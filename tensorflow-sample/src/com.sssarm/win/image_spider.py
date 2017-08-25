# coding=utf-8
# author: Eric

import os
import json
import urllib.parse
import urllib.request
from random import randint
import hashlib


def get_json_data(img_url):
    user_agent = [
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36',
        'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36 OPR/47.0.2631.55',
        'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0'
    ]
    values = {
        'act': 'login',
        'login[email]': 'yzhang@i9i8.com',
        'login[password]': '123456'
    }
    headers = {'User-Agent': user_agent[randint(0, 2)]}

    # data = urllib.parse.urlencode(values)
    # req = urllib.request.Request(img_url, data, headers)
    req = urllib.request.Request(img_url, headers=headers)
    response = urllib.request.urlopen(req)
    the_page = response.read()

    # print(the_page.decode("utf8"))
    return the_page.decode("utf8")


def get_image_urls_from_response(data):
    img_data = json.loads(data)
    # print(img_data['queryExt'])
    # print(img_data['data'])
    image_urls = []
    for item in img_data['data']:
        if 'middleURL' in item:
            # print("imageUrl:{}".format(item['middleURL']))
            # download_image_from_url(item['middleURL'])
            image_urls.append(item['middleURL'])
        else:
            print("item[{}] has no attr middleURL".format(item))
    return image_urls


def download_image_from_url(image_url):
    file_path = '/tmp/'
    if not os.path.exists(file_path):
        os.mkdir(file_path)
    md5 = hashlib.md5()
    md5.update(image_url.encode('utf-8'))
    image_name = md5.hexdigest()
    urllib.request.urlretrieve(image_url, '/tmp/{}.jpg'.format(image_name))
    # Download the file from `url` and save it locally under `file_name`:
    # with urllib.request.urlopen(image_url) as response, open('/tmp/{}.jpg'.format(image_name), 'wb') as out_file:
    #     data = response.read()  # a `bytes` object
    #     out_file.write(data)


if __name__ == '__main__':
    url = 'https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result&queryWord+=&cl=2' \
          '&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&word=%E5%B0%8F%E4%BB%93%E4%BC%98%E5%AD%90&z=&ic=0&s=&se=&tab' \
          '=&width=&height=&face=0&istype=2&qc=&nc=1&fr=&step_word=%E5%B0%8F%E4%BB%93%E4%BC%98%E5%AD%90&pn=60&rn=30' \
          '&gsm=3c&1503626356433= '
    img_response = get_json_data(url)
    image_urls = get_image_urls_from_response(data=img_response)
    print(image_urls)

    # example_img_url = 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=182546408,3144929003&fm=26&gp=0.jpg'
    # download_image_from_url(example_img_url)
    #
    # arr = ['a', 'b', 'c']
    # print(arr[0])
    #
    # keyword = '小仓优子'
    # keyword = urllib.parse.quote(keyword)
    # print(urllib.parse.unquote('%E5%B0%8F%E4%BB%93%E4%BC%98%E5%AD%90'))
    # url_template = 'https://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&ct=201326592&is=&fp=result' \
    #                '&queryWord+=&cl=2' \
    #                '&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&word={1}&z=&ic=0&s=&se=&tab' \
    #                '=&width=&height=&face=0&istype=2&qc=&nc=1&fr=&step_word={1}&pn={0}&rn=30' \
    #                '&gsm=3c&1503626356433= '
    # for pn in range(0, 100, 30):
    #     print(url_template.format(pn, keyword))

