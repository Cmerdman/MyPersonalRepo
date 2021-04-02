library("tidyverse")
library("readxl")
invoices = read_xlsx("AEP/data/Invoices.xlsx")
summary = read_xlsx("AEP/data/Summary.xlsx")
income = read.csv("data/annualIncomeSummary.csv")
Zip <- income[, "Zip"]
incomeNew <- cbind(Zip, income[, 52:55])
income <- distinct(incomeNew)


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
# mean margin = 56.6396, median = 55.9, min = 0, max = 77.31

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



#### exploratory Analysis ####

