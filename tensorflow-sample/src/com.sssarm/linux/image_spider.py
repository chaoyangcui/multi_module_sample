# coding=utf-8
# author: Eric

import os
import time
import json
import urllib.parse
import urllib.request
from random import randint
import hashlib
import face_recognize

image_urls = []


def get_image_json_data_from_baiduapi(img_url):
    """
    从API地址中获取图片的json数据

    Args:
        :param img_url: API地址

    :return: 图片的json数据
    """
    user_agent = [
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.101 Safari/537.36',
        'Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.78 Safari/537.36 OPR/47.0.2631.55',
        'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:54.0) Gecko/20100101 Firefox/54.0',
        'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_6) AppleWebKit/603.3.8 (KHTML, like Gecko) Version/10.1.2 Safari/603.3.8'
    ]
    values = {
        'act': 'login',
        'login[email]': 'yzhang@i9i8.com',
        'login[password]': '123456'
    }
    headers = {'User-Agent': user_agent[randint(0, 3)]}

    # data = urllib.parse.urlencode(values)
    # req = urllib.request.Request(img_url, data, headers)
    req = urllib.request.Request(img_url, headers=headers)
    response = urllib.request.urlopen(req)
    the_page = response.read()

    # print(the_page.decode("utf8"))
    return the_page.decode("utf8")


def get_image_urls_from_response(json_data):
    # img_data = json.loads(json_data)
    img_data = {}
    try:
        img_data = json.loads(json_data)
    except BaseException:
        print("json loads error,json_data:{}".format(json_data))
        return []

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


def download_image_from_urls(file_path, urls):
    for image_url in urls:
        md5.update(image_url.encode('utf-8'))
        image_name = md5.hexdigest()
        image_file = '{0}/{1}.jpg'.format(file_path, image_name)
        urllib.request.urlretrieve(image_url, image_file)
        # print("image save:{imageUrl:{}, imageFile:{}}", image_url, image_file)
        # Download the file from `url` and save it locally under `file_name`:
        # with urllib.request.urlopen(image_url) as response, open('/tmp/{}.jpg'.format(image_name), 'wb') as out_file:
        #     data = response.read()  # a `bytes` object
        #     out_file.write(data)


def data_spider(spider_config):
    keyword = spider_config['keyword']
    file_path = spider_config['file_path']
    loop_times_start = spider_config['loop_times_start']
    loop_times = spider_config['loop_times']
    # 判断路径是否存在，不存在则创建
    if not os.path.exists(file_path):
        os.makedirs(file_path)
    # keyword = '小仓优子'
    keyword = urllib.parse.quote(keyword)
    for pn in range(loop_times_start, loop_times):
        curr_timestamp = int(time.time() * 1000)
        api_url = api_url_template.format(pn * 30, keyword, curr_timestamp)
        print(api_url)
        img_response = get_image_json_data_from_baiduapi(api_url)
        image_urls = get_image_urls_from_response(json_data=img_response)
        print(image_urls)
        download_image_from_urls(file_path=file_path, urls=image_urls)


md5 = hashlib.md5()
api_url_template = 'https://image.baidu.com/search/acjson?tn=resultjson_com' \
                   '&ipn=rj&ct=201326592&is=&fp=result&queryWord+=&cl=2' \
                   '&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1&word={1}&z=&ic=0&s=&se=&tab' \
                   '=&width=&height=&face=0&istype=2&qc=&nc=1&fr=&step_word={1}&pn={0}&rn=30' \
                   '&gsm=3c&{2}='
spider_configs = [
    {
        'name_en': 'YukoOgura',
        'keyword': '小仓优子',
        'file_path': '/Users/cuiguiyang/spider/YukoOgura/',
        'head_image_path': '/Users/cuiguiyang/headimage/YukoOgura/{}.jpg',
        'loop_times_start': 0,
        'loop_times': 50,
        'finished': True
    },
    {
        'name_en': 'Erika',
        'keyword': '桃谷绘里香',
        'file_path': '/Users/cuiguiyang/spider/Erika/',
        'head_image_path': '/Users/cuiguiyang/headimage/Erika/{}.jpg',
        'loop_times_start': 38,
        'loop_times': 50,
        'finished': False
    },
    {
        'name_en': 'SolaAoi',
        'keyword': '苍井空',
        'file_path': '/Users/cuiguiyang/spider/SolaAoi/',
        'head_image_path': '/Users/cuiguiyang/headimage/SolaAoi/{}.jpg',
        'loop_times_start': 0,
        'loop_times': 50,
        'finished': False
    }
]

if __name__ == '__main__':
    for spider_config in spider_configs:
        print(spider_config)
        if spider_config['finished']:
            continue
        file_path = spider_config['file_path']
        # 如果目录不存在则创建
        if not os.path.exists(file_path):
            os.makedirs(file_path)
        # 如果目录为空则抓去图片保存
        if not os.listdir(file_path):
            # 抓去图片并保存
            print("spider start.")
            data_spider(spider_config)
        else:
            print("spider start.")
            data_spider(spider_config)

        # 获取对应路径下面所有图片做人脸检测保存
        print("face recognize.")
        images = face_recognize.list_files(file_path)
        for image in images:
            face_recognize.faces_recognize_and_save(image, spider_config['head_image_path'])

    url = 'https://image.baidu.com/search/acjson?tn=resultjson_com' \
          '&ipn=rj&ct=201326592&is=&fp=result&queryWord+=&cl=2' \
          '&lm=-1&ie=utf-8&oe=utf-8&adpicid=&st=-1' \
          '&word=%E5%B0%8F%E4%BB%93%E4%BC%98%E5%AD%90&z=&ic=0&s=&se=&tab' \
          '=&width=&height=&face=0&istype=2&qc=&nc=1' \
          '&fr=&step_word=%E5%B0%8F%E4%BB%93%E4%BC%98%E5%AD%90&pn=60&rn=30' \
          '&gsm=3c&1503626356433='
    # img_response = get_image_json_data_from_baiduapi(url)
    # image_urls = get_image_urls_from_response(data=img_response)
    # print(image_urls)

    # example_img_url = 'https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=182546408,3144929003&fm=26&gp=0.jpg'
    # download_image_from_url(example_img_url)
    #
    # arr = ['a', 'b', 'c']
    # if len(arr) == 3:
    #     arr.append("d")
    # print(arr[0])

