library(jsonlite)
library(tidyverse)
data = fromJSON(txt = "Doordash/reddit_doordash_comments_20200609-140129.log")
data = data.frame(matrix(unlist(data), nrow=length(data), byrow=T))
data
