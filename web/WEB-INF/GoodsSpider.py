# conding=utf-8
# ※Author = 胡志达
# ※Time = 2021/12/2 23:54
# ※File Name = GoodsSpider.py
# ※Email = 840831038@qq.com

import requests
from bs4 import BeautifulSoup
import json
import random
import re
import os
import xlwt



img_host = "https://img1.360buyimg.com/n6/"
compiler_name = re.compile(r'"ad_title_text":"(.*?)",')
compiler_img = re.compile(r'"image_url":"(.*?)",')
compiler_price = re.compile(r'"sku_price":"(.*?)",')

workbook = xlwt.Workbook()
sheet = workbook.add_sheet('sheet 1')
proxies = {"http":None,"https":None}
headers = {
        'user-agent':'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.45 Safari/537.36',
    }
row = 1

for i in range(1,10):
    url = "https://re.jd.com/search/getHotSaleGoods?keyword=arcteryx&page=%d&cid3=12126&semword=arcteryx&page_uuid=2477405d-22df-4ba1-bb1b-59b269baabde&callback=jQuery17205559821354599574_1638498503638&_=1638498503769"%i
    req = requests.get(url,headers=headers,proxies=proxies)
    rs_name = re.findall(compiler_name,req.text)
    rs_imgsrc = re.findall(compiler_img,req.text)
    rs_price = re.findall(compiler_price,req.text)
    # print(req.text)

    for i in range(len(rs_name)):
        column = 0
        sheet.write(row,column,rs_name[i].replace("\\u0027","\'"))
        column+=1
        sheet.write(row,column,float(rs_price[i]))
        column += 1
        sheet.write(row,column,random.randint(0,int(float(rs_price[i]))))
        column += 1
        sheet.write(row,column,"python-spider")
        column+=1
        sheet.write(row,column,rs_name[i].replace("\\u0027","\'"))
        column+=1
        sheet.write(row, column, img_host+rs_imgsrc[i])
        row+=1




workbook.save('C://Users/84083/Desktop/test.xls')