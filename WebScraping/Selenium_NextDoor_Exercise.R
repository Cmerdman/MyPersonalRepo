library(RSelenium)
library(rvest)
library(dplyr)

rD = rsDriver(browser="firefox", port=4547L, verbose=F)                   #try getallcookies and inserting random ones until it works and bypasses login
remDr = rD[["client"]]
remDr$navigate("https://nextdoor.com/login/")

email = "gderdman@fioptics.com"
password = "Pump1kin"

remDr$findElement(using = "id", value = "id_email")$sendKeysToElement(list(email))
remDr$findElement(using = "id", value = "id_password")$sendKeysToElement(list(password))      #logs in in to NextDoor page
remDr$findElement(using = "id", value = "signin_button")$clickElement()
Sys.sleep(5)











remDr$close()
rm(rD)          #closes and quits the server
gc()