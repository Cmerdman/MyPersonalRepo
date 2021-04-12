library("tidyverse")
library("readxl")
invoices = read_xlsx("data/Invoices.xlsx")
summary = read_xlsx("data/Summary.xlsx")
income = read.csv("data/income_data.csv")
income <- income[,-c(1)]
annIncome <- read.csv("data/annualIncomeSummary.csv")

##### Summary Statistics #####

mean(invoices[["MWh"]])
median(invoices[["MWh"]])
min(invoices[["MWh"]])
max(invoices[["MWh"]])
# mean MWh = 0.9671409, median = 0.8, min = -0.4, max = 11.4

mean(invoices[["Margin"]])
median(invoices[["Margin"]])
min(invoices[["Margin"]])
max(invoices[["Margin"]])
# mean margin = 5.453739, median = 3.6, min = -80.12, max = 227.58

mean(invoices[["Price_MWh"]])
median(invoices[["Price_MWh"]])
min(invoices[["Price_MWh"]])
max(invoices[["Price_MWh"]])
# mean Price_MWh = 56.6396, median = 55.9, min = 0, max = 77.31

length(unique(invoices[["CDSID"]]))
# 2375 unique ID's

df <- unique(invoices[["CDSID"]])
m <- c()
for(i in 1:2375){
  dt = invoices[invoices$CDSID == df[i],]
  m[i] <- dt[1, "Invoice_count"]
}
e <- sapply(m, mean)
mean(e)
mean(summary[["Invoice_count"]])
median(e)
min(e)
max(e)
# mean of invoice count = 31.72337, median = 34, min = 16, max = 52

mean(income[["medIncome"]])
median(income[["medIncome"]])
min(income[["medIncome"]])
max(income[["medIncome"]])
#mean median income = 59314.57, median = 56414, min = 20221, max = 142689

#### exploratory Analysis ####


##Exploring relation of margin and zip code, margin and zip code income##

#margin and median income
medInc <- annIncome[, "medIncome"]
margin <- summary[, "Margin_per_invoice"]
margin <- unlist(margin)
relation <- lm(medInc ~ margin)
plot(margin , medInc , col = "blue", main = "Median income and Margin regression")
abline(lm(medInc ~ margin), cex = 1.3, pch = 16, xlab = "Median Income", ylab = "margin of profitability")

#margin and zip code
zipcode <- summary[, "Zip"]
margin <- summary[, "Margin_per_invoice"]
margin <- unlist(margin)
plot(zipcode, margin, main = "Scatter plot by zipcode and margin", xlab = "Zip Code", ylab = "Margin of Profitability", pch = 19)
zip_uni = unique(zipcode)
means <- c()
nums <- c()
for(x in 1:407){
  zips <-c()
  y = 0
  for(i in 1:2375){
    temp = zipcode[i:i,1:1]
    if(zip_uni[x:x, 1:1] == temp){
      zips <- c(zips, margin[i:i])
      y = y+1
    }
  }
  nums <- c(nums, y)
  means<- c(means, mean(as.numeric(zips)))
}
marginByZip = data.frame(zip_uni, means, nums, stringsAsFactors = F)
write.csv(marginByZip, 'data/marginByZip')










