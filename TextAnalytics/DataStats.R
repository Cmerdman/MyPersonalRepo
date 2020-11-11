library(tidyverse)
couriers_black = read.table("CouriersofReddit/grep/black.txt")  %>% rename(Place = V1,
                                                                           Comment = V4)
couriers_black[c(2,3,5)] = NULL
couriers_discriminate = read.table("CouriersofReddit/grep/discriminate.txt")  %>% rename(Place = V1,
                                                                                         Comment = V4)
couriers_discriminate[c(2,3,5)] = NULL
couriers_racism = read.table("CouriersofReddit/grep/racism.txt") %>% rename(Place = V1,
                                                                            Comment = V4)
couriers_racism[c(2,3,5)] = NULL
couriers_racistcomment = read.table("CouriersofReddit/grep/racistcomment.txt") %>% rename(Place = V1,
                                                                                          Comment = V4)
couriers_racistcomment[c(2,3,5)] = NULL

post_black = read.table("PostMates/grep/black.txt") %>% rename(Place = V1,
                                                               Comment = V4)
post_black[c(2,3,5)] = NULL
post_discriminate = read.table("PostMates/grep/discriminate.txt") %>% rename(Place = V1,
                                                                             Comment = V4)
post_discriminate[c(2,3,5)] = NULL

post_racism = read.table("PostMates/grep/racism.txt") %>% rename(Place = V1,
                                                                 Comment = V4)
post_racism[c(2,3,5)] = NULL
post_racistcomment = read.table("PostMates/grep/racistcomment.txt") %>% rename(Place = V1,
                                                                               Comment = V4)
post_racistcomment[c(2,3,5)] = NULL

#starts


con = file("Doordash/reddit_doordash_comments_20200609-140129.log", open = "r")
authors = c()
text = c()
score = c()

while(T){
  line = readLines(con, n = 1)
  if(length(line) == 0) break
  else if(grepl("comment_author", line)) authors <- c(authors, line)
  else if(grepl("comment_body", line)) text <- c(text, line)
  else if(grepl("comment_upvotes", line)) score <- c(score, line)
}

cleanValues = function(values){
  x=1
  for(i in values){
    values[x] = gsub("\"","", values[x])
    values[x] = gsub("                comment_author: ","",values[x])
    values[x] = gsub("                comment_body: ","",values[x])
    values[x] = gsub("                comment_upvotes: ","",values[x])
    values[x] = gsub(",","",values[x])
    x = x+1
  }
  return(values)
}

authors = cleanValues(authors)
text = cleanValues(text)
score = cleanValues(score)
score = as.numeric(score)

cleaned_data = data.frame(authors,score,text)



