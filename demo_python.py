#!/usr/bin/env python2
# -*- coding: utf-8 -*-

import urllib, urllib2,base64
import ssl

#https
host='https://vcode.weibits.com'
path = '/vcode'
#post
method = 'POST'


#your appcode= 自己的appcode
appcode = 'APPCODE 自己的appcode'

querys = ''
bodys = {}
url = host + path

f=open('./2079569834.jpg','rb')
imgdata=f.read()
base64=base64.b64encode(imgdata)


bodys['convert_to_jpg'] = '0'
bodys['img_base64'] =base64
bodys['typeId'] = '34'

post_data = urllib.urlencode(bodys)
request = urllib2.Request(url, post_data)
request.add_header('Authorization', appcode)
request.add_header('connection', 'keep-alive');

request.add_header('Content-Type', 'application/x-www-form-urlencoded; charset=UTF-8')
ctx = ssl.create_default_context()
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE
response = urllib2.urlopen(request,context=ctx)
#response = urllib2.urlopen(request)
content = response.read()
if (content):
    print(content)
