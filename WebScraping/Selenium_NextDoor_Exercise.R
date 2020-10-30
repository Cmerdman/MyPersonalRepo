library(RSelenium)
library(rvest)
library(dplyr)

rD = rsDriver(browser="firefox", port=4546L, verbose=F)                
remDr = rD[["client"]]
remDr$navigate("https://nextdoor.com/")

email = "bdaa.nextdoor+brute@gmail.com"
address = "156 E 13th"

remDr$findElement(using = "css", value = "input[class = 'select-display-block']")$sendKeysToElement(list(address))
Sys.sleep(1)
remDr$sendKeysToElement(list("R", key = "enter"))
remDr$sendKeysToElement(list("R", key = "enter"))
remDr$findElement(using = "css", value = "input[class = 'nux-signup-address-chooser-email-field fs-block']")$sendKeysToElement(list(email))
remDr$findElement(using = "css", value = "button[class = 'css-3tna2']")$clickElement()
Sys.sleep(5)











remDr$close()
rm(rD)          #closes and quits the server
gc()