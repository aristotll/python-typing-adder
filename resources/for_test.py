# coding=utf-8
# Name: for_test
# Created by aristotll.
# User: Yao
# Date: 17/6/11
# Time: 21:49

def greeting(name: str) -> str:
    return 'Hello ' + name


def greeting_with_type(name):  # type: (str) -> str
    return 'Hello ' + name
