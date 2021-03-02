"""
Write a password generator in Python. 
Be creative with how you generate passwords - strong passwords have a mix of lowercase letters, uppercase letters, numbers, and symbols. 
The passwords should be random, generating a new password every time the user asks for a new password. 
Include your run-time code in a main method.
"""

from random import randrange as rand

def passwordgen(x):
    password = ""
    for i in range(0,x):
        password = password + chars[rand(40)]
    return password


chars = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','w','x','y','z','0','1','2','3','4','5','6','7','8','9','!','@','#','$','%','&','*'] # 0 through 40

print("Would you like a password")
answer = input()


if answer == 'yes':
    print("Wonderful!")
    password = passwordgen(7)
    print("Your password: " + password)
else:
    print("Then why are you here?")
